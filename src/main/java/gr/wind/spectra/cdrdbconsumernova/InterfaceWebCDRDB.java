
package gr.wind.spectra.cdrdbconsumernova;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "InterfaceWebCDRDB", targetNamespace = "http://web.cdrdb.wind.gr/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface InterfaceWebCDRDB {


    /**
     * 
     * @param password
     * @param userName
     * @param parameters
     * @return
     *     returns gr.wind.cdrdb.web.HasOutageResponse
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(name = "hasOutageResponse", targetNamespace = "http://web.cdrdb.wind.gr/", partName = "result")
    @Action(input = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/hasOutageRequest", output = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/hasOutageResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/hasOutage/Fault/Exception")
    })
    public HasOutageResponse hasOutage(
        @WebParam(name = "hasOutage", targetNamespace = "http://web.cdrdb.wind.gr/", partName = "parameters")
        HasOutage parameters,
        @WebParam(name = "UserName", targetNamespace = "http://web.cdrdb.wind.gr/", header = true, partName = "UserName")
        String userName,
        @WebParam(name = "Password", targetNamespace = "http://web.cdrdb.wind.gr/", header = true, partName = "Password")
        String password)
        throws Exception_Exception
    ;

    /**
     * 
     * @param password
     * @param userName
     * @param parameters
     * @return
     *     returns gr.wind.cdrdb.web.InsertCallerDataResponse
     * @throws Exception_Exception
     */
    @WebMethod(operationName = "InsertCallerData")
    @WebResult(name = "InsertCallerDataResponse", targetNamespace = "http://web.cdrdb.wind.gr/", partName = "result")
    @Action(input = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/InsertCallerDataRequest", output = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/InsertCallerDataResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://web.cdrdb.wind.gr/InterfaceWebCDRDB/InsertCallerData/Fault/Exception")
    })
    public InsertCallerDataResponse insertCallerData(
        @WebParam(name = "InsertCallerData", targetNamespace = "http://web.cdrdb.wind.gr/", partName = "parameters")
        InsertCallerData parameters,
        @WebParam(name = "UserName", targetNamespace = "http://web.cdrdb.wind.gr/", header = true, partName = "UserName")
        String userName,
        @WebParam(name = "Password", targetNamespace = "http://web.cdrdb.wind.gr/", header = true, partName = "Password")
        String password)
        throws Exception_Exception
    ;

}
