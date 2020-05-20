package ServerCentral;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.ws.Endpoint;

import Log.Log;

public class ServerCentralPublisher {

	private static String ipAdress = "127.0.0.1"; // La ip se define local por defecto
	private static int port;

	public static void main(String[] args) {
		String ipHoroscopo, ipClima;
		int portHoroscopo, portClima;
		if (args.length != 5) {
			System.err.println("Los parametros recibidos son incorrectos se requiere:"
					+ "\nDireccion ip y puerto del servidor horoscopo, " + "\nDireccion ip y puerto del servidor clima,"
					+ "\nPuerto donde se ejecutara el servidor central");
			return;
		} else {
			ipHoroscopo = args[0];
			ipClima = args[2];
			try {
				port = Integer.parseInt(args[4]);
				portHoroscopo = Integer.parseInt(args[1]);
				portClima = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				System.err.println("Puerto ingresado no valido");
				return;
			}
		}

		// Iniciar el Log
		try {
			Log.startLog("LogCentral.txt");
		} catch (SecurityException | IOException ex) {
			System.out.println("->ServerCentral: No se pudo iniciar el Log");
		}

		try {
			ServerCentral serverCentral = new ServerCentralImp(ipHoroscopo, portHoroscopo, ipClima, portClima);
			Endpoint.publish("http://" + ipAdress + ":" + port + "/ws/ServicioCentral", serverCentral); //////////publicacion

			// Naming.rebind("rmi://" + ipAdress + ":" + port + "/ServerCentral",
			// serverCentral);

			Log.logInfo("ServidorCentral", "Publicado");
			System.out.println("->ServidorCentral: Publicado");
		} catch (MalformedURLException e) {
			Log.logError("ServidorCentral", "Error en la URL de rmi - " + e.getMessage());
			System.err.println("->ServidorCentral: Error en la URL de rmi: " + e.getMessage());
			System.exit(1);
		}
	}

}
