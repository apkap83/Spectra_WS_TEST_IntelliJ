
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for closeOutageResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="closeOutageResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://web.spectra.wind.gr/}basicStruct6" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "closeOutageResponse", propOrder = {
    "result"
})
public class CloseOutageResponse {

    @XmlElement(name = "Result")
    protected BasicStruct6 result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link BasicStruct6 }
     *     
     */
    public BasicStruct6 getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicStruct6 }
     *     
     */
    public void setResult(BasicStruct6 value) {
        this.result = value;
    }

}
