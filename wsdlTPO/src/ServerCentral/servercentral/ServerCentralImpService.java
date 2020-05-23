
package servercentral;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServerCentralImpService", targetNamespace = "http://ServerCentral/", wsdlLocation = "http://localhost:10003/ws/ServicioCentral?wsdl")
public class ServerCentralImpService
    extends Service
{

    private final static URL SERVERCENTRALIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVERCENTRALIMPSERVICE_EXCEPTION;
    private final static QName SERVERCENTRALIMPSERVICE_QNAME = new QName("http://ServerCentral/", "ServerCentralImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:10003/ws/ServicioCentral?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVERCENTRALIMPSERVICE_WSDL_LOCATION = url;
        SERVERCENTRALIMPSERVICE_EXCEPTION = e;
    }

    public ServerCentralImpService() {
        super(__getWsdlLocation(), SERVERCENTRALIMPSERVICE_QNAME);
    }

    public ServerCentralImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVERCENTRALIMPSERVICE_QNAME, features);
    }

    public ServerCentralImpService(URL wsdlLocation) {
        super(wsdlLocation, SERVERCENTRALIMPSERVICE_QNAME);
    }

    public ServerCentralImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVERCENTRALIMPSERVICE_QNAME, features);
    }

    public ServerCentralImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServerCentralImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServerCentral
     */
    @WebEndpoint(name = "ServerCentralImpPort")
    public ServerCentral getServerCentralImpPort() {
        return super.getPort(new QName("http://ServerCentral/", "ServerCentralImpPort"), ServerCentral.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServerCentral
     */
    @WebEndpoint(name = "ServerCentralImpPort")
    public ServerCentral getServerCentralImpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ServerCentral/", "ServerCentralImpPort"), ServerCentral.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVERCENTRALIMPSERVICE_EXCEPTION!= null) {
            throw SERVERCENTRALIMPSERVICE_EXCEPTION;
        }
        return SERVERCENTRALIMPSERVICE_WSDL_LOCATION;
    }

}
