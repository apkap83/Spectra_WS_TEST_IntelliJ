
package gr.wind.spectra.consumer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gr.wind.spectra.consumer package. 
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

    private final static QName _GetOutageStatus_QNAME = new QName("http://web.spectra.wind.gr/", "getOutageStatus");
    private final static QName _CloseOutageResponse_QNAME = new QName("http://web.spectra.wind.gr/", "closeOutageResponse");
    private final static QName _Exception_QNAME = new QName("http://web.spectra.wind.gr/", "Exception");
    private final static QName _InvalidInputException_QNAME = new QName("http://web.spectra.wind.gr/", "InvalidInputException");
    private final static QName _NLUActiveResponse_QNAME = new QName("http://web.spectra.wind.gr/", "NLU_ActiveResponse");
    private final static QName _UserName_QNAME = new QName("http://web.spectra.wind.gr/", "UserName");
    private final static QName _CloseOutage_QNAME = new QName("http://web.spectra.wind.gr/", "closeOutage");
    private final static QName _ModifyOutage_QNAME = new QName("http://web.spectra.wind.gr/", "modifyOutage");
    private final static QName _Element_QNAME = new QName("http://web.spectra.wind.gr/", "Element");
    private final static QName _GetOutageStatusResponse_QNAME = new QName("http://web.spectra.wind.gr/", "getOutageStatusResponse");
    private final static QName _Password_QNAME = new QName("http://web.spectra.wind.gr/", "Password");
    private final static QName _GetHierarchy_QNAME = new QName("http://web.spectra.wind.gr/", "getHierarchy");
    private final static QName _SubmitOutageResponse_QNAME = new QName("http://web.spectra.wind.gr/", "submitOutageResponse");
    private final static QName _SubmitOutage_QNAME = new QName("http://web.spectra.wind.gr/", "submitOutage");
    private final static QName _GetHierarchyResponse_QNAME = new QName("http://web.spectra.wind.gr/", "getHierarchyResponse");
    private final static QName _ModifyOutageResponse_QNAME = new QName("http://web.spectra.wind.gr/", "modifyOutageResponse");
    private final static QName _NLUActive_QNAME = new QName("http://web.spectra.wind.gr/", "NLU_Active");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gr.wind.spectra.consumer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NLUActiveResponse }
     * 
     */
    public NLUActiveResponse createNLUActiveResponse() {
        return new NLUActiveResponse();
    }

    /**
     * Create an instance of {@link InvalidInputException }
     * 
     */
    public InvalidInputException createInvalidInputException() {
        return new InvalidInputException();
    }

    /**
     * Create an instance of {@link CloseOutage }
     * 
     */
    public CloseOutage createCloseOutage() {
        return new CloseOutage();
    }

    /**
     * Create an instance of {@link ModifyOutage }
     * 
     */
    public ModifyOutage createModifyOutage() {
        return new ModifyOutage();
    }

    /**
     * Create an instance of {@link GetOutageStatus }
     * 
     */
    public GetOutageStatus createGetOutageStatus() {
        return new GetOutageStatus();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CloseOutageResponse }
     * 
     */
    public CloseOutageResponse createCloseOutageResponse() {
        return new CloseOutageResponse();
    }

    /**
     * Create an instance of {@link ModifyOutageResponse }
     * 
     */
    public ModifyOutageResponse createModifyOutageResponse() {
        return new ModifyOutageResponse();
    }

    /**
     * Create an instance of {@link GetHierarchyResponse }
     * 
     */
    public GetHierarchyResponse createGetHierarchyResponse() {
        return new GetHierarchyResponse();
    }

    /**
     * Create an instance of {@link NLUActive }
     * 
     */
    public NLUActive createNLUActive() {
        return new NLUActive();
    }

    /**
     * Create an instance of {@link GetOutageStatusResponse }
     * 
     */
    public GetOutageStatusResponse createGetOutageStatusResponse() {
        return new GetOutageStatusResponse();
    }

    /**
     * Create an instance of {@link SubmitOutage }
     * 
     */
    public SubmitOutage createSubmitOutage() {
        return new SubmitOutage();
    }

    /**
     * Create an instance of {@link GetHierarchy }
     * 
     */
    public GetHierarchy createGetHierarchy() {
        return new GetHierarchy();
    }

    /**
     * Create an instance of {@link SubmitOutageResponse }
     * 
     */
    public SubmitOutageResponse createSubmitOutageResponse() {
        return new SubmitOutageResponse();
    }

    /**
     * Create an instance of {@link BasicStruct2 }
     * 
     */
    public BasicStruct2 createBasicStruct2() {
        return new BasicStruct2();
    }

    /**
     * Create an instance of {@link BasicStruct3 }
     * 
     */
    public BasicStruct3 createBasicStruct3() {
        return new BasicStruct3();
    }

    /**
     * Create an instance of {@link BasicStruct6 }
     * 
     */
    public BasicStruct6 createBasicStruct6() {
        return new BasicStruct6();
    }

    /**
     * Create an instance of {@link BasicStruct5 }
     * 
     */
    public BasicStruct5 createBasicStruct5() {
        return new BasicStruct5();
    }

    /**
     * Create an instance of {@link BasicStruct8 }
     * 
     */
    public BasicStruct8 createBasicStruct8() {
        return new BasicStruct8();
    }

    /**
     * Create an instance of {@link BasicStruct }
     * 
     */
    public BasicStruct createBasicStruct() {
        return new BasicStruct();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOutageStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getOutageStatus")
    public JAXBElement<GetOutageStatus> createGetOutageStatus(GetOutageStatus value) {
        return new JAXBElement<GetOutageStatus>(_GetOutageStatus_QNAME, GetOutageStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloseOutageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "closeOutageResponse")
    public JAXBElement<CloseOutageResponse> createCloseOutageResponse(CloseOutageResponse value) {
        return new JAXBElement<CloseOutageResponse>(_CloseOutageResponse_QNAME, CloseOutageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidInputException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "InvalidInputException")
    public JAXBElement<InvalidInputException> createInvalidInputException(InvalidInputException value) {
        return new JAXBElement<InvalidInputException>(_InvalidInputException_QNAME, InvalidInputException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NLUActiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "NLU_ActiveResponse")
    public JAXBElement<NLUActiveResponse> createNLUActiveResponse(NLUActiveResponse value) {
        return new JAXBElement<NLUActiveResponse>(_NLUActiveResponse_QNAME, NLUActiveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "UserName")
    public JAXBElement<String> createUserName(String value) {
        return new JAXBElement<String>(_UserName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloseOutage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "closeOutage")
    public JAXBElement<CloseOutage> createCloseOutage(CloseOutage value) {
        return new JAXBElement<CloseOutage>(_CloseOutage_QNAME, CloseOutage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOutage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "modifyOutage")
    public JAXBElement<ModifyOutage> createModifyOutage(ModifyOutage value) {
        return new JAXBElement<ModifyOutage>(_ModifyOutage_QNAME, ModifyOutage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "Element")
    public JAXBElement<Object> createElement(Object value) {
        return new JAXBElement<Object>(_Element_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOutageStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getOutageStatusResponse")
    public JAXBElement<GetOutageStatusResponse> createGetOutageStatusResponse(GetOutageStatusResponse value) {
        return new JAXBElement<GetOutageStatusResponse>(_GetOutageStatusResponse_QNAME, GetOutageStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "Password")
    public JAXBElement<String> createPassword(String value) {
        return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHierarchy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getHierarchy")
    public JAXBElement<GetHierarchy> createGetHierarchy(GetHierarchy value) {
        return new JAXBElement<GetHierarchy>(_GetHierarchy_QNAME, GetHierarchy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitOutageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "submitOutageResponse")
    public JAXBElement<SubmitOutageResponse> createSubmitOutageResponse(SubmitOutageResponse value) {
        return new JAXBElement<SubmitOutageResponse>(_SubmitOutageResponse_QNAME, SubmitOutageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitOutage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "submitOutage")
    public JAXBElement<SubmitOutage> createSubmitOutage(SubmitOutage value) {
        return new JAXBElement<SubmitOutage>(_SubmitOutage_QNAME, SubmitOutage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHierarchyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getHierarchyResponse")
    public JAXBElement<GetHierarchyResponse> createGetHierarchyResponse(GetHierarchyResponse value) {
        return new JAXBElement<GetHierarchyResponse>(_GetHierarchyResponse_QNAME, GetHierarchyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOutageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "modifyOutageResponse")
    public JAXBElement<ModifyOutageResponse> createModifyOutageResponse(ModifyOutageResponse value) {
        return new JAXBElement<ModifyOutageResponse>(_ModifyOutageResponse_QNAME, ModifyOutageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NLUActive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "NLU_Active")
    public JAXBElement<NLUActive> createNLUActive(NLUActive value) {
        return new JAXBElement<NLUActive>(_NLUActive_QNAME, NLUActive.class, null, value);
    }

}
