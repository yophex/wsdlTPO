package ServerHoroscopo;

import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ServerHoroscopo {

	@WebMethod public String getHoroscopo(String solicitud, String clientName)  throws MalformedURLException;

}
