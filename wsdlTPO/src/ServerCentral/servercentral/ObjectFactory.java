
package servercentral;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servercentral package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPronostico_QNAME = new QName("http://ServerCentral/", "getPronostico");
    private final static QName _GetPronosticoResponse_QNAME = new QName("http://ServerCentral/", "getPronosticoResponse");
    private final static QName _MalformedURLException_QNAME = new QName("http://ServerCentral/", "MalformedURLException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servercentral
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MalformedURLException }
     * 
     */
    public MalformedURLException createMalformedURLException() {
        return new MalformedURLException();
    }

    /**
     * Create an instance of {@link GetPronostico }
     * 
     */
    public GetPronostico createGetPronostico() {
        return new GetPronostico();
    }

    /**
     * Create an instance of {@link GetPronosticoResponse }
     * 
     */
    public GetPronosticoResponse createGetPronosticoResponse() {
        return new GetPronosticoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPronostico }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerCentral/", name = "getPronostico")
    public JAXBElement<GetPronostico> createGetPronostico(GetPronostico value) {
        return new JAXBElement<GetPronostico>(_GetPronostico_QNAME, GetPronostico.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPronosticoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerCentral/", name = "getPronosticoResponse")
    public JAXBElement<GetPronosticoResponse> createGetPronosticoResponse(GetPronosticoResponse value) {
        return new JAXBElement<GetPronosticoResponse>(_GetPronosticoResponse_QNAME, GetPronosticoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MalformedURLException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerCentral/", name = "MalformedURLException")
    public JAXBElement<MalformedURLException> createMalformedURLException(MalformedURLException value) {
        return new JAXBElement<MalformedURLException>(_MalformedURLException_QNAME, MalformedURLException.class, null, value);
    }

}
