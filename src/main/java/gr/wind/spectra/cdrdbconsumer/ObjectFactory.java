
package gr.wind.spectra.cdrdbconsumer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the gr.wind.cdrdb.web package.
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

	private final static QName _HasOutage_QNAME = new QName("http://web.cdrdb.wind.gr/", "hasOutage");
	private final static QName _InsertCallerData_QNAME = new QName("http://web.cdrdb.wind.gr/", "InsertCallerData");
	private final static QName _InsertCallerDataResponse_QNAME = new QName("http://web.cdrdb.wind.gr/",
			"InsertCallerDataResponse");
	private final static QName _Password_QNAME = new QName("http://web.cdrdb.wind.gr/", "Password");
	private final static QName _Exception_QNAME = new QName("http://web.cdrdb.wind.gr/", "Exception");
	private final static QName _UserName_QNAME = new QName("http://web.cdrdb.wind.gr/", "UserName");
	private final static QName _HasOutageResponse_QNAME = new QName("http://web.cdrdb.wind.gr/", "hasOutageResponse");
	private final static QName _Element_QNAME = new QName("http://web.cdrdb.wind.gr/", "Element");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gr.wind.cdrdb.web
	 * 
	 */
	public ObjectFactory()
	{
	}

	/**
	 * Create an instance of {@link HasOutageResponse }
	 * 
	 */
	public HasOutageResponse createHasOutageResponse()
	{
		return new HasOutageResponse();
	}

	/**
	 * Create an instance of {@link BasicStruct2 }
	 * 
	 */
	public BasicStruct2 createBasicStruct2()
	{
		return new BasicStruct2();
	}

	/**
	 * Create an instance of {@link InsertCallerData }
	 * 
	 */
	public InsertCallerData createInsertCallerData()
	{
		return new InsertCallerData();
	}

	/**
	 * Create an instance of {@link HasOutage }
	 * 
	 */
	public HasOutage createHasOutage()
	{
		return new HasOutage();
	}

	/**
	 * Create an instance of {@link Exception }
	 * 
	 */
	public Exception createException()
	{
		return new Exception();
	}

	/**
	 * Create an instance of {@link InsertCallerDataResponse }
	 * 
	 */
	public InsertCallerDataResponse createInsertCallerDataResponse()
	{
		return new InsertCallerDataResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link HasOutage }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "hasOutage")
	public JAXBElement<HasOutage> createHasOutage(HasOutage value)
	{
		return new JAXBElement<HasOutage>(_HasOutage_QNAME, HasOutage.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link InsertCallerData }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "InsertCallerData")
	public JAXBElement<InsertCallerData> createInsertCallerData(InsertCallerData value)
	{
		return new JAXBElement<InsertCallerData>(_InsertCallerData_QNAME, InsertCallerData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link InsertCallerDataResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "InsertCallerDataResponse")
	public JAXBElement<InsertCallerDataResponse> createInsertCallerDataResponse(InsertCallerDataResponse value)
	{
		return new JAXBElement<InsertCallerDataResponse>(_InsertCallerDataResponse_QNAME,
				InsertCallerDataResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "Password")
	public JAXBElement<String> createPassword(String value)
	{
		return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "Exception")
	public JAXBElement<Exception> createException(Exception value)
	{
		return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "UserName")
	public JAXBElement<String> createUserName(String value)
	{
		return new JAXBElement<String>(_UserName_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link HasOutageResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "hasOutageResponse")
	public JAXBElement<HasOutageResponse> createHasOutageResponse(HasOutageResponse value)
	{
		return new JAXBElement<HasOutageResponse>(_HasOutageResponse_QNAME, HasOutageResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BasicStruct2 }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.cdrdb.wind.gr/", name = "Element")
	public JAXBElement<BasicStruct2> createElement(BasicStruct2 value)
	{
		return new JAXBElement<BasicStruct2>(_Element_QNAME, BasicStruct2.class, null, value);
	}

}
