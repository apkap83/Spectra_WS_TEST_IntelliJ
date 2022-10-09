
package gr.wind.spectra.cdrdbconsumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for basicStruct2 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="basicStruct2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasOutage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorOccurred" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicStruct2", propOrder = { "requestId", "hasOutage", "errorOccurred", "errorMessage" })
public class BasicStruct2
{

	protected String requestId;
	protected String hasOutage;
	protected boolean errorOccurred;
	protected String errorMessage;

	/**
	 * Gets the value of the requestId property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getRequestId()
	{
		return requestId;
	}

	/**
	 * Sets the value of the requestId property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setRequestId(String value)
	{
		this.requestId = value;
	}

	/**
	 * Gets the value of the hasOutage property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getHasOutage()
	{
		return hasOutage;
	}

	/**
	 * Sets the value of the hasOutage property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setHasOutage(String value)
	{
		this.hasOutage = value;
	}

	/**
	 * Gets the value of the errorOccurred property.
	 * 
	 */
	public boolean isErrorOccurred()
	{
		return errorOccurred;
	}

	/**
	 * Sets the value of the errorOccurred property.
	 * 
	 */
	public void setErrorOccurred(boolean value)
	{
		this.errorOccurred = value;
	}

	/**
	 * Gets the value of the errorMessage property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * Sets the value of the errorMessage property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setErrorMessage(String value)
	{
		this.errorMessage = value;
	}

}
