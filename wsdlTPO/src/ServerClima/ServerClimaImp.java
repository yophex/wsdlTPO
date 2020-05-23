package ServerClima;

import java.util.Random;

import javax.jws.WebService;
//Service Implementation
@WebService(endpointInterface = "ServerClima.ServerClima")



public class ServerClimaImp implements ServerClima{
	

	private String[] pronostico;    

    public ServerClimaImp(){
        super();

        //Log.logInfo("ServidorClima", "Se crea una nueva instancia con id: " + this.ref);
        System.out.println("->ServidorClima: Se crea una nueva instancia");
        this.pronostico = new String[]{"Lluvias Aisladas", "Lluvias Intensas", "Despejado", "Tormentas",
            "Nublado", "Viento", "Chaparrones", "Ciclon", "Rafagas Fuertes", "Relampagos"};
    }
    
    @Override
    public String getClima(String fecha, String clientName){
        //Se verifica que la solicitud sea v�lida y se responde con un 
        //pronostico si lo es, o un mensaje de error en caso contrario
        String servidorClimaStr = "ServidorClima-" + clientName;
        //Log.logInfo(servidorClimaStr, "Se solicita un pronostico del clima para la fecha: " + fecha);
        System.out.println("->ServidorClima: Se solicita un horoscopo");
        String respuesta, rtaValidacion;
        Random aleatorio = new Random();
        int indiceRandom, dia, mes, anio,
                indexA = fecha.indexOf("-"), indexB = fecha.indexOf("-", indexA + 1);

        rtaValidacion = validarFecha(fecha);
        if (rtaValidacion.equals("valida")) {
        //    Log.logInfo(servidorClimaStr, "Solicitud valida");
            dia = Integer.parseInt(fecha.substring(0, indexA));
            mes = Integer.parseInt(fecha.substring(indexA + 1, indexB));
            anio = Integer.parseInt(fecha.substring(indexB + 1));
            synchronized (this) {
                //Se obtiene una predicci�n aleatoria y se simula su procesamiento (tiempo de espera 1 seg)
                try {
                    this.wait(1000);
                } catch (InterruptedException ex) {
        //            Log.logError(servidorClimaStr, "Error en el procesamiento del pronostico: " + ex.getMessage());
                    System.err.println("->ServidorClima: Error en el procesamiento del pronostico");
                    return "ESC";
                }
                indiceRandom = (dia + mes + anio + aleatorio.nextInt(1000)) % this.pronostico.length;
            }
            respuesta = this.pronostico[indiceRandom];
        } else {
            //Solicitud no valida por el protocolo
        //    Log.logError(servidorClimaStr, "Solicitud invalida");
            respuesta = rtaValidacion;
        }
       // Log.logInfo(servidorClimaStr, "Se responde al Cliente: " + respuesta);
        System.out.println("->ServidorClima: Se responde a una solicitud");
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
