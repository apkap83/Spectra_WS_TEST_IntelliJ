
package gr.wind.spectra.cdrdbconsumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for hasOutage complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="hasOutage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AAA_Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cli" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DSLAM_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CO_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApiProcess" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hasOutage", propOrder = { "requestID", "aaaUsername", "cli", "dslamName", "coid", "apiProcess" })
public class HasOutage
{

	@XmlElement(name = "RequestID", required = true)
	protected String requestID;
	@XmlElement(name = "AAA_Username")
	protected String aaaUsername;
	@XmlElement(name = "Cli", required = true)
	protected String cli;
	@XmlElement(name = "DSLAM_name", required = true)
	protected String dslamName;
	@XmlElement(name = "CO_ID")
	protected String coid;
	@XmlElement(name = "ApiProcess")
	protected String apiProcess;

	/**
	 * Gets the value of the requestID property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getRequestID()
	{
		return requestID;
	}

	/**
	 * Sets the value of the requestID property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setRequestID(String value)
	{
		this.requestID = value;
	}

	/**
	 * Gets the value of the aaaUsername property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAAAUsername()
	{
		return aaaUsername;
	}

	/**
	 * Sets the value of the aaaUsername property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAAAUsername(String value)
	{
		this.aaaUsername = value;
	}

	/**
	 * Gets the value of the cli property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCli()
	{
		return cli;
	}

	/**
	 * Sets the value of the cli property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCli(String value)
	{
		this.cli = value;
	}

	/**
	 * Gets the value of the dslamName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDSLAMName()
	{
		return dslamName;
	}

	/**
	 * Sets the value of the dslamName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDSLAMName(String value)
	{
		this.dslamName = value;
	}

	/**
	 * Gets the value of the coid property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCOID()
	{
		return coid;
	}

	/**
	 * Sets the value of the coid property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCOID(String value)
	{
		this.coid = value;
	}

	/**
	 * Gets the value of the apiProcess property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getApiProcess()
	{
		return apiProcess;
	}

	/**
	 * Sets the value of the apiProcess property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setApiProcess(String value)
	{
		this.apiProcess = value;
	}

}
