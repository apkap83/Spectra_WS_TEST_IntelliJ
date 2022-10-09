
package gr.wind.spectra.consumer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basicStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basicStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="item" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hierarchySelected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voiceCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clisAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeDataCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tvCustomersAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAffected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicStruct", propOrder = {
    "requestID",
    "elementType",
    "item",
    "hierarchySelected",
    "dataCustomersAffected",
    "voiceCustomersAffected",
    "clisAffected",
    "activeDataCustomersAffected",
    "tvCustomersAffected",
    "wsAffected"
})
public class BasicStruct {

    protected String requestID;
    protected String elementType;
    @XmlElement(nillable = true)
    protected List<String> item;
    protected String hierarchySelected;
    protected String dataCustomersAffected;
    protected String voiceCustomersAffected;
    protected String clisAffected;
    protected String activeDataCustomersAffected;
    protected String tvCustomersAffected;
    protected String wsAffected;

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
     * Gets the value of the elementType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * Sets the value of the elementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementType(String value) {
        this.elementType = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getItem() {
        if (item == null) {
            item = new ArrayList<String>();
        }
        return this.item;
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
     * Gets the value of the clisAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClisAffected() {
        return clisAffected;
    }

    /**
     * Sets the value of the clisAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClisAffected(String value) {
        this.clisAffected = value;
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
     * Gets the value of the wsAffected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsAffected() {
        return wsAffected;
    }

    /**
     * Sets the value of the wsAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsAffected(String value) {
        this.wsAffected = value;
    }

}
