package ServerClima;

import Log.Log;
import java.io.IOException;
import javax.xml.ws.Endpoint;

public class PublicadorServerClima {

    private static String ipAdress = "127.0.0.1"; //La ip se define local por defecto
    private static int port;

    public static void main(String[] args) {
        //Verificar los parametros recibidos
        if (args.length != 1) {
            System.err.println("El parametro es incorrecto, ingrese el puerto donde se conectara");
            return;
        } else {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Puerto ingresado no valido");
                return;
            }
        }

        //Iniciar el Log
        try {
            Log.startLog("LogClima.txt");
        } catch (SecurityException | IOException ex) {
            System.err.println("->ServerClima: No se pudo iniciar el Log");
        }

        //Publicar el ServidorClima
        Endpoint.publish("http://" + ipAdress + ":" + port + "/ws/Clima", new ServerClimaImp());
        Log.logInfo("ServidorClima",  "Publicado");
    }
}
