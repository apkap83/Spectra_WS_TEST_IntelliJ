
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyOutageResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyOutageResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://web.spectra.wind.gr/}basicStruct5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyOutageResponse", propOrder = {
    "result"
})
public class ModifyOutageResponse {

    @XmlElement(name = "Result")
    protected BasicStruct5 result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link BasicStruct5 }
     *     
     */
    public BasicStruct5 getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicStruct5 }
     *     
     */
    public void setResult(BasicStruct5 value) {
        this.result = value;
    }

}
