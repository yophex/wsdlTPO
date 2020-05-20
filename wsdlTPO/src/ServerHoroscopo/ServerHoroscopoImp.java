package ServerHoroscopo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "ServerHoroscopo.ServerHoroscopo")
public class ServerHoroscopoImp implements ServerHoroscopo {
	
	
	private ArrayList<String> protocoloHoroscopo;
	private static String[] pronostico = {"Para sanar sus heridas se debera cumplir un reto que se le presentara al medio dia",
	        "Se debera cuidar de sus amigos, pues ellos podran revelarle algo inesperado",
	        "Las relaciones con sus pares seran muy llenadoras, aprovechelas porque no se le presentara por un tiempo",
	        "Arriesguese a lo que desea, hoy es un dia para hacerle frente a su futuro",
	        "Aproveche el tiempo libre para hacer ejercicio, su cuerpo y alma se lo agradecera",
	        "Aveces las relaciones son toxicas, mire a su alrededor y observe como se comportan los demas con su compasion",
	        "La luna muestra su nueva cara, todo indica que algo nuevo va a ocurrir en su contexto y le cambiara la vida para siempre",
	        "Momentos para trabajar duro en sus proyectos y metas, no deje que nadie le diga que no puede hacer algo",
	        "Aprovecha para darte atencion a las peque�as cosas que aportan a que tu vida se lleve a cabo con serenidad",
	        "No hagas caso a la gente que habla mal de tu persona, sigue tus instintos, ellos te llevaran muy lejos"};
	public ServerHoroscopoImp(){
        super();

        Log.logInfo("ServidorHoroscopo", "Se crea una nueva instancia con id: " );
        System.out.println("->ServidorHoroscopo: Se crea una nueva instancia");
        this.protocoloHoroscopo = new ArrayList<>();
        this.protocoloHoroscopo.addAll(Arrays.asList(
        new String[]{"AR", "TA", "GE", "CC", "LE", "VG", "LB", "ES", "SA", "CP", "AC", "PI"}));
    }
	@Override
    public String getHoroscopo(String horoscopo, String clientName){
        //Se verifica que la horoscopo sea v�lida y se responde con un 
        //pronostico si lo es, o un mensaje de error en caso contrario
        String servidorHoroscopoStr = "ServidorHoroscopo-" + clientName;
        Log.logInfo(servidorHoroscopoStr, "Se solicita un horoscopo para el signo: " + horoscopo);
        System.out.println("->ServidorHoroscopo: Se solicita un horoscopo");
        String respuesta;
        Random aleatorio = new Random();

        if (horoscopo.length() == 2 && protocoloHoroscopo.contains(horoscopo)) {
            Log.logInfo(servidorHoroscopoStr, "Solicitud valida");
            synchronized (this) {
                //Se obtiene una predicci�n aleatoria y se simula su procesamiento (tiempo de espera 1 seg)
                try {
                    this.wait(1000);
                } catch (InterruptedException ex) {
                    Log.logError(servidorHoroscopoStr, "Error en el procesamiento del pronostico: " + ex.getMessage());
                    System.err.println("->ServidorHoroscopo: Error en el procesamiento del pronostico");
                    return "ESH";
                }
                respuesta = pronostico[aleatorio.nextInt(pronostico.length)];
            }
        } else {
            Log.logError(servidorHoroscopoStr, "Solicitud invalida");
            respuesta = "PH"; //Solicitud no valida por el protocolo
        }
        Log.logInfo(servidorHoroscopoStr, "Se responde al Cliente: " + respuesta);
        System.out.println("->ServidorHoroscopo: Se responde a una horoscopo");
        return respuesta;
    }
	
	
	
}
