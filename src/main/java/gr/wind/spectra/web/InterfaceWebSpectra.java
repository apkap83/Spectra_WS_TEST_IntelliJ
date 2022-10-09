package gr.wind.spectra.web;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import gr.wind.spectra.model.ProductOfCloseOutage;
import gr.wind.spectra.model.ProductOfGetHierarchy;
import gr.wind.spectra.model.ProductOfGetOutage;
import gr.wind.spectra.model.ProductOfModify;
import gr.wind.spectra.model.ProductOfNLUActive;
import gr.wind.spectra.model.ProductOfSubmission;

@WebService
public interface InterfaceWebSpectra
{

	@WebMethod()
	@WebResult(name = "Result")
	List<ProductOfGetHierarchy> getHierarchy(
			@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "RequestTimestamp") @XmlElement(required = true) String RequestTimestamp,
			@WebParam(name = "SystemID") @XmlElement(required = true) String SystemID,
			@WebParam(name = "UserID") @XmlElement(required = true) String UserID,
			@WebParam(name = "Hierarchy") String Hierarchy) throws Exception, InvalidInputException;

	@WebMethod()
	@WebResult(name = "Result")
	List<ProductOfSubmission> submitOutage(@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "RequestTimestamp") @XmlElement(required = true) String RequestTimestamp,
			@WebParam(name = "SystemID") @XmlElement(required = true) String SystemID,
			@WebParam(name = "UserID") @XmlElement(required = true) String UserID,
			@WebParam(name = "IncidentID") @XmlElement(required = true) String IncidentID,
			@WebParam(name = "Scheduled") @XmlElement(required = true) String Scheduled,
			@WebParam(name = "StartTime") @XmlElement(required = true) String StartTime,
			@WebParam(name = "EndTime") @XmlElement(required = false) String EndTime,
			@WebParam(name = "Duration") @XmlElement(required = false) String Duration,
			// TV, VOICE, DATA
			@WebParam(name = "AffectedServices") @XmlElement(required = true) String AffectedServices,
			// Quality, Loss
			@WebParam(name = "Impact") @XmlElement(required = true) String Impact,
			@WebParam(name = "Priority") @XmlElement(required = true) String Priority,
			@WebParam(name = "HierarchySelected") @XmlElement(required = true) String HierarchySelected)
			throws Exception, InvalidInputException;

	@WebMethod()
	@WebResult(name = "Result")
	List<ProductOfGetOutage> getOutageStatus(
			@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "IncidentID") @XmlElement(required = true) String IncidentID,
			@WebParam(name = "IncidentStatus") @XmlElement(required = true) String IncidentStatus)
			throws Exception, InvalidInputException;

	@WebMethod()
	@WebResult(name = "Result")
	ProductOfModify modifyOutage(@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "RequestTimestamp") @XmlElement(required = true) String RequestTimestamp,
			@WebParam(name = "SystemID") @XmlElement(required = true) String SystemID,
			@WebParam(name = "UserID") @XmlElement(required = true) String UserID,
			@WebParam(name = "IncidentID") @XmlElement(required = true) String IncidentID,
			@WebParam(name = "OutageID") @XmlElement(required = true) String OutageID,
			@WebParam(name = "StartTime") @XmlElement(required = false) String StartTime,
			@WebParam(name = "EndTime") @XmlElement(required = false) String EndTime,
			@WebParam(name = "Duration") @XmlElement(required = false) String Duration,
			// Quality, Loss
			@WebParam(name = "Impact") @XmlElement(required = false) String Impact)
			throws Exception, InvalidInputException;

	@WebMethod()
	@WebResult(name = "Result")
	ProductOfCloseOutage closeOutage(@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "RequestTimestamp") @XmlElement(required = true) String RequestTimestamp,
			@WebParam(name = "SystemID") @XmlElement(required = true) String SystemID,
			@WebParam(name = "UserID") @XmlElement(required = true) String UserID,
			@WebParam(name = "IncidentID") @XmlElement(required = true) String IncidentID,
			@WebParam(name = "OutageID") @XmlElement(required = true) String OutageID)
			throws Exception, InvalidInputException;

	@WebMethod
	@WebResult(name = "Result")
	ProductOfNLUActive NLU_Active(@WebParam(name = "UserName", header = true, mode = Mode.IN) String UserName,
			@WebParam(name = "Password", header = true, mode = Mode.IN) String Password,
			@WebParam(name = "RequestID") @XmlElement(required = true) String RequestID,
			@WebParam(name = "SystemID") @XmlElement(required = true) String SystemID,
			@WebParam(name = "RequestTimestamp") @XmlElement(required = true) String RequestTimestamp,
			@WebParam(name = "CLI") @XmlElement(required = true) String CLI,
			@WebParam(name = "Service") @XmlElement(required = true) String Service,
			@WebParam(name = "ServiceL2") @XmlElement(required = true) String ServiceL2,
			@WebParam(name = "ServiceL3") @XmlElement(required = true) String ServiceL3)
			throws Exception, InvalidInputException;
}