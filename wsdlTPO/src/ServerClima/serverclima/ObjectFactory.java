
package serverclima;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the serverclima package. 
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

    private final static QName _GetClima_QNAME = new QName("http://ServerClima/", "getClima");
    private final static QName _GetClimaResponse_QNAME = new QName("http://ServerClima/", "getClimaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: serverclima
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClimaResponse }
     * 
     */
    public GetClimaResponse createGetClimaResponse() {
        return new GetClimaResponse();
    }

    /**
     * Create an instance of {@link GetClima }
     * 
     */
    public GetClima createGetClima() {
        return new GetClima();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClima }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerClima/", name = "getClima")
    public JAXBElement<GetClima> createGetClima(GetClima value) {
        return new JAXBElement<GetClima>(_GetClima_QNAME, GetClima.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClimaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerClima/", name = "getClimaResponse")
    public JAXBElement<GetClimaResponse> createGetClimaResponse(GetClimaResponse value) {
        return new JAXBElement<GetClimaResponse>(_GetClimaResponse_QNAME, GetClimaResponse.class, null, value);
    }

}
