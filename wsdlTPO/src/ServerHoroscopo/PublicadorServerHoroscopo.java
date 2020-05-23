package ServerHoroscopo;

import Log.Log;
import java.io.IOException;
import javax.xml.ws.Endpoint;

public class PublicadorServerHoroscopo {

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
            Log.startLog("LogHoroscopo.txt");
        } catch (SecurityException | IOException ex) {
            System.err.println("->ServerHoroscopo: No se pudo iniciar el Log");
        }

        //Publicar el ServidorHoroscopo 
        Endpoint.publish("http://" + ipAdress + ":" + port + "/ws/Horoscopo", new ServerHoroscopoImp());
        Log.logInfo("ServidorHoroscopo",  "Publicado");
    }
}
