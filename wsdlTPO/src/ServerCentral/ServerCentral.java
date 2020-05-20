package ServerCentral;

import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ServerCentral {

	@WebMethod public ArrayList<String> getPronostico(String horoscopo, String fecha, String clientName) throws MalformedURLException;

}