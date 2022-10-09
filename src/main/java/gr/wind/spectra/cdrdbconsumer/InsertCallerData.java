
package gr.wind.spectra.cdrdbconsumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for InsertCallerData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InsertCallerData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cli" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Incident_Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Affected_Services" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PayTV_Services" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Is_Scheduled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Backup_Elegible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CSSCollection_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Access_Service" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Active_Element" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BRAS_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CO_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="API_User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="API_Process" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertCallerData", propOrder = { "requestID", "username", "cli", "incidentNumber", "affectedServices",
		"payTVServices", "isScheduled", "backupElegible", "cssCollectionName", "accessService", "clid", "activeElement",
		"brasName", "coid", "apiUser", "apiProcess" })
public class InsertCallerData
{

	@XmlElement(name = "RequestID", required = true)
	protected String requestID;
	@XmlElement(name = "Username")
	protected String username;
	@XmlElement(name = "Cli", required = true)
	protected String cli;
	@XmlElement(name = "Incident_Number", required = true)
	protected String incidentNumber;
	@XmlElement(name = "Affected_Services", required = true)
	protected String affectedServices;
	@XmlElement(name = "PayTV_Services", required = true)
	protected String payTVServices;
	@XmlElement(name = "Is_Scheduled", required = true)
	protected String isScheduled;
	@XmlElement(name = "Backup_Elegible", required = true)
	protected String backupElegible;
	@XmlElement(name = "CSSCollection_Name", required = true)
	protected String cssCollectionName;
	@XmlElement(name = "Access_Service", required = true)
	protected String accessService;
	@XmlElement(name = "CLID", required = true)
	protected String clid;
	@XmlElement(name = "Active_Element", required = true)
	protected String activeElement;
	@XmlElement(name = "BRAS_Name", required = true)
	protected String brasName;
	@XmlElement(name = "CO_ID", required = true)
	protected String coid;
	@XmlElement(name = "API_User", required = true)
	protected String apiUser;
	@XmlElement(name = "API_Process", required = true)
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
	 * Gets the value of the username property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * Sets the value of the username property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setUsername(String value)
	{
		this.username = value;
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
	 * Gets the value of the incidentNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getIncidentNumber()
	{
		return incidentNumber;
	}

	/**
	 * Sets the value of the incidentNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIncidentNumber(String value)
	{
		this.incidentNumber = value;
	}

	/**
	 * Gets the value of the affectedServices property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAffectedServices()
	{
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
	public void setAffectedServices(String value)
	{
		this.affectedServices = value;
	}

	/**
	 * Gets the value of the payTVServices property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getPayTVServices()
	{
		return payTVServices;
	}

	/**
	 * Sets the value of the payTVServices property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPayTVServices(String value)
	{
		this.payTVServices = value;
	}

	/**
	 * Gets the value of the isScheduled property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getIsScheduled()
	{
		return isScheduled;
	}

	/**
	 * Sets the value of the isScheduled property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIsScheduled(String value)
	{
		this.isScheduled = value;
	}

	/**
	 * Gets the value of the backupElegible property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getBackupElegible()
	{
		return backupElegible;
	}

	/**
	 * Sets the value of the backupElegible property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setBackupElegible(String value)
	{
		this.backupElegible = value;
	}

	/**
	 * Gets the value of the cssCollectionName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCSSCollectionName()
	{
		return cssCollectionName;
	}

	/**
	 * Sets the value of the cssCollectionName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCSSCollectionName(String value)
	{
		this.cssCollectionName = value;
	}

	/**
	 * Gets the value of the accessService property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAccessService()
	{
		return accessService;
	}

	/**
	 * Sets the value of the accessService property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAccessService(String value)
	{
		this.accessService = value;
	}

	/**
	 * Gets the value of the clid property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCLID()
	{
		return clid;
	}

	/**
	 * Sets the value of the clid property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCLID(String value)
	{
		this.clid = value;
	}

	/**
	 * Gets the value of the activeElement property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getActiveElement()
	{
		return activeElement;
	}

	/**
	 * Sets the value of the activeElement property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setActiveElement(String value)
	{
		this.activeElement = value;
	}

	/**
	 * Gets the value of the brasName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getBRASName()
	{
		return brasName;
	}

	/**
	 * Sets the value of the brasName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setBRASName(String value)
	{
		this.brasName = value;
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
	 * Gets the value of the apiUser property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAPIUser()
	{
		return apiUser;
	}

	/**
	 * Sets the value of the apiUser property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAPIUser(String value)
	{
		this.apiUser = value;
	}

	/**
	 * Gets the value of the apiProcess property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getAPIProcess()
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
	public void setAPIProcess(String value)
	{
		this.apiProcess = value;
	}

}
