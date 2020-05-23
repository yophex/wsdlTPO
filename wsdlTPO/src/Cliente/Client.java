package Cliente;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ServerCentral.ServerCentral;
import ServerHoroscopo.ServerHoroscopo;
import ServerClima.ServerClima;
 
public class Client{
 	public static void main(String[] args) throws Exception {
 		String ipAdress="127.0.0.1";
 		int portH=10000,portC=10001,portCe=10003;
 		
 		// Para probar solo con clima y horosocpo
 		
 		URL urlH = new URL("http://"+ipAdress+":"+portH+"/ws/Horoscopo?wsdl");
 		//URL urlC = new URL("http://"+ipAdress+":"+portC+"/ws/Clima?wsdl");
 		
 		
 		
        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qnameH = new QName("http://ServerHoroscopo/", "ServerHoroscopoImpService");
        //QName qnameC= new QName("http://ServerClima/", "ServerClimaImpService");
 
        Service serviceH = Service.create(urlH, qnameH);
        ServerHoroscopo horoscopo = serviceH.getPort(ServerHoroscopo.class);

        /*Service serviceC = Service.create(urlC, qnameC);
        ServerClima clima = serviceC.getPort(ServerClima.class);*/
        
    
 		
 		
        //Para probar con el central
        /*URL urlCentral = new URL("http://"+ipAdress+":"+portCe+"/ws/ServicioCentral?wsdl");
        
        QName qnameCe= new QName("http://ServerCentral/", "ServerCentralImpService");
        
        Service serviceCentral = Service.create(urlCentral, qnameCe);
        ServerCentral central = serviceCentral.getPort(ServerCentral.class);*/
        
      System.out.println(horoscopo.getHoroscopo("AR", "El cliente numero 1"));
      //System.out.println(clima.getClima("05-05-2020", "El cliente numero 1"));
        /*ArrayList<String> respuesta=central.getPronostico("AR", "05-05-2020", "El cliente numero 1");
        System.out.println("El clima es "+respuesta.get(1)+ " y el horoscopo es "+respuesta.get(0));*/
        
    }
 
}


