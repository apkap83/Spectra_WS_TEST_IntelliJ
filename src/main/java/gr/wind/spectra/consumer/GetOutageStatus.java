
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOutageStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOutageStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncidentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncidentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOutageStatus", propOrder = {
    "requestID",
    "incidentID",
    "incidentStatus"
})
public class GetOutageStatus {

    @XmlElement(name = "RequestID", required = true)
    protected String requestID;
    @XmlElement(name = "IncidentID", required = true)
    protected String incidentID;
    @XmlElement(name = "IncidentStatus", required = true)
    protected String incidentStatus;

    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
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
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the incidentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentID() {
        return incidentID;
    }

    /**
     * Sets the value of the incidentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentID(String value) {
        this.incidentID = value;
    }

    /**
     * Gets the value of the incidentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatus() {
        return incidentStatus;
    }

    /**
     * Sets the value of the incidentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentStatus(String value) {
        this.incidentStatus = value;
    }

}
