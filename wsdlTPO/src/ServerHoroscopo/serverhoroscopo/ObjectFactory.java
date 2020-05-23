
package serverhoroscopo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the serverhoroscopo package. 
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

    private final static QName _GetHoroscopoResponse_QNAME = new QName("http://ServerHoroscopo/", "getHoroscopoResponse");
    private final static QName _GetHoroscopo_QNAME = new QName("http://ServerHoroscopo/", "getHoroscopo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: serverhoroscopo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHoroscopo }
     * 
     */
    public GetHoroscopo createGetHoroscopo() {
        return new GetHoroscopo();
    }

    /**
     * Create an instance of {@link GetHoroscopoResponse }
     * 
     */
    public GetHoroscopoResponse createGetHoroscopoResponse() {
        return new GetHoroscopoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHoroscopoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerHoroscopo/", name = "getHoroscopoResponse")
    public JAXBElement<GetHoroscopoResponse> createGetHoroscopoResponse(GetHoroscopoResponse value) {
        return new JAXBElement<GetHoroscopoResponse>(_GetHoroscopoResponse_QNAME, GetHoroscopoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHoroscopo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ServerHoroscopo/", name = "getHoroscopo")
    public JAXBElement<GetHoroscopo> createGetHoroscopo(GetHoroscopo value) {
        return new JAXBElement<GetHoroscopo>(_GetHoroscopo_QNAME, GetHoroscopo.class, null, value);
    }

}
