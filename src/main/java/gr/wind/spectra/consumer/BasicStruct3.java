
package gr.wind.spectra.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basicStruct3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basicStruct3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scheduled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affectedServices" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="impact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hierarchyselected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affectedVoiceCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affectedDataCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affectedCLICustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeDataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tvCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentAffectedVoiceCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentAffectedDataCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicStruct3", propOrder = {
    "requestID",
    "outageID",
    "incidentStatus",
    "requestTimestamp",
    "systemID",
    "userID",
    "incidentID",
    "scheduled",
    "startTime",
    "endTime",
    "duration",
    "affectedServices",
    "impact",
    "priority",
    "hierarchyselected",
    "affectedVoiceCustomers",
    "affectedDataCustomers",
    "affectedCLICustomers",
    "activeDataCustomersAffected",
    "tvCustomersAffected",
    "incidentAffectedVoiceCustomers",
    "incidentAffectedDataCustomers"
})
public class BasicStruct3 {

    protected String requestID;
    protected String outageID;
    protected String incidentStatus;
    protected String requestTimestamp;
    protected String systemID;
    protected String userID;
    protected String incidentID;
    protected String scheduled;
    protected String startTime;
    protected String endTime;
    protected String duration;
    protected String affectedServices;
    protected String impact;
    protected String priority;
    protected String hierarchyselected;
    protected String affectedVoiceCustomers;
    protected String affectedDataCustomers;
    protected String affectedCLICustomers;
    protected String activeDataCustomersAffected;
    protected String tvCustomersAffected;
    protected String incidentAffectedVoiceCustomers;
    protected String incidentAffectedDataCustomers;

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
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
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
     * Gets the value of the scheduled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduled() {
        return scheduled;
    }

    /**
     * Sets the value of the scheduled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduled(String value) {
        this.scheduled = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the affectedServices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffectedServices() {
        return affectedServices;
    }

    /**
     * Sets the value of the affectedServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffectedServices(String value) {
        this.affectedServices = value;
    }

    /**
     * Gets the value of the impact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpact() {
        return impact;
    }

    /**
     * Sets the value of the impact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpact(String value) {
        this.impact = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the hierarchyselected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchyselected() {
        return hierarchyselected;
    }

    /**
     * Sets the value of the hierarchyselected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchyselected(String value) {
        this.hierarchyselected = value;
    }

    /**
     * Gets the value of the affectedVoiceCustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffectedVoiceCustomers() {
        return affectedVoiceCustomers;
    }

    /**
     * Sets the value of the affectedVoiceCustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffectedVoiceCustomers(String value) {
        this.affectedVoiceCustomers = value;
    }

    /**
     * Gets the value of the affectedDataCustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffectedDataCustomers() {
        return affectedDataCustomers;
    }

    /**
     * Sets the value of the affectedDataCustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffectedDataCustomers(String value) {
        this.affectedDataCustomers = value;
    }

    /**
     * Gets the value of the affectedCLICustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffectedCLICustomers() {
        return affectedCLICustomers;
    }

    /**
     * Sets the value of the affectedCLICustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffectedCLICustomers(String value) {
        this.affectedCLICustomers = value;
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
     * Gets the value of the incidentAffectedVoiceCustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentAffectedVoiceCustomers() {
        return incidentAffectedVoiceCustomers;
    }

    /**
     * Sets the value of the incidentAffectedVoiceCustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentAffectedVoiceCustomers(String value) {
        this.incidentAffectedVoiceCustomers = value;
    }

    /**
     * Gets the value of the incidentAffectedDataCustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentAffectedDataCustomers() {
        return incidentAffectedDataCustomers;
    }

    /**
     * Sets the value of the incidentAffectedDataCustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentAffectedDataCustomers(String value) {
        this.incidentAffectedDataCustomers = value;
    }

}
