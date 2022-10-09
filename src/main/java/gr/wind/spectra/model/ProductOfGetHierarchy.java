package gr.wind.spectra.model;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import gr.wind.spectra.business.DB_Operations;
import gr.wind.spectra.business.Help_Func;
import gr.wind.spectra.web.InvalidInputException;

@XmlRootElement(name = "Element")
@XmlType(name = "basicStruct", propOrder = { "requestID", "type", "item", "hierarchySelected", "dataCustomersAffected",
		"voiceCustomersAffected", "clisAffected", "activeDataCustomersAffected", "tvCustomersAffected", "wsAffected" })
public class ProductOfGetHierarchy
{

	private String type;
	private List<String> items;
	private String dataCustomersAffected = "none";
	private String voiceCustomersAffected = "none";
	private String CLIsAffected = "none";

	private String activeDataCustomersAffected = "none";
	private String tvCustomersAffected = "none";
	private String wsAffected = "none";
	private String requestID;
	private String hierarchyProvided;
	private String[] nodeNames;
	private String[] nodeValues;
	private String[] hierarchyFullPathList;
	private String[] hierElements;

	// Empty constructor requirement of JAXB (Java Architecture for XML Binding)
	public ProductOfGetHierarchy()
	{
	}
	// Product(wb.dbs, fullHierarchyFromDBSplit, fullDataSubsHierarchyFromDBSplit,
	// fullVoiceSubsHierarchyFromDBSplit, Hierarchy, fullHierarchyFromDBSplit[0] ,
	// ElementsList, nodeNames, nodeValues, RequestID);

	public ProductOfGetHierarchy(DB_Operations dbs, String[] hierarchyFullPathList, String[] fullDataHierarchyPath,
			String[] fullVoiceHierarchyPath, String hierarchyProvided, String type, List<String> items,
			String[] nodeNames, String[] nodeValues, String requestID, String WSAffected)
			throws SQLException, InvalidInputException
	{

		this.hierarchyProvided = hierarchyProvided;
		this.type = type;
		this.items = items;
		this.nodeNames = nodeNames;
		this.nodeValues = nodeValues;
		this.requestID = requestID;
		this.wsAffected = WSAffected;
		this.hierarchyFullPathList = hierarchyFullPathList;
		this.hierElements = hierarchyProvided.split("->");

		Help_Func hf = new Help_Func();

		// If there are no items to be returned, then we assume that you are at MaxLevel
		if (items.size() == 0)
		{
			this.type = "MaxLevel";
		}

		// If hierarchyProvided is null then return only values provided
		if (this.hierarchyProvided == null || this.hierarchyProvided.equals("") || this.hierarchyProvided.equals("?"))
		{
			// System.out.println("APOSTOLIS PRODUCT HERE 1");
		} else
		{
			// If hierarchyProvided is not null and has > 1 level hierarchy e.g.
			// FTTX->OltElementName
			if (this.hierElements.length > 1)
			{
				// Get Root element from hierarchy
				String rootElement = hf.getRootHierarchyNode(this.hierarchyProvided);

				// Firstly determine the hierarchy table that will be used based on the root
				// hierarchy provided
				String dataSubsTable = dbs.getOneValue("HierarchyTablePerTechnology2", "DataSubscribersTableName",
						new String[] { "RootHierarchyNode" }, new String[] { rootElement }, new String[] { "String" });

				String voiceSubsTable = dbs.getOneValue("HierarchyTablePerTechnology2", "VoiceSubscribersTableName",
						new String[] { "RootHierarchyNode" }, new String[] { rootElement }, new String[] { "String" });

				String IPTVSubsTable = dbs.getOneValue("HierarchyTablePerTechnology2", "IPTVSubscribersTableName",
						new String[] { "RootHierarchyNode" }, new String[] { rootElement }, new String[] { "String" });

				// Secondly determine NGA_TYPE based on rootElement
				String ngaTypes = dbs.getOneValue("HierarchyTablePerTechnology2", "NGA_TYPE",
						new String[] { "RootHierarchyNode" }, new String[] { rootElement }, new String[] { "String" });

				// Calculate data Customers Affected but replace column names in order to
				// search table for customers affected

				String dataCustomersAffected = dbs.countDistinctRowsForSpecificColumnsNGAIncluded(dataSubsTable,
						new String[] { "PASPORT_COID" },
						hf.hierarchyKeys(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						hf.hierarchyValues(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						hf.hierarchyStringTypes(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						ngaTypes);

				this.dataCustomersAffected = dataCustomersAffected;

				// Calculate Voice Customers Affected but replace column names in order to
				// search table for customers affected
				String voiceCustomersAffected = dbs.countDistinctRowsForSpecificColumnsNGAIncluded(voiceSubsTable,
						new String[] { "PASPORT_COID" },
						hf.hierarchyKeys(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullVoiceHierarchyPath)),
						hf.hierarchyValues(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullVoiceHierarchyPath)),
						hf.hierarchyStringTypes(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullVoiceHierarchyPath)),
						ngaTypes);

				this.voiceCustomersAffected = voiceCustomersAffected;

				// Commented Temporarily to reduce burden of Get Hierarchy Queries
				// Calculate CLIs Affected but replace column names in order to search table for
				// customers affected
				/*
				String CLIsAffected = dbs.countDistinctCLIsAffected(new String[] { "PASPORT_COID" },
						Help_Func.hierarchyKeys(Help_Func.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullVoiceHierarchyPath)),
						Help_Func.hierarchyValues(Help_Func.replaceHierarchyForSubscribersAffected(
								this.hierarchyProvided, fullVoiceHierarchyPath)),
						Help_Func.hierarchyStringTypes(Help_Func.replaceHierarchyForSubscribersAffected(
								this.hierarchyProvided, fullVoiceHierarchyPath)),
						ngaTypes, "NotSpecificService", voiceSubsTable, dataSubsTable, IPTVSubsTable);
				
				this.CLIsAffected = String.valueOf(CLIsAffected);
				*/

				this.CLIsAffected = "0";

				// Calculate IPTV Customers Affected but replace column names in order to search table for
				// customers affected
				String tvCustomersAffected = dbs.countDistinctRowsForSpecificColumnsNGAIncluded(IPTVSubsTable,
						new String[] { "PASPORT_COID" },
						hf.hierarchyKeys(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						hf.hierarchyValues(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						hf.hierarchyStringTypes(hf.replaceHierarchyForSubscribersAffected(this.hierarchyProvided,
								fullDataHierarchyPath)),
						ngaTypes);

				this.tvCustomersAffected = tvCustomersAffected;

				// Calculate this
				this.activeDataCustomersAffected = "0";
				// this.tvCustomersAffected = "0";
			}
		}
		// Ability To Export SQL Resultset to exported File
		//		SQLStatementToCSV s = new SQLStatementToCSV("MyUniqueFileExport2.txt", "Internet_Resource_Path",
		//				new String[] { "BRASNAME", "TECHNOLOGY" }, new String[] { "BRASNAME" }, new String[] { "AthMet1BR03" },
		//				new String[] { "String" });
		//		s.start();

	}

	@XmlElement(name = "wsAffected")
	public String getwsAffected()
	{
		return this.wsAffected;
	}

	public void setWSAffected(String wSAffected)
	{
		this.wsAffected = wSAffected;
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

	@XmlElement(name = "tvCustomersAffected")
	public String gettvCustomersAffected()
	{
		return tvCustomersAffected;
	}

	public void settvCustomersAffected(String tvCustomersAffected)
	{
		this.tvCustomersAffected = tvCustomersAffected;
	}

	@XmlElement(name = "elementType")
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
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

	@XmlElement(name = "dataCustomersAffected")
	public String getdataCustomersAffected()
	{
		return dataCustomersAffected;
	}

	public void setdataCustomersAffected(String dataCustomersAffected)
	{
		this.dataCustomersAffected = dataCustomersAffected;
	}

	@XmlElement(name = "voiceCustomersAffected")
	public String getvoiceCustomersAffected()
	{
		return voiceCustomersAffected;
	}

	public void setvoiceCustomersAffected(String voiceCustomersAffected)
	{
		this.voiceCustomersAffected = voiceCustomersAffected;
	}

	@XmlElement(name = "clisAffected")
	public String getclisAffected()
	{
		return this.CLIsAffected;
	}

	public void setclisAffected(String clisAffected)
	{
		this.CLIsAffected = clisAffected;
	}

	public List<String> getitem()
	{
		return this.items;
	}

	public void setitem(List<String> valuesList)
	{
		this.items = valuesList;
	}

	@XmlElement(name = "hierarchySelected")
	public String gethierarchySelected()
	{
		Help_Func hf = new Help_Func();

		String output = "";
		if (this.hierarchyProvided == null || this.hierarchyProvided.equals("") || this.hierarchyProvided.equals("?"))
		{
			output = "none";
		} else
		{
			// root element provided only
			if (this.hierElements.length == 0)
			{
				output = "None";
			} else if (this.hierElements.length >= 1)
			{
				// return this.hierarchyProvided + "->" + this.hierarchyFullPathList[0] + "=";
				output = hf.conCatHierarchy(nodeNames, nodeValues, this.hierarchyFullPathList);
			}
		}

		return output;
	}

	public void sethierarchySelected(String hierarchyProvided)
	{
		this.hierarchyProvided = hierarchyProvided;
	}

}
