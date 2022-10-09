
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NLU_ActiveResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NLU_ActiveResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://web.spectra.wind.gr/}basicStruct8" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NLU_ActiveResponse", propOrder = {
    "result"
})
public class NLUActiveResponse {

    @XmlElement(name = "Result")
    protected BasicStruct8 result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link BasicStruct8 }
     *     
     */
    public BasicStruct8 getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicStruct8 }
     *     
     */
    public void setResult(BasicStruct8 value) {
        this.result = value;
    }

}
