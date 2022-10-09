
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncidentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hierarchySelected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voiceCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLIsAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentVoiceCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentDataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncidentTVCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeDataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tvCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicStruct2", propOrder = {
    "requestID",
    "outageID",
    "incidentID",
    "serviceAffected",
    "hierarchySelected",
    "location",
    "voiceCustomersAffected",
    "dataCustomersAffected",
    "clIsAffected",
    "incidentVoiceCustomersAffected",
    "incidentDataCustomersAffected",
    "incidentTVCustomersAffected",
    "activeDataCustomersAffected",
    "tvCustomersAffected",
    "statusCode",
    "description"
})
public class BasicStruct2 {

    protected String requestID;
    protected String outageID;
    @XmlElement(name = "IncidentID")
    protected String incidentID;
    protected String serviceAffected;
    protected String hierarchySelected;
    protected String location;
    protected String voiceCustomersAffected;
    protected String dataCustomersAffected;
    @XmlElement(name = "CLIsAffected")
    protected String clIsAffected;
    protected String incidentVoiceCustomersAffected;
    protected String incidentDataCustomersAffected;
    @XmlElement(name = "IncidentTVCustomersAffected")
    protected String incidentTVCustomersAffected;
    protected String activeDataCustomersAffected;
    protected String tvCustomersAffected;
    protected String statusCode;
    protected String description;

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
     * Gets the value of the outageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutageID() {
        return outageID;
    }

    /**
     * Sets the value of the outageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutageID(String value) {
        this.outageID = value;
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
     * Gets the value of the serviceAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceAffected() {
        return serviceAffected;
    }

    /**
     * Sets the value of the serviceAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceAffected(String value) {
        this.serviceAffected = value;
    }

    /**
     * Gets the value of the hierarchySelected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchySelected() {
        return hierarchySelected;
    }

    /**
     * Sets the value of the hierarchySelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchySelected(String value) {
        this.hierarchySelected = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the voiceCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoiceCustomersAffected() {
        return voiceCustomersAffected;
    }

    /**
     * Sets the value of the voiceCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoiceCustomersAffected(String value) {
        this.voiceCustomersAffected = value;
    }

    /**
     * Gets the value of the dataCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataCustomersAffected() {
        return dataCustomersAffected;
    }

    /**
     * Sets the value of the dataCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCustomersAffected(String value) {
        this.dataCustomersAffected = value;
    }

    /**
     * Gets the value of the clIsAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLIsAffected() {
        return clIsAffected;
    }

    /**
     * Sets the value of the clIsAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLIsAffected(String value) {
        this.clIsAffected = value;
    }

    /**
     * Gets the value of the incidentVoiceCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentVoiceCustomersAffected() {
        return incidentVoiceCustomersAffected;
    }

    /**
     * Sets the value of the incidentVoiceCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentVoiceCustomersAffected(String value) {
        this.incidentVoiceCustomersAffected = value;
    }

    /**
     * Gets the value of the incidentDataCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentDataCustomersAffected() {
        return incidentDataCustomersAffected;
    }

    /**
     * Sets the value of the incidentDataCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentDataCustomersAffected(String value) {
        this.incidentDataCustomersAffected = value;
    }

    /**
     * Gets the value of the incidentTVCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentTVCustomersAffected() {
        return incidentTVCustomersAffected;
    }

    /**
     * Sets the value of the incidentTVCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentTVCustomersAffected(String value) {
        this.incidentTVCustomersAffected = value;
    }

    /**
     * Gets the value of the activeDataCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveDataCustomersAffected() {
        return activeDataCustomersAffected;
    }

    /**
     * Sets the value of the activeDataCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveDataCustomersAffected(String value) {
        this.activeDataCustomersAffected = value;
    }

    /**
     * Gets the value of the tvCustomersAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTvCustomersAffected() {
        return tvCustomersAffected;
    }

    /**
     * Sets the value of the tvCustomersAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTvCustomersAffected(String value) {
        this.tvCustomersAffected = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
