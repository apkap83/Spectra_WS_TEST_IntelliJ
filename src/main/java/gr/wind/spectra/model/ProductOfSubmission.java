package gr.wind.spectra.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Element")
@XmlType(name = "basicStruct2", propOrder = { "requestID", "outageID", "incidentID", "serviceAffected",
		"hierarchySelected", "location", "voiceCustomersAffected", "dataCustomersAffected", "CLIsAffected",
		"incidentVoiceCustomersAffected", "incidentDataCustomersAffected", "incidentTVCustomersAffected",
		"activeDataCustomersAffected", "tvCustomersAffected", "statusCode", "description" })

public class ProductOfSubmission
{
	private String requestID;
	private String outageID;
	private String incidentID;
	private String voiceCustomersAffected;
	private String dataCustomersAffected;
	private String incidentTVCustomersAffected;
	private String CLIsAffected;
	private String incidentVoiceCustomersAffected;
	private String incidentDataCustomersAffected;
	private String statusCode;
	private String description;
	private String serviceAffected;
	private String hierarchySelected;
	private String location = "Unknown";

	private String activeDataCustomersAffected;
	private String tvCustomersAffected;

	// Empty constructor requirement of JAXB (Java Architecture for XML Binding)
	public ProductOfSubmission()
	{
	}

	public ProductOfSubmission(String requestID, String outageID, String incidentID, String voiceCustomersAffected,
			String dataCustomersAffected, String IPTVCustomersAffected, String CLIsAffected, String locationsAffected,
			String incidentVoiceCustomersAffected, String incidentDataCustomersAffected,
			String incidentTVCustomersAffected, String statusCode, String serviceAffected, String hierarchySelected,
			String description)
	{
		this.requestID = requestID;
		this.incidentID = incidentID;
		this.outageID = outageID;
		this.voiceCustomersAffected = voiceCustomersAffected;
		this.dataCustomersAffected = dataCustomersAffected;
		this.CLIsAffected = CLIsAffected;
		if (locationsAffected != null)
		{
			this.location = locationsAffected;
		}
		this.incidentVoiceCustomersAffected = incidentVoiceCustomersAffected;
		this.incidentDataCustomersAffected = incidentDataCustomersAffected;
		this.incidentTVCustomersAffected = incidentTVCustomersAffected;
		this.statusCode = statusCode;
		this.description = description;
		this.serviceAffected = serviceAffected;
		this.hierarchySelected = hierarchySelected;

		this.activeDataCustomersAffected = "0";
		this.tvCustomersAffected = IPTVCustomersAffected;
	}

	@XmlElement(name = "requestID")
	public String getrequestID()
	{
		return this.requestID;
	}

	public void setrequestID(String requestID)
	{
		this.requestID = requestID;
	}

	@XmlElement(name = "activeDataCustomersAffected")
	public String getactiveDataCustomersAffected()
	{
		return activeDataCustomersAffected;
	}

	public void setactiveDataCustomersAffected(String activeDataCustomersAffected)
	{
		this.activeDataCustomersAffected = activeDataCustomersAffected;
	}

	@XmlElement(name = "IncidentTVCustomersAffected")
	public String getIncidentTVCustomersAffected()
	{
		return incidentTVCustomersAffected;
	}

	public void setIncidentTVCustomersAffected(String incidentTVCustomersAffected)
	{
		this.incidentTVCustomersAffected = incidentTVCustomersAffected;
	}

	@XmlElement(name = "tvCustomersAffected")
	public String gettvCustomersAffected()
	{
		return tvCustomersAffected;
	}

	public void settvCustomersAffected(String tvCustomersAffected)
	{
		this.tvCustomersAffected = tvCustomersAffected;
	}

	@XmlElement(name = "serviceAffected")
	public String getserviceAffected()
	{
		return this.serviceAffected;
	}

	public void setserviceAffected(String serviceAffected)
	{
		this.serviceAffected = serviceAffected;
	}

	@XmlElement(name = "hierarchySelected")
	public String gethierarchySelected()
	{
		return this.hierarchySelected;
	}

	@XmlElement(name = "location")
	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void sethierarchySelected(String hierarchySelected)
	{
		this.hierarchySelected = hierarchySelected;
	}

	@XmlElement(name = "voiceCustomersAffected")
	public String getvoiceCustomersAffected()
	{
		return this.voiceCustomersAffected;
	}

	public void setvoiceCustomersAffected(String voiceCustomersAffected)
	{
		this.voiceCustomersAffected = voiceCustomersAffected;
	}

	@XmlElement(name = "dataCustomersAffected")
	public String getdataCustomersAffected()
	{
		return this.dataCustomersAffected;
	}

	public void setdataCustomersAffected(String dataCustomersAffected)
	{
		this.dataCustomersAffected = dataCustomersAffected;
	}

	@XmlElement(name = "CLIsAffected")
	public String getCLIsAffected()
	{
		return this.CLIsAffected;
	}

	public void setCLIsAffected(String CLIsAffected)
	{
		this.CLIsAffected = CLIsAffected;
	}

	@XmlElement(name = "incidentVoiceCustomersAffected")
	public String getincidentVoiceCustomersAffected()
	{
		return this.incidentVoiceCustomersAffected;
	}

	public void setincidentVoiceCustomersAffected(String incidentVoiceCustomersAffected)
	{
		this.incidentVoiceCustomersAffected = incidentVoiceCustomersAffected;
	}

	@XmlElement(name = "incidentDataCustomersAffected")
	public String getincidentDataCustomersAffected()
	{
		return this.incidentDataCustomersAffected;
	}

	public void setincidentDataCustomersAffected(String incidentDataCustomersAffected)
	{
		this.incidentDataCustomersAffected = incidentDataCustomersAffected;
	}

	@XmlElement(name = "outageID")
	public String getoutageID()
	{
		return this.outageID;
	}

	public void setoutageID(String outageID)
	{
	}

	@XmlElement(name = "IncidentID")
	public String getIncidentID()
	{
		return this.incidentID;
	}

	public void setIncidentID(String incidentID)
	{
		this.incidentID = incidentID;
	}

	@XmlElement(name = "statusCode")
	public String getstatusCode()
	{
		return this.statusCode;
	}

	public void setstatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	@XmlElement(name = "description")
	public String getdescription()
	{
		return this.description;
	}

	public void setdescription(String description)
	{
		this.description = description;
	}

}
