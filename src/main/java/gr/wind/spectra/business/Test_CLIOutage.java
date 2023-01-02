package gr.wind.spectra.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import gr.wind.spectra.cdrdbconsumer.HasOutage;
//import gr.wind.spectra.cdrdbconsumer.WebCDRDBService;

import gr.wind.spectra.cdrdbconsumernova.HasOutage;
import gr.wind.spectra.cdrdbconsumernova.WebCDRDBService;

import gr.wind.spectra.model.ProductOfNLUActive;
import gr.wind.spectra.web.InvalidInputException;

public class Test_CLIOutage
{
	private iDB_Operations dbs;
	private iStatic_DB_Operations s_dbs;
	private String requestID;
	private String systemID;

	private String FoundForCompany;
	private String tablePrefix;
	private final String windTableNamePrefix = "";
	private final String novaTableNamePrefix = "Nova_";

	Help_Func hf = new Help_Func();

	DateFormat dateFormat = new SimpleDateFormat(hf.DATE_FORMAT);

	// Logger instance
	private static final Logger logger = LogManager.getLogger(gr.wind.spectra.business.Test_CLIOutage.class.getName());

	public Test_CLIOutage(iDB_Operations dbs, iStatic_DB_Operations s_dbs, String requestID, String systemID, String FoundForCompany) throws Exception
	{
		this.dbs = dbs;
		this.s_dbs = s_dbs;
		this.requestID = requestID;
		this.systemID = systemID;
		this.FoundForCompany = FoundForCompany;

		// Check if Export is for Nova or Wind
		if (dbs.getClass().toString().equals("class gr.wind.spectra.business.DB_Operations")) {
			this.tablePrefix = windTableNamePrefix;
		} else if (dbs.getClass().toString().equals("class gr.wind.spectra.business.TnovaDynamicDBOperations")) {
			this.tablePrefix = novaTableNamePrefix;
		}

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
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2",
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
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2",
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
			fullVoiceSubsHierarchyFromDB = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2",
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

	public ProductOfNLUActive checkAdHocOutage(String RequestID, String CLIProvided, String Company) throws SQLException, ParseException
	{
		ProductOfNLUActive ponla = new ProductOfNLUActive();

		// Check if CLI is affected by AdHoc Outage
		// Get Lines with CliValue = CLIProvided
		ResultSet myRS = null;
		myRS = s_dbs.getRows(tablePrefix + "Test_AdHocOutage_CLIS",
				new String[] { "CliValue", "Start_DateTime", "End_DateTime", "BackupEligible", "Message" },
				new String[] { "CliValue" }, new String[] { CLIProvided }, new String[] { "String" });

		while (myRS.next())
		{
			String AdHoc_CliValue = myRS.getString("CliValue").trim();
			Date AdHoc_StartTime = myRS.getTimestamp("Start_DateTime");
			Date AdHoc_EndTime = myRS.getTimestamp("End_DateTime");
			String BackupEligible = myRS.getString("BackupEligible").trim();
			String adHocMessage = myRS.getString("Message").trim();

			// If it's null it's msg2
			if (adHocMessage == null)
			{
				adHocMessage = "msg2";
			}

			// Backup Eligible response should be "Y" or "N"
			if (BackupEligible.equals("Yes"))
			{
				BackupEligible = "Y";
			} else
			{
				BackupEligible = "N";
			}

			// Get current date
			LocalDateTime now = LocalDateTime.now();

			// Convert StartTime date to LocalDateTime object
			LocalDateTime StartTimeInLocalDateTime = Instant.ofEpochMilli(AdHoc_StartTime.getTime())
					.atZone(ZoneId.systemDefault()).toLocalDateTime();

			// Convert EndTime date to LocalDateTime object
			LocalDateTime EndTimeInLocalDateTime = Instant.ofEpochMilli(AdHoc_EndTime.getTime())
					.atZone(ZoneId.systemDefault()).toLocalDateTime();

			// if Start time is after NOW and End Time is Before NOW then we have outage
			if (now.isAfter(StartTimeInLocalDateTime) && now.isBefore(EndTimeInLocalDateTime))
			{
				logger.debug(
						"ReqID: " + RequestID + " - AdHoc Incident for CliValue " + AdHoc_CliValue + " is ongoing");

				// Convert End DateTime to String
				String AdHoc_EndTimeString = "";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				AdHoc_EndTimeString = dateFormat.format(AdHoc_EndTime);

				ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "Yes", "AdHoc_Outage", "Critical",
						"Voice|Data|IPTV", "Yes", null, AdHoc_EndTimeString, "LoS", adHocMessage, BackupEligible,
						"NULL");

				logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: " + CLIProvided
						+ " from AdHoc Outage - Message: " + adHocMessage + " | Backup: " + BackupEligible);

				// Update Statistics
				s_dbs.updateUsageStatisticsForMethod("AdHoc_Pos");

				// Update asynchronously - Add Caller to Caller data table (Test_Caller_Data) with empty values for IncidentID, Affected Services & Scheduling
				Update_CallerDataTable ucdt = new Update_CallerDataTable(dbs, s_dbs, CLIProvided, "AdHoc_Outage",
						"Voice|Data|IPTV", "Yes", adHocMessage, BackupEligible, RequestID, systemID, Company);
				ucdt.run();

				// Update asynchronously Test_Stats_Pos_NLU_Requests to count number of successful NLU requests per CLI
				Update_ReallyAffectedTable uRat = new Update_ReallyAffectedTable(s_dbs, "AdHoc_Outage",
						"Voice|Data|IPTV", "Yes", CLIProvided);
				uRat.run();

				return ponla;
			} else
			{
				logger.debug(
						"ReqID: " + RequestID + " - AdHoc Incident for CliValue " + AdHoc_CliValue + " is NOT ongoing");
				continue;
			}

		}

		return null;
	}

	public ProductOfNLUActive checkCLIOutage(String RequestID, String CLIProvided, String ServiceType)
			throws SQLException, InvalidInputException, ParseException
	{
		String Company = "UNKNOWN";

		ProductOfNLUActive ponla = new ProductOfNLUActive();
		boolean foundAtLeastOneCLIAffected = false;
		boolean voiceAffected = false;
		boolean dataAffected = false;
		boolean iptvAffected = false;
		String allAffectedServices = "";

		Help_Func hf = new Help_Func();

		if (FoundForCompany.equals("FOUND_FOR_WIND")) {
			Company = "WIND";
			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Checking CLI Outage CLI: " + CLIProvided + " | "
					+ ServiceType + " (Found in Wind DB)");
		} else if (FoundForCompany.equals("FOUND_FOR_NOVA")) {
			Company = "NOVA";
			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Checking CLI Outage CLI: " + CLIProvided + " | "
					+ ServiceType + " (Found in Nova DB)");
		} else if (FoundForCompany.equals("NOT_FOUND_FOR_WIND_OR_NOVA")) {
			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Checking CLI Outage CLI: " + CLIProvided + " | "
					+ ServiceType + " (Not Found in DBs)");
		}

		// Check if we have Ad-Hoc Outage from Table: Test_AdHocOutage_CLIS
		ProductOfNLUActive ponla_AdHoc = checkAdHocOutage(RequestID, CLIProvided, Company);
		if (ponla_AdHoc != null)
		{
			return ponla_AdHoc;
		}

		// Check if we have at least one OPEN incident
		boolean weHaveOpenIncident = s_dbs.checkIfStringExistsInSpecificColumn(tablePrefix + "Test_SubmittedIncidents",
				"IncidentStatus", "OPEN");

		// Check number of open incidents
		String numOfOpenIncidentsCurrently = s_dbs.numberOfRowsFound(tablePrefix + "Test_SubmittedIncidents",
				new String[] { "IncidentStatus" }, new String[] { "OPEN" }, new String[] { "String" });

		// If the submitted service type is empty then fill it with "Voice|Data"
		if (hf.checkIfEmpty("ServiceType", ServiceType))
		{
			ServiceType = "Voice|Data|IPTV";
		}

		// Split ServiceType
		String delimiterCharacter = "\\|";
		String[] ServiceTypeSplitted = ServiceType.split(delimiterCharacter);

		logger.debug("ReqID: " + RequestID + " - We have open incidents: " + weHaveOpenIncident);
		// If We have at least one opened incident...
		if (weHaveOpenIncident)
		{
			logger.debug(
					"ReqID: " + RequestID + " - Number of incidents currently OPEN: " + numOfOpenIncidentsCurrently);

			// String foundHierarchySelected = "";
			String foundPriority = "";
			// String foundOutageAffectedService = "";
			String foundIncidentID = "";
			String foundScheduled = "";
			String foundDuration = "";
			Date foundStartTime = null;
			Date foundEndTime = null;
			String foundImpact = "";
			String EndTimeString = null;
			String foundOutageMsg = "";
			String foundFlag2_BackupEligible = "";

			for (String service : ServiceTypeSplitted)
			{
				ResultSet rs = null;
				// Get Lines with IncidentStatus = "OPEN"
				rs = s_dbs.getRows(tablePrefix + "Test_SubmittedIncidents",
						new String[] { "WillBePublished", "IncidentID", "OutageID", "BackupEligible",
								"HierarchySelected", "Priority", "AffectedServices", "Scheduled", "Duration",
								"StartTime", "EndTime", "Impact", "OutageMsg" },
						new String[] { "IncidentStatus" }, new String[] { "OPEN" }, new String[] { "String" });

				while (rs.next())
				{
					boolean isOutageWithinScheduledRange = false;

					String WillBePublished = rs.getString("WillBePublished");
					String IncidentID = rs.getString("IncidentID");
					int OutageID = rs.getInt("OutageID");
					String HierarchySelected = rs.getString("HierarchySelected");
					String Priority = rs.getString("Priority");
					String outageAffectedService = rs.getString("AffectedServices");
					String Scheduled = rs.getString("Scheduled");
					String Duration = rs.getString("Duration");
					Date StartTime = rs.getTimestamp("StartTime");
					Date EndTime = rs.getTimestamp("EndTime");
					String Impact = rs.getString("Impact");
					String OutageMsg = rs.getString("OutageMsg");
					String BackupEligible = rs.getString("BackupEligible");

					// If it is OPEN & Scheduled & Date(Now) > StartTime then set
					// isOutageWithinScheduledRange to TRUE
					if (Scheduled.equals("Yes"))
					{
						logger.debug("ReqID: " + RequestID + " - Checking Scheduled Incident: " + IncidentID);
						// Get current date
						LocalDateTime now = LocalDateTime.now();

						// Convert StartTime date to LocalDateTime object
						LocalDateTime StartTimeInLocalDateTime = Instant.ofEpochMilli(StartTime.getTime())
								.atZone(ZoneId.systemDefault()).toLocalDateTime();

						// Convert EndTime date to LocalDateTime object
						LocalDateTime EndTimeInLocalDateTime = Instant.ofEpochMilli(EndTime.getTime())
								.atZone(ZoneId.systemDefault()).toLocalDateTime();

						// if Start time is after NOW and End Time is Before NOW then we have outage
						if (now.isAfter(StartTimeInLocalDateTime) && now.isBefore(EndTimeInLocalDateTime))
						{
							isOutageWithinScheduledRange = true;
							logger.debug(
									"ReqID: " + RequestID + " - Scheduled Incident: " + IncidentID + " is ongoing");
						} else
						{
							isOutageWithinScheduledRange = false;
							logger.debug(
									"ReqID: " + RequestID + " - Scheduled Incident: " + IncidentID + " is NOT ongoing");
							continue;
						}

						// If the scheduled period (Start Time - End Time) has passed current local time then change the Incident status to "CLOSED"
						/*  NOT TESTED YET
						if (now.isAfter(EndTimeInLocalDateTime))
						{
							int numOfRowsUpdated = s_dbs.updateColumnOnSpecificCriteria("Test_SubmittedIncidents",
									new String[] { "IncidentStatus" }, new String[] { "CLOSED" },
									new String[] { "String" }, new String[] { "IncidentID", "OutageID" },
									new String[] { IncidentID, String.valueOf(OutageID) },
									new String[] { "String", "Integer" });
						
							if (numOfRowsUpdated > 0)
							{
								logger.debug("ReqID: " + RequestID + " - Scheduled Incident: " + IncidentID
										+ " was marked as CLOSED");
							}
						}
						*/
					}

					// if service given in web request is Voice
					if (outageAffectedService.equals("Voice") && service.equals("Voice"))
					{
						// Replace Hierarchy keys from the correct column names of Hierarchy Subscribers
						// table
						HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "Voice");

						// Add CLI Value in Hierarcy
						HierarchySelected += "->CliValue=" + CLIProvided;

						// Get root hierarchy String
						String rootElementInHierarchy = hf.getRootHierarchyNode(HierarchySelected);

						// Get Hierarchy Table for that root hierarchy
						String table = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2", "VoiceSubscribersTableName",
								new String[] { "RootHierarchyNode" }, new String[] { rootElementInHierarchy },
								new String[] { "String" });

						String numOfRowsFound = dbs.numberOfRowsFound(table, hf.hierarchyKeys(HierarchySelected),
								hf.hierarchyValues(HierarchySelected), hf.hierarchyStringTypes(HierarchySelected));

						// If matched Hierarchy + CLI matches lines (then those CLIs have actually
						// Outage)
						if (WillBePublished.equals("Yes"))
						{
							if (Integer.parseInt(numOfRowsFound) > 0 && Scheduled.equals("No"))
							{

								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								voiceAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Non-scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;

							} else if (Integer.parseInt(numOfRowsFound) > 0 && Scheduled.equals("Yes")
									&& isOutageWithinScheduledRange)
							{
								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								voiceAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;
							}
						}
					} else if (outageAffectedService.equals("Data") && service.equals("Data"))
					{
						// Replace Hierarchy keys from the correct column names of Hierarchy Subscribers
						// table
						HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "Data");

						// Add CLI Value in Hierarcy
						HierarchySelected += "->CliValue=" + CLIProvided;

						// Get root hierarchy String
						String rootElementInHierarchy = hf.getRootHierarchyNode(HierarchySelected);

						// Get Hierarchy Table for that root hierarchy
						String table = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2", "DataSubscribersTableName",
								new String[] { "RootHierarchyNode" }, new String[] { rootElementInHierarchy },
								new String[] { "String" });

						String numOfRowsFound = dbs.numberOfRowsFound(table, hf.hierarchyKeys(HierarchySelected),
								hf.hierarchyValues(HierarchySelected), hf.hierarchyStringTypes(HierarchySelected));

						// Scheduled No & Rows Found
						if (WillBePublished.equals("Yes"))
						{
							if (Integer.parseInt(numOfRowsFound) > 0 && Scheduled.equals("No"))
							{
								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								dataAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Non-scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;
								// Scheduled Yes & Rows Found & Outage Within Scheduled Range
							} else if (WillBePublished.equals("Yes") && Integer.parseInt(numOfRowsFound) > 0
									&& Scheduled.equals("Yes") && isOutageWithinScheduledRange)
							{
								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								dataAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;
							}
						}
					} else if (outageAffectedService.equals("IPTV") && service.equals("IPTV"))
					{
						// Replace Hierarchy keys from the correct column names of Hierarchy Subscribers
						// table
						HierarchySelected = this.replaceHierarchyColumns(HierarchySelected, "IPTV");

						// Add CLI Value in Hierarcy
						HierarchySelected += "->CliValue=" + CLIProvided;

						// Get root hierarchy String
						String rootElementInHierarchy = hf.getRootHierarchyNode(HierarchySelected);

						// Get Hierarchy Table for that root hierarchy
						String table = dbs.getOneValue(tablePrefix + "HierarchyTablePerTechnology2", "IPTVSubscribersTableName",
								new String[] { "RootHierarchyNode" }, new String[] { rootElementInHierarchy },
								new String[] { "String" });

						String numOfRowsFound = dbs.numberOfRowsFound(table, hf.hierarchyKeys(HierarchySelected),
								hf.hierarchyValues(HierarchySelected), hf.hierarchyStringTypes(HierarchySelected));

						// Scheduled No & Rows Found
						if (WillBePublished.equals("Yes"))
						{
							if (Integer.parseInt(numOfRowsFound) > 0 && Scheduled.equals("No"))
							{
								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								iptvAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Non-scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;
								// Scheduled Yes & Rows Found & Outage Within Scheduled Range
							} else if (WillBePublished.equals("Yes") && Integer.parseInt(numOfRowsFound) > 0
									&& Scheduled.equals("Yes") && isOutageWithinScheduledRange)
							{
								foundIncidentID = IncidentID;
								foundPriority = Priority;
								foundScheduled = Scheduled;
								foundDuration = Duration;
								foundStartTime = StartTime;
								foundEndTime = EndTime;
								foundImpact = Impact;
								foundOutageMsg = OutageMsg;
								foundFlag2_BackupEligible = BackupEligible;

								foundAtLeastOneCLIAffected = true;
								iptvAffected = true;
								logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected CLI: "
										+ CLIProvided + " | " + ServiceType + " from Scheduled INC: " + IncidentID
										+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
										+ foundOutageMsg + " | " + BackupEligible);
								break;
							}
						}
					}
				}
			}

			// CLI is not affected from outage
			if (!foundAtLeastOneCLIAffected)
			{
				// Update Statistics
				s_dbs.updateUsageStatisticsForMethod("NLU_Active_Neg");

				logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - No Service affection for CLI: "
						+ CLIProvided + " | " + ServiceType);

				// Update asynchronously - Add Caller to Caller data table (Test_Caller_Data) with empty values for IncidentID, Affected Services & Scheduling
				Update_CallerDataTable ucdt = new Update_CallerDataTable(dbs, s_dbs, CLIProvided, "", "", "", "", "",
						RequestID, systemID, Company);
				ucdt.run();

				ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "No", "none", "none", "none", "none",
						"none", "none", "none", "NULL", "NULL", "NULL");

				// **************************************
				// Send request and ask CDR DB for Outage
				// **************************************
				WebCDRDBService myWebService = new WebCDRDBService();
				gr.wind.spectra.cdrdbconsumernova.InterfaceWebCDRDB iws = myWebService.getWebCDRDBPort();

				HasOutage ho = new HasOutage();
				Map<String, String> fields = dbs.getCDRDB_Parameters(tablePrefix + "Prov_Internet_Resource_Path", tablePrefix + "AAA21_NMAP",
						new String[] { "A.CliValue", "A.Username",
								"B.Active_Element as \"AAA DLSAM Name\", A.ActiveElement as \"WindOwnedElement\"",
								" A.PASPORT_COID" },
						CLIProvided);

				ho.setAAAUsername(fields.get("Username"));
				ho.setRequestID(RequestID);
				ho.setCompany(Company);

				ho.setCli(CLIProvided);

				// Check if AAA DSLAM exist, if not pick up WindOwnedElement
				if (fields.get("AAA DLSAM Name") == null)
				{
					if (fields.get("WindOwnedElement") != null)
					{
						ho.setDSLAMName(fields.get("WindOwnedElement"));
					} else
					{
						ho.setDSLAMName(null);
					}

				} else
				{
					ho.setDSLAMName(fields.get("AAA DLSAM Name"));
				}

				ho.setCOID(fields.get("PASPORT_COID"));
				ho.setApiProcess(systemID);

				try
				{
					String specificOutageMessage = "msg1";
					gr.wind.spectra.cdrdbconsumernova.HasOutageResponse hor = iws.hasOutage(ho, "spectra",
							"YtfLwvEuCAly9fJS6R46");

					String cdrDBResponse = hor.getResult().getHasOutage(); // "y" or "n"

					if (cdrDBResponse.equals("y"))
					{
						String ActualServicesAffected = "Data|IPTV";

						// Check if Customer has VOIP Telephony - Addition 24 Jan 2022
						String ActualUserServiceType = dbs.getOneValue(tablePrefix + "Prov_Voice_Resource_Path", "ServiceType",
								new String[] { "CliValue" }, new String[] { CLIProvided }, new String[] { "String" });

						if (ActualUserServiceType.equals("SIP_VOIP"))
						{
							ActualServicesAffected = "Voice|Data|IPTV";
						}

						logger.info("SysID: CDR_DB ReqID: " + RequestID + " - Found Affected CLI: " + CLIProvided
								+ " for DSLAM: " + ho.getDSLAMName() + " | msg: " + specificOutageMessage);

						ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "Yes", "CDR-DB", "Critical",
								ActualServicesAffected, "No", "none", "none", "LoS", specificOutageMessage, "N",
								"NULL");

						// Update stats
						s_dbs.updateUsageStatisticsForMethod("CDR_DB_Pos");

					} else
					{
						logger.info("SysID: CDR_DB ReqID: " + RequestID + " - No Service affection for CLI: "
								+ CLIProvided + " for DSLAM: " + ho.getDSLAMName());

						// Update stats
						s_dbs.updateUsageStatisticsForMethod("CDR_DB_Neg");
					}

				} catch (Exception e)
				{

					e.printStackTrace();
				}

			} else
			{
				// Indicate Voice, Data or Voice|Data service affection
				if (voiceAffected && dataAffected && iptvAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Voice");

					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Data");

					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_IPTV");

					allAffectedServices = "Voice|Data|IPTV";
				} else if (voiceAffected && dataAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Voice");

					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Data");

					allAffectedServices = "Voice|Data";
				} else if (voiceAffected && iptvAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Voice");

					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_IPTV");

					allAffectedServices = "Voice|IPTV";
				} else if (dataAffected && iptvAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Data");

					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_IPTV");

					allAffectedServices = "Data|IPTV";
				} else if (voiceAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Voice");

					allAffectedServices = "Voice";
				} else if (dataAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_Data");

					allAffectedServices = "Data";
				} else if (iptvAffected)
				{
					// Update Statistics
					s_dbs.updateUsageStatisticsForMethod("NLU_Active_Pos_IPTV");

					allAffectedServices = "IPTV";
				}

				// Get String representation of EndTime Date object
				// If End Time is NOT set but Duration is set then calculate the new published End Time...
				// else use the EndTime defined from the Sumbission of the ticket
				if (foundEndTime != null)
				{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					EndTimeString = dateFormat.format(foundEndTime);

				} else if (foundDuration != null)
				{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					Calendar cal = Calendar.getInstance(); // creates calendar
					cal.setTime(foundStartTime); // sets calendar time/date
					cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(foundDuration));
					Date myActualEndTime = cal.getTime(); // returns new date object, one hour in the future

					EndTimeString = dateFormat.format(myActualEndTime);
				}

				// Update asynchronously Test_Stats_Pos_NLU_Requests to count number of successful NLU requests per CLI
				Update_ReallyAffectedTable uRat = new Update_ReallyAffectedTable(s_dbs, foundIncidentID,
						allAffectedServices, foundScheduled, CLIProvided);
				uRat.run();

				// foundFlag2_BackupEligible = Yes -> backupEligible = Y
				String backupEligible = "";
				if (foundFlag2_BackupEligible != null)
				{
					if (foundFlag2_BackupEligible.equals("Yes"))
					{
						backupEligible = "Y";
					} else if (foundFlag2_BackupEligible.equals("No"))
					{
						backupEligible = "N";
					} else
					{
						backupEligible = "N";
					}
				} else
				{
					backupEligible = "N";
				}

				// Update asynchronously - Add Caller to Caller data table (Test_Caller_Data) with empty values for IncidentID, Affected Services & Scheduling
				Update_CallerDataTable ucdt = new Update_CallerDataTable(dbs, s_dbs, CLIProvided, foundIncidentID,
						allAffectedServices, foundScheduled, foundOutageMsg, backupEligible, RequestID, systemID, Company);
				ucdt.run();

				ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "Yes", foundIncidentID, foundPriority,
						allAffectedServices, foundScheduled, foundDuration, EndTimeString, foundImpact, foundOutageMsg,
						backupEligible, "NULL");
			}

		} else
		{
			// Update Statistics
			s_dbs.updateUsageStatisticsForMethod("NLU_Active_Neg");

			// Update asynchronously - Add Caller to Caller data table (Test_Caller_Data) with empty values for IncidentID, Affected Services & Scheduling
			Update_CallerDataTable ucdt = new Update_CallerDataTable(dbs, s_dbs, CLIProvided, "", "", "", "", "",
					RequestID, systemID, Company);
			ucdt.run();

			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - No Service affection for CLI: "
					+ CLIProvided + " | " + ServiceType);

			ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "No", "none", "none", "none", "none", "none",
					"none", "none", "NULL", "NULL", "NULL");

			// **************************************
			// Send request and ask CDR DB for Outage
			// **************************************
			WebCDRDBService myWebService = new WebCDRDBService();
			gr.wind.spectra.cdrdbconsumernova.InterfaceWebCDRDB iws = myWebService.getWebCDRDBPort();

			HasOutage ho = new HasOutage();
			Map<String, String> fields = dbs.getCDRDB_Parameters(tablePrefix + "Prov_Internet_Resource_Path", tablePrefix + "AAA21_NMAP",
					new String[] { "A.CliValue", "A.Username",
							"B.Active_Element as \"AAA DLSAM Name\", A.ActiveElement as \"WindOwnedElement\"",
							" A.PASPORT_COID" },
					CLIProvided);

			ho.setAAAUsername(fields.get("Username"));
			ho.setRequestID(RequestID);
			ho.setCompany(Company);
			ho.setCli(CLIProvided);

			// Check if AAA DSLAM exist, if not pick up WindOwnedElement
			if (fields.get("AAA DLSAM Name") == null)
			{
				if (fields.get("WindOwnedElement") != null)
				{
					ho.setDSLAMName(fields.get("WindOwnedElement"));
				} else
				{
					ho.setDSLAMName(null);
				}

			} else
			{
				ho.setDSLAMName(fields.get("AAA DLSAM Name"));
			}

			ho.setCOID(fields.get("PASPORT_COID"));
			ho.setApiProcess(systemID);

			try
			{
				String specificOutageMessage = "msg1";
				gr.wind.spectra.cdrdbconsumernova.HasOutageResponse hor = iws.hasOutage(ho, "spectra",
						"YtfLwvEuCAly9fJS6R46");

				String cdrDBResponse = hor.getResult().getHasOutage(); // "y" or "n"

				if (cdrDBResponse.equals("y"))
				{
					String ActualServicesAffected = "Data|IPTV";

					// Check if Customer has VOIP Telephony - Addition 24 Jan 2022
					String ActualUserServiceType = dbs.getOneValue(tablePrefix + "Prov_Voice_Resource_Path", "ServiceType",
							new String[] { "CliValue" }, new String[] { CLIProvided }, new String[] { "String" });

					   // For Wind                               ||  For Nova
					if (ActualUserServiceType.equals("SIP_VOIP") || ActualUserServiceType.equals("SIP"))
					{
						ActualServicesAffected = "Voice|Data|IPTV";
					}

					logger.info("SysID: CDR_DB ReqID: " + RequestID + " - Found Affected CLI: " + CLIProvided
							+ " for DSLAM: " + ho.getDSLAMName() + " | msg: " + specificOutageMessage);

					// Update stats
					s_dbs.updateUsageStatisticsForMethod("CDR_DB_Pos");

					ponla = new ProductOfNLUActive(this.requestID, CLIProvided, "Yes", "CDR-DB", "Critical",
							ActualServicesAffected, "No", "none", "none", "LoS", specificOutageMessage, "N", "NULL");
				} else
				{
					logger.info("SysID: CDR_DB ReqID: " + RequestID + " - No Service affection for CLI: " + CLIProvided
							+ " for DSLAM: " + ho.getDSLAMName());

					// Update stats
					s_dbs.updateUsageStatisticsForMethod("CDR_DB_Neg");
				}

			} catch (Exception e)
			{

				e.printStackTrace();
			}
		}

		dbs = null;
		s_dbs = null;
		requestID = null;

		return ponla;
	}

}