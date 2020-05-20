package ServerCentral;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import Log.Log;

public class ServerCentralImp implements ServerCentral{
	
	private ServerHoroscopo servicioHoroscopo;
    private ServerClima servicioClima;
    private Map<String, String[]> cache;
    private Semaphore semaforoCache;
    private ArrayList<String> protocoloHoroscopo;
	
	public ServerCentralImp(String ipHoroscopo, int portHoroscopo, String ipClima, int portClima) throws MalformedURLException{
		Hashtable<String, String[]> mapa = new Hashtable<>();
        this.cache = Collections.synchronizedMap(mapa);
        this.semaforoCache = new Semaphore(1);

        this.protocoloHoroscopo = new ArrayList<>();
        this.protocoloHoroscopo.addAll(Arrays.asList(
                new String[]{"AR", "TA", "GE", "CC", "LE", "VG", "LB", "ES", "SA", "CP", "AC", "PI"}));

        //Se conecta con los Servidores de Horoscopo y Clima
        try {
        	
        	URL urlHoroscopo = new URL("http://"+ipHoroscopo+":"+portHoroscopo+"/ws/Horoscopo?wsdl");
        	QName qnameHoroscopo = new QName("http://wsSHoroscopo/", "ServicioHoroscopoImpl");
    	    Service serviceHoroscopo = Service.create(urlHoroscopo, qnameHoroscopo);
    	    ServerHoroscopo servicioHoroscopo = serviceHoroscopo.getPort(ServerHoroscopo.class);
                        
            //this.svrHoroscopo = (ServerHoroscopo) Naming.lookup("//" + ipHoroscopo + ":" + portHoroscopo + "/ServerHoroscopo");
    	    
            Log.logInfo("ServidorCentral", "Se conecto al Servidor Horoscopo");
            
            URL urlClima = new URL("http://"+ipHoroscopo+":"+portHoroscopo+"/ws/Clima?wsdl");
        	QName qnameClima = new QName("http://wsSClima/", "ServicioClimaImpl");
    	    Service serviceClima = Service.create(urlClima, qnameClima);
    	    ServerHoroscopo sum = serviceClima.getPort(ServerClima.class);
            
            //this.svrClima = (ServerClima) Naming.lookup("//" + ipClima + ":" + portClima + "/ServerClima");
            
            Log.logInfo("ServidorCentral", "Se conecto al Servidor Clima");
        } catch (//NotBoundException | MalformedURLException | RemoteException ex) {
            Log.logError("ServidorCentral", ex.getMessage());
            System.err.println("->ServidorCentral: ERROR: " + ex.getMessage());
            System.exit(1);
        }
	}
	
	public ArrayList<String> getPronostico(String horoscopo, String fecha, String clientName) {
		//Se verifica que la solicitud sea v�lida y se responde con un 
        //pronostico si lo es, o un mensaje de error en caso contrario.

        String servidorCentralStr = "ServidorCentral-" + clientName;
        Log.logInfo(servidorCentralStr, "Se recibe una nueva solicitud de: "
                + "<" + horoscopo + ", " + fecha + ">");
        System.out.println("->ServidorCentral: Se recibe una solicitud nueva de: " + clientName);

        String respuestaHoroscopo, respuestaClima, rtaValidacion,
                claveCache, clave;
        String[] respuestaCache, valor;
        ArrayList<String> respuesta = new ArrayList<>();

        try {
            //Se verfica el formato de la solicitud recibida
            rtaValidacion = validacion(horoscopo, fecha);
            if (rtaValidacion.equals("valida")) {
                //Se consulta de la cache
                claveCache = horoscopo + fecha;
                respuestaCache = cache.get(claveCache);
                Log.logInfo(servidorCentralStr, "La consulta a la cache con la clave: " + claveCache
                        + " respondio: " + (respuestaCache != null
                                ? "Consulta encontrada" : "Consulta no encontrada"));

                if (respuestaCache != null) {
                    //La cache tuvo exito 					
                    if (respuestaCache[0].equals("respuestaEnCurso")) {
                        while (respuestaCache[0].equals("respuestaEnCurso")) {
                            Log.logInfo(servidorCentralStr, "Esperando por respuesta en curso");
                            semaforoCache.acquire();
                            respuestaCache = this.cache.get(claveCache);
                        }
                        semaforoCache.release();
                    }

                    respuesta.add(respuestaCache[0]);
                    respuesta.add(respuestaCache[1]);
                    Log.logInfo(servidorCentralStr, "La cache respondio: "
                            + "<" + respuestaCache[0] + "; " + respuestaCache[1] + ">");
                } else {
                    //Si la cache no tuvo exito se realiza la consulta

                    //Se bloquea a los siguientes hilos para que no busquen una consulta igual a otro
                    semaforoCache.acquire();
                    Log.logInfo(servidorCentralStr, "Realiza una consulta a los Servidores");
                    cache.put(claveCache, new String[]{"respuestaEnCurso"});

                    respuestaHoroscopo = servicioHoroscopo.getHoroscopo(horoscopo, clientName);
                    respuestaClima = servicioClima.getClima(fecha, clientName);
                    //Si ambas consultas fueron validas, responde al cliente y almacena la  
                    //respuesta en cache, caso contrario caso contrario retransmite el error           
                    if (!respuestaHoroscopo.contains("ESH") && !respuestaHoroscopo.contains("PH")) {
                        //Verifica si la respuesta fue de error en el horoscopo
                        if (!respuestaClima.contains("FD") && !respuestaClima.contains("FM")
                                && !respuestaClima.contains("PC") && !respuestaClima.contains("ESC")) {
                            //Verifica si la respuesta fue de error en el clima

                            //La clave se forma concatenando las solicitudes
                            clave = horoscopo + fecha;
                            //El valor se forma con las respuestas correctas de ambos servidores
                            valor = new String[]{respuestaHoroscopo, respuestaClima};
                            Log.logInfo(servidorCentralStr, "Se almacena en cache la clave: " + clave);
                            cache.put(clave, valor);

                            //Se le responde al cliente la solicitud                          
                            respuesta.add(respuestaHoroscopo);
                            respuesta.add(respuestaClima);
                            //Se libera las consultas iguales en espera, debido que la cache ya tiene su respuesta
                            this.semaforoCache.release();
                            Log.logInfo(servidorCentralStr, "Se responde al cliente con: <" + respuestaHoroscopo + "; " + respuestaClima + ">");
                            Log.logInfo(servidorCentralStr, "Se libera las consultas iguales en espera, debido que la cache ya tiene su respuesta");
                        } else {
                            //Se le responde al cliente del error en clima
                            respuesta.add(respuestaClima);
                        }
                    } else {
                        //Se le responde al cliente del error en horoscopo
                        respuesta.add(respuestaHoroscopo);
                    }
                }
            } else {
                Log.logError(servidorCentralStr, "Se responde al Cliente: " + rtaValidacion);
                respuesta.add(rtaValidacion);
            }
        } catch (InterruptedException ex) {
            Log.logError(servidorCentralStr, "Error al esperar por la respuesta: " + ex.getMessage());
            respuesta.add("SC");
            return respuesta;
        } catch (RemoteException ex) {
            Log.logError(servidorCentralStr, "Error con el sistema rmi: " + ex.getMessage());
            respuesta.add("SC");
            return respuesta;
        }
        return respuesta;
	}
	
	private String validacion(String horoscopo, String fecha) {
        //Se valida la consulta recibida, respondiendo "valida" si esta lo es
        String respuesta;

        //Primero se verifica si es valido para el Protocolo Clima y luego para el de Horoscopo
        respuesta = validarFecha(fecha);
        if (respuesta.equals("valida")) {
            if (horoscopo.length() == 2 && protocoloHoroscopo.contains(horoscopo)) {
                respuesta = "valida";
            } else {
                respuesta = "PH"; //Solicitud no valida por protocolo
            }
        }
        return respuesta;
    }
	
	private String validarFecha(String fecha) {
        //Verifica que el parametro recibido cumple el protocolo y es una fecha valida
        //retorna "valida" si lo es o el tipo de error si no
        String respuesta;
        int indexA = fecha.indexOf("-"), indexB = fecha.indexOf("-", indexA + 1),
                dia, mes, anio;

        //Se verifica que la fecha recibida cumpla el formato DD-MM-A... (el a�o puede ser desde 0 en adelante)        
        if (indexA == 2 && indexB == 5 && fecha.length() >= 7) {
            try {
                //Luego se obtiene los valores para cada dia, mes y a�o.
                dia = Integer.parseInt(fecha.substring(0, indexA));
                mes = Integer.parseInt(fecha.substring(indexA + 1, indexB));
                anio = Integer.parseInt(fecha.substring(indexB + 1));
            } catch (NumberFormatException ex) {
                //Un valor no es un numero entero
                return "PC"; //Protocolo Clima
            }
            //Se controla que el dia sea valido
            if (dia >= 1 && dia <= 31) {
                //Si el mes es febrero, se verifica si el a�o es bisiesto, y que el dia sea 
                //menor a 29 dias, caso contrario 28 dias                    
                if (mes == 2) {
                    if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                        if (dia <= 29) {
                            respuesta = "valida";
                        } else {
                            //Error en el dia recibido, la fecha tiene mas dias 
                            //de los que permite febrero bisiesto
                            respuesta = "FD";
                        }
                    } else if (dia <= 28) {
                        
                        
                        //TEST anduvo
                        
                        
                        respuesta = "valida";
                    } else {
                        //Error en el dia recibido, la fecha tiene mas dias de 
                        //los que permite febrero no bisiesto
                        respuesta = "FD";
                    }
                    //Si no es feberero, se verifican para los meses restantes si tiene 30 o 31 dias
                } else if ((mes == 4 || mes == 6 || mes == 7 || mes == 11) && dia <= 30) {
                    respuesta = "valida";
                } else if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
                        || mes == 10 || mes == 12) && dia <= 31) {
                    respuesta = "valida";
                } else {
                    //Error en el mes recibido, la fecha cuenta con un valor de mes invalido
                    respuesta = "FM";
                }
            } else {
                //Error en el dia recibido, la fecha cuenta con m�s de 31 d�as
                respuesta = "FD";
            }

        } else {
            //Wrror en el formato de la fecha recibida, no se respet� el formato
            respuesta = "PC"; //Protocolo Clima
        }
        return respuesta;
    }
	
	

}
