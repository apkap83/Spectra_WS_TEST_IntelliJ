
package gr.wind.spectra.cdrdbconsumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for hasOutageResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="hasOutageResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://web.cdrdb.wind.gr/}basicStruct2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hasOutageResponse", propOrder = { "result" })
public class HasOutageResponse
{

	@XmlElement(name = "Result")
	protected BasicStruct2 result;

	/**
	 * Gets the value of the result property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BasicStruct2 }
	 *     
	 */
	public BasicStruct2 getResult()
	{
		return result;
	}

	/**
	 * Sets the value of the result property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link BasicStruct2 }
	 *     
	 */
	public void setResult(BasicStruct2 value)
	{
		this.result = value;
	}

}
