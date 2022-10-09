package gr.wind.spectra.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import gr.wind.spectra.web.InvalidInputException;

public class OpenningIncidentOutageToCSV
{
	private DB_Operations dbs;
	private s_DB_Operations s_dbs;
	private String incidentID;
	private String outageID;

	public OpenningIncidentOutageToCSV(DB_Operations dbs, s_DB_Operations s_dbs, String incidentID, String outageID)
	{
		this.dbs = dbs;
		this.s_dbs = s_dbs;
		this.incidentID = incidentID;
		this.outageID = outageID;
	}

	public String replaceHierarchyColumns(String hierarchyProvided, String technology)
			throws SQLException, InvalidInputException
	{
		Help_Func hf = new Help_Func();
		String newHierarchyValue = "";

		if (technology.equals("Voice"))
		{
			// Get root hierarchy String
			String rootElementInHierarchy = hf.getRootHierarchyNode(hierarchyProvided);

			String fullVoiceSubsHierarchyFromDB;
			String[] fullVoiceSubsHierarchyFromDBSplit;
			// Get Full Voice hierarchy in style :
			// OltElementName->OltSlot->OltPort->Onu->ActiveElement->Slot
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue("HierarchyTablePerTechnology2",
					"VoiceSubscribersTableNamePath", new String[] { "RootHierarchyNode" },
					new String[] { rootElementInHierarchy }, new String[] { "String" });

			// Split the Data hierarchy retrieved from DB into fields
			fullVoiceSubsHierarchyFromDBSplit = fullVoiceSubsHierarchyFromDB.split("->");

			// Replace Hierarchy Columns from the relevant subscribers table
			newHierarchyValue = hf.replaceHierarchyForSubscribersAffected(hierarchyProvided,
					fullVoiceSubsHierarchyFromDBSplit);
		} else if (technology.equals("Data"))
		{
			// Get root hierarchy String
			String rootElementInHierarchy = hf.getRootHierarchyNode(hierarchyProvided);

			String fullVoiceSubsHierarchyFromDB;
			String[] fullVoiceSubsHierarchyFromDBSplit;
			// Get Full Voice hierarchy in style :
			// OltElementName->OltSlot->OltPort->Onu->ActiveElement->Slot
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue("HierarchyTablePerTechnology2",
					"DataSubscribersTableNamePath", new String[] { "RootHierarchyNode" },
					new String[] { rootElementInHierarchy }, new String[] { "String" });

			// Split the Data hierarchy retrieved from DB into fields
			fullVoiceSubsHierarchyFromDBSplit = fullVoiceSubsHierarchyFromDB.split("->");

			// Replace Hierarchy Columns from the relevant subscribers table
			newHierarchyValue = hf.replaceHierarchyForSubscribersAffected(hierarchyProvided,
					fullVoiceSubsHierarchyFromDBSplit);
		} else if (technology.equals("IPTV"))
		{
			// Get root hierarchy String
			String rootElementInHierarchy = hf.getRootHierarchyNode(hierarchyProvided);

			String fullVoiceSubsHierarchyFromDB;
			String[] fullVoiceSubsHierarchyFromDBSplit;
			// Get Full Voice hierarchy in style :
			// OltElementName->OltSlot->OltPort->Onu->ActiveElement->Slot
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue("HierarchyTablePerTechnology2",
					"IPTVSubscribersTableNamePath", new String[] { "RootHierarchyNode" },
					new String[] { rootElementInHierarchy }, new String[] { "String" });

			// Split the Data hierarchy retrieved from DB into fields
			fullVoiceSubsHierarchyFromDBSplit = fullVoiceSubsHierarchyFromDB.split("->");

			// Replace Hierarchy Columns from the relevant subscribers table
			newHierarchyValue = hf.replaceHierarchyForSubscribersAffected(hierarchyProvided,
					fullVoiceSubsHierarchyFromDBSplit);
		}
		return newHierarchyValue;
	}

	public void produceReport() throws SQLException, InvalidInputException
	{
		Help_Func hf = new Help_Func();

		//Get current date time
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String currentDate = now.format(formatter);

		ResultSet rs = null;
		// Get Lines with IncidentStatus = "OPEN"
		rs = s_dbs.getRows("Test_SubmittedIncidents",
				new String[] { "HierarchySelected", "StartTime", "EndTime", "Scheduled", "Impact", "AffectedServices",
						"IncidentStatus", "IncidentID", "Scheduled", "Priority", "Locations" },
				new String[] { "incidentID", "outageID" }, new String[] { incidentID, outageID },
				new String[] { "String", "String" });

		String HierarchySelected = "";
		Date startTime = null;
		String endTime = "null";
		String scheduled = "";
		String impact = "";
		String outageAffectedService = "";
		String incidentID = "";
		String priority = "";
		String locations = "";
		while (rs.next())
		{
			rs.getString("IncidentStatus");
			incidentID = rs.getString("IncidentID");
			scheduled = rs.getString("Scheduled");
			startTime = rs.getTimestamp("StartTime");

			outageAffectedService = rs.getString("AffectedServices");
			impact = rs.getString("Impact");
			priority = rs.getString("Priority");
			locations = rs.getString("Locations");
			HierarchySelected = rs.getString("HierarchySelected");
		}

		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);

		// Firstly determine the hierarchy table that will be used based on the root
		// hierarchy provided
		String rootHierarchySelected = hf.getRootHierarchyNode(HierarchySelected);

		// Secondly determine NGA_TYPE based on rootElement
		String ngaTypes = dbs.getOneValue("HierarchyTablePerTechnology2", "NGA_TYPE",
				new String[] { "RootHierarchyNode" }, new String[] { rootHierarchySelected },
				new String[] { "String" });

		// If the closed incident is a "Data" affected one
		if (outageAffectedService.equals("Data"))
		{
			String exportedFileName = "/opt/ExportedFiles/AllOpenedOutages/Test_Env/Spectra_CLIs_Affected_INC_"
					+ incidentID + "_OutageID_" + outageID + "_Data_" + currentDate + ".csv";

			HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "Data");

			// Null pointer exception investigation
			//			System.out.println("outageID = " + outageID);
			//			System.out.println("incidentID = " + incidentID);
			//			System.out.println("scheduled = " + scheduled);
			//
			//			System.out.println("df.format(startTime) = " + df.format(startTime));
			//			System.out.println("outageAffectedService = " + outageAffectedService);
			//			System.out.println("impact = " + impact);
			//			System.out.println("priority = " + priority);
			//			System.out.println("HierarchySelected = " + HierarchySelected);
			//			System.out.println("locations = " + locations);

			// If no locations are found then set it to empty string
			if (locations == null)
			{
				locations = "";
			}

			SQLStatementToCSV sCSV = new SQLStatementToCSV(exportedFileName, "Prov_Internet_Resource_Path",
					new String[] { "CliValue", "'" + outageID + "'", "'OPEN'", "'" + incidentID + "'",
							"'" + scheduled + "'", "'" + df.format(startTime) + "'", "'" + endTime + "'",
							"'" + outageAffectedService + "'", "'" + impact + "'", "'" + priority + "'",
							"'" + HierarchySelected + "'", "SiteName" },
					hf.hierarchyKeys(HierarchySelected), hf.hierarchyValues(HierarchySelected),
					hf.hierarchyStringTypes(HierarchySelected), ngaTypes);
			sCSV.start();
		}

		// If the closed incident is a "Voice" affected one
		else if (outageAffectedService.equals("Voice"))
		{
			String exportedFileName = "/opt/ExportedFiles/AllOpenedOutages/Test_Env/Spectra_CLIs_Affected_INC_"
					+ incidentID + "_OutageID_" + outageID + "_Voice_" + currentDate + ".csv";

			HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "Voice");

			SQLStatementToCSV sCSV = new SQLStatementToCSV(exportedFileName, "Prov_Voice_Resource_Path",
					new String[] { "CliValue", "'" + outageID + "'", "'OPEN'", "'" + incidentID + "'",
							"'" + scheduled + "'", "'" + df.format(startTime) + "'", "'" + endTime + "'",
							"'" + outageAffectedService + "'", "'" + impact + "'", "'" + priority + "'",
							"'" + HierarchySelected + "'", "SiteName" },
					hf.hierarchyKeys(HierarchySelected), hf.hierarchyValues(HierarchySelected),
					hf.hierarchyStringTypes(HierarchySelected), ngaTypes);
			sCSV.start();
		}
		// If the closed incident is a "IPTV" affected one
		else if (outageAffectedService.equals("IPTV"))
		{
			String exportedFileName = "/opt/ExportedFiles/AllOpenedOutages/Test_Env/Spectra_CLIs_Affected_INC_"
					+ incidentID + "_OutageID_" + outageID + "_IPTV_" + currentDate + ".csv";

			HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "IPTV");

			SQLStatementToCSV sCSV = new SQLStatementToCSV(exportedFileName, "Prov_IPTV_Resource_Path",
					new String[] { "CliValue", "'" + outageID + "'", "'OPEN'", "'" + incidentID + "'",
							"'" + scheduled + "'", "'" + df.format(startTime) + "'", "'" + endTime + "'",
							"'" + outageAffectedService + "'", "'" + impact + "'", "'" + priority + "'",
							"'" + HierarchySelected + "'", "SiteName" },
					hf.hierarchyKeys(HierarchySelected), hf.hierarchyValues(HierarchySelected),
					hf.hierarchyStringTypes(HierarchySelected), ngaTypes);
			sCSV.start();
		}

		dbs = null;
		s_dbs = null;
		incidentID = null;
		outageID = null;
	}

}
