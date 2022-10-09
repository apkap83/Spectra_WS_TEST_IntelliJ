
package gr.wind.spectra.consumer.oneway;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the gr.wind.spectra.web package.
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
public class ObjectFactory
{

	private final static QName _GetOutageStatus_QNAME = new QName("http://web.spectra.wind.gr/", "getOutageStatus");
	private final static QName _Password_QNAME = new QName("http://web.spectra.wind.gr/", "Password");
	private final static QName _GetHierarchy_QNAME = new QName("http://web.spectra.wind.gr/", "getHierarchy");
	private final static QName _SubmitOutage_QNAME = new QName("http://web.spectra.wind.gr/", "submitOutage");
	private final static QName _UserName_QNAME = new QName("http://web.spectra.wind.gr/", "UserName");
	private final static QName _CloseOutage_QNAME = new QName("http://web.spectra.wind.gr/", "closeOutage");
	private final static QName _ModifyOutage_QNAME = new QName("http://web.spectra.wind.gr/", "modifyOutage");
	private final static QName _NLUActive_QNAME = new QName("http://web.spectra.wind.gr/", "NLU_Active");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gr.wind.spectra.web
	 * 
	 */
	public ObjectFactory()
	{
	}

	/**
	 * Create an instance of {@link CloseOutage }
	 * 
	 */
	public CloseOutage createCloseOutage()
	{
		return new CloseOutage();
	}

	/**
	 * Create an instance of {@link ModifyOutage }
	 * 
	 */
	public ModifyOutage createModifyOutage()
	{
		return new ModifyOutage();
	}

	/**
	 * Create an instance of {@link NLUActive }
	 * 
	 */
	public NLUActive createNLUActive()
	{
		return new NLUActive();
	}

	/**
	 * Create an instance of {@link GetOutageStatus }
	 * 
	 */
	public GetOutageStatus createGetOutageStatus()
	{
		return new GetOutageStatus();
	}

	/**
	 * Create an instance of {@link SubmitOutage }
	 * 
	 */
	public SubmitOutage createSubmitOutage()
	{
		return new SubmitOutage();
	}

	/**
	 * Create an instance of {@link GetHierarchy }
	 * 
	 */
	public GetHierarchy createGetHierarchy()
	{
		return new GetHierarchy();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetOutageStatus }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getOutageStatus")
	public JAXBElement<GetOutageStatus> createGetOutageStatus(GetOutageStatus value)
	{
		return new JAXBElement<GetOutageStatus>(_GetOutageStatus_QNAME, GetOutageStatus.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "Password")
	public JAXBElement<String> createPassword(String value)
	{
		return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetHierarchy }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "getHierarchy")
	public JAXBElement<GetHierarchy> createGetHierarchy(GetHierarchy value)
	{
		return new JAXBElement<GetHierarchy>(_GetHierarchy_QNAME, GetHierarchy.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SubmitOutage }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "submitOutage")
	public JAXBElement<SubmitOutage> createSubmitOutage(SubmitOutage value)
	{
		return new JAXBElement<SubmitOutage>(_SubmitOutage_QNAME, SubmitOutage.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "UserName")
	public JAXBElement<String> createUserName(String value)
	{
		return new JAXBElement<String>(_UserName_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CloseOutage }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "closeOutage")
	public JAXBElement<CloseOutage> createCloseOutage(CloseOutage value)
	{
		return new JAXBElement<CloseOutage>(_CloseOutage_QNAME, CloseOutage.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOutage }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "modifyOutage")
	public JAXBElement<ModifyOutage> createModifyOutage(ModifyOutage value)
	{
		return new JAXBElement<ModifyOutage>(_ModifyOutage_QNAME, ModifyOutage.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link NLUActive }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.spectra.wind.gr/", name = "NLU_Active")
	public JAXBElement<NLUActive> createNLUActive(NLUActive value)
	{
		return new JAXBElement<NLUActive>(_NLUActive_QNAME, NLUActive.class, null, value);
	}

}
