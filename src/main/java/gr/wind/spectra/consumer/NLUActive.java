
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NLU_Active complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NLU_Active">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SystemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RequestTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceL2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceL3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NLU_Active", propOrder = {
    "requestID",
    "systemID",
    "requestTimestamp",
    "cli",
    "service",
    "serviceL2",
    "serviceL3"
})
public class NLUActive {

    @XmlElement(name = "RequestID", required = true)
    protected String requestID;
    @XmlElement(name = "SystemID", required = true)
    protected String systemID;
    @XmlElement(name = "RequestTimestamp", required = true)
    protected String requestTimestamp;
    @XmlElement(name = "CLI", required = true)
    protected String cli;
    @XmlElement(name = "Service", required = true)
    protected String service;
    @XmlElement(name = "ServiceL2", required = true)
    protected String serviceL2;
    @XmlElement(name = "ServiceL3", required = true)
    protected String serviceL3;

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
     * Gets the value of the systemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemID() {
        return systemID;
    }

    /**
     * Sets the value of the systemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemID(String value) {
        this.systemID = value;
    }

    /**
     * Gets the value of the requestTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    /**
     * Sets the value of the requestTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestTimestamp(String value) {
        this.requestTimestamp = value;
    }

    /**
     * Gets the value of the cli property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLI() {
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
    public void setCLI(String value) {
        this.cli = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Gets the value of the serviceL2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceL2() {
        return serviceL2;
    }

    /**
     * Sets the value of the serviceL2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceL2(String value) {
        this.serviceL2 = value;
    }

    /**
     * Gets the value of the serviceL3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceL3() {
        return serviceL3;
    }

    /**
     * Sets the value of the serviceL3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceL3(String value) {
        this.serviceL3 = value;
    }

}
