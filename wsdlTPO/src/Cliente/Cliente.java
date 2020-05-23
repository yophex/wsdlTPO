package Cliente;

import Log.Log;
import ServerCentral.ServerCentral;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Cliente implements Runnable {

    private String name;
    private String ipAdress;
    private int port;
    private ServerCentral serverCentral;
    private String horoscopo;
    private String fecha;

    public Cliente(String name, String horoscopo, String fecha, String ipAdress, int port) {
        this.name = name;
        this.horoscopo = horoscopo;
        this.fecha = fecha;
        this.ipAdress = ipAdress;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            //Conectar al ServerCentral mediante rmi
            URL urlCentral = new URL("http://" + ipAdress + ":" + port + "/ws/ServicioCentral?wsdl");
            QName qnameCe = new QName("http://ServerCentral/", "ServerCentralImpService");
            serverCentral = Service.create(urlCentral, qnameCe).getPort(ServerCentral.class);
            Log.logInfo(name, "Se conecto al Servidor Central");

            //Solicitar un pronostico
            ArrayList<String> respuestas;
            Log.logInfo(name, "Solicita un pronostico con la tupla <" + horoscopo + ", " + fecha + ">");
            respuestas = serverCentral.getPronostico(horoscopo, fecha, name);

            //Procesar la respuesta
            if (respuestas.size() == 1) {
                mostrarError(respuestas.get(0));
            } else if (respuestas.isEmpty() || respuestas.size() > 2) {
                mostrarError("SC");
            } else {
                Log.logInfo(name, "recibio: \n"
                        + "----Pronostico Horoscopo: " + respuestas.get(0)
                        + "\n----Pronostico Clima: " + respuestas.get(1));
                System.out.println("->" + name + " recibio: \n"
                        + "----Pronostico Horoscopo: " + respuestas.get(0)
                        + "\n----Pronostico Clima: " + respuestas.get(1));
            }
        } catch (MalformedURLException ex) {
            Log.logError(name, ex.getMessage());
            System.err.println("->" + name + ": ERROR: " + ex.getMessage());
            System.exit(1);
        }
    }

    private void mostrarError(String error) {
        String msg = "ERROR: DESCONOCIDO";
        switch (error) {
            case "ESH":
                msg = "ERROR: ERROR POR PARTE DEL SERVIDOR DE HOROSCOPOS";
                break;
            case "PH":
                msg = "ERROR: ERROR EN EL PROTOCOLO DEL HOROSCOPO";
                break;
            case "ESC":
                msg = "ERROR: ERROR POR PARTE DEL SERVIDOR DEL CLIMA";
                break;
            case "FD":
                msg = "ERROR: DIA NO VALIDO";
                break;
            case "PC":
                msg = "ERROR: FECHA NO VALIDA";
                break;
            case "FM":
                msg = "ERROR: MES NO VALIDO";
                break;
            case "SC":
                msg = "ERROR: ERROR DEL SERVIDOR CENTRAL";
                break;
        }

        System.err.println("->" + name + ": " + msg);
        Log.logError(name, msg);

    }
}
