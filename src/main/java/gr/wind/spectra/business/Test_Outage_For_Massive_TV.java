package gr.wind.spectra.business;

import gr.wind.spectra.cdrdbconsumernova.HasOutage;
import gr.wind.spectra.cdrdbconsumernova.WebCDRDBService;
import gr.wind.spectra.model.ProductOfNLUActive;
import gr.wind.spectra.web.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Scheduled;

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

public class Test_Outage_For_Massive_TV {
	private iDB_Operations dbs;
	private iStatic_DB_Operations s_dbs;
	private String requestID;
	private String systemID;

	private final String OTT_OUTAGE_HIERARCHY = "Massive_TV_Outage->TV_Service=ALL_EON_Boxes";
	private final String SATELLITE_OUTAGE_HIERARCHY = "Massive_TV_Outage->TV_Service=ALL_Satellite_Boxes";
	Help_Func hf = new Help_Func();

	DateFormat dateFormat = new SimpleDateFormat(hf.DATE_FORMAT);

	// Logger instance
	private static final Logger logger = LogManager.getLogger(Test_Outage_For_Massive_TV.class.getName());

	public Test_Outage_For_Massive_TV(iDB_Operations dbs, iStatic_DB_Operations s_dbs, String requestID, String systemID) throws Exception {
		this.dbs = dbs;
		this.s_dbs = s_dbs;
		this.requestID = requestID;
		this.systemID = systemID;

	}

	public ProductOfNLUActive checkMassiveTVOutage(String RequestID, String TV_ID)
			throws SQLException, InvalidInputException, ParseException
	{
		ProductOfNLUActive ponla = new ProductOfNLUActive();
		boolean foundAtLeastOneCLIAffected = false;
		boolean voiceAffected = false;
		boolean dataAffected = false;
		boolean iptvAffected = false;
		String allAffectedServices = "";

		Help_Func hf = new Help_Func();

		// Check if TV_ID Exists in our Database
		boolean TV_ID_Found_in_Satellite = dbs.checkIfStringExistsInSpecificColumn("OTT_DTH_Data",
				"TV_ID", TV_ID);

		// If it doesn't exist then Exit
		if (!TV_ID_Found_in_Satellite) {
			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - TV_ID: "
					+ TV_ID + " was not found in Table: OTT_DTH_Data");

			return new ProductOfNLUActive(this.requestID, TV_ID, "No", "none", "none", "none", "none",
					"none", "none", "none", "NULL", "NULL", "NULL");
		}

		// Get the Value of TV_Service for that TV_ID - Possible Values: OTT or DTH
		String TypeOfTV_ID = dbs.getOneValue("OTT_DTH_Data", "TV_Service", new String[]{"TV_ID"}, new String[]{TV_ID}, new String[]{"String"});
		TypeOfTV_ID = TypeOfTV_ID.trim();

		logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Checking Massive TV Outage For " + TypeOfTV_ID + " TV_ID: " + TV_ID);

		// Check if it is OTT or DTH - If not then Exit
		if (!TypeOfTV_ID.equals("OTT") && !TypeOfTV_ID.equals("DTH")) {
			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - TV_ID: "
					+ TV_ID + " has TV_Service: " + TypeOfTV_ID + " - Expected OTT or DTH Only - Aborting Check");

			return new ProductOfNLUActive(this.requestID, TV_ID, "No", "none", "none", "none", "none",
					"none", "none", "none", "NULL", "NULL", "NULL");
		}

		// Check if we have Open EON TV Outage Incident
		boolean weHaveMassiveEONIncident = s_dbs.checkIfCriteriaExists("Test_SubmittedIncidents", new String[]{"IncidentStatus", "HierarchySelected"},
				new String[]{"OPEN", OTT_OUTAGE_HIERARCHY}, new String[]{"String", "String"});

		// Check if we have Open Satellite TV Outage Incident
		boolean weHaveMassiveSatelliteIncident = s_dbs.checkIfCriteriaExists("Test_SubmittedIncidents", new String[]{"IncidentStatus", "HierarchySelected"},
				new String[]{"OPEN", SATELLITE_OUTAGE_HIERARCHY}, new String[]{"String", "String"});

		if (TypeOfTV_ID.equals("OTT") && weHaveMassiveEONIncident) {

			try {
				ResultSet rs = null;
				// Get Lines with IncidentStatus = "OPEN"
				rs = s_dbs.getRows("Test_SubmittedIncidents",
						new String[]{"WillBePublished", "IncidentID", "OutageID", "BackupEligible",
								"HierarchySelected", "Priority", "AffectedServices", "Scheduled", "Duration",
								"StartTime", "EndTime", "Impact", "OutageMsg"},
						new String[]{"HierarchySelected"}, new String[]{OTT_OUTAGE_HIERARCHY}, new String[]{"String"});

				String WillBePublished = null;
				String IncidentID = null;
				int OutageID = 0;
				String HierarchySelected = null;
				String Priority = null;
				String outageAffectedService = null;
				String Scheduled = null;
				String Duration = null;
				Date StartTime = null;
				Date EndTime = null;
				String Impact = null;
				String OutageMsg = null;
				String BackupEligible = null;

				while (rs.next()) {
					WillBePublished = rs.getString("WillBePublished");
					IncidentID = rs.getString("IncidentID");
					OutageID = rs.getInt("OutageID");
					HierarchySelected = rs.getString("HierarchySelected");
					Priority = rs.getString("Priority");
					outageAffectedService = rs.getString("AffectedServices");
					Scheduled = rs.getString("Scheduled");
					Duration = rs.getString("Duration");
					StartTime = rs.getTimestamp("StartTime");
					EndTime = rs.getTimestamp("EndTime");
					Impact = rs.getString("Impact");
					OutageMsg = rs.getString("OutageMsg");
					BackupEligible = rs.getString("BackupEligible");

				}

				String EndTimeString = null;

				// Get String representation of EndTime Date object
				// If End Time is NOT set but Duration is set then calculate the new published End Time...
				// else use the EndTime defined from the Sumbission of the ticket
				if (EndTime != null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					EndTimeString = dateFormat.format(EndTime);

				} else if (Duration != null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					Calendar cal = Calendar.getInstance(); // creates calendar
					cal.setTime(StartTime); // sets calendar time/date
					cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(Duration));
					Date myActualEndTime = cal.getTime(); // returns new date object, one hour in the future

					EndTimeString = dateFormat.format(myActualEndTime);
				}

				logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected OTT TV_ID: "
						+ TV_ID + " | " + IncidentID
						+ " | OutageID: " + OutageID + " | " + "IPTV" + " | "
						+ OutageMsg + " | " + BackupEligible);

				return new ProductOfNLUActive(this.requestID, TV_ID, "Yes", IncidentID, "Critical",
						"IPTV", Scheduled, Duration, EndTimeString, Impact, OutageMsg, BackupEligible, "NULL");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		if (TypeOfTV_ID.equals("DTH") && weHaveMassiveSatelliteIncident) {

			try {

			ResultSet rs = null;
			// Get Lines with IncidentStatus = "OPEN"
			rs = s_dbs.getRows("Test_SubmittedIncidents",
					new String[]{"WillBePublished", "IncidentID", "OutageID", "BackupEligible",
							"HierarchySelected", "Priority", "AffectedServices", "Scheduled", "Duration",
							"StartTime", "EndTime", "Impact", "OutageMsg"},
					new String[]{"HierarchySelected"}, new String[]{SATELLITE_OUTAGE_HIERARCHY}, new String[]{"String"});

			String WillBePublished = null;
			String IncidentID = null;
			int OutageID = 0;
			String HierarchySelected = null;
			String Priority = null;
			String outageAffectedService = null;
			String Scheduled = null;
			String Duration = null;
			Date StartTime = null;
			Date EndTime = null;
			String Impact = null;
			String OutageMsg = null;
			String BackupEligible = null;

			while (rs.next()) {
				WillBePublished = rs.getString("WillBePublished");
				IncidentID = rs.getString("IncidentID");
				OutageID = rs.getInt("OutageID");
				HierarchySelected = rs.getString("HierarchySelected");
				Priority = rs.getString("Priority");
				outageAffectedService = rs.getString("AffectedServices");
				Scheduled = rs.getString("Scheduled");
				Duration = rs.getString("Duration");
				StartTime = rs.getTimestamp("StartTime");
				EndTime = rs.getTimestamp("EndTime");
				Impact = rs.getString("Impact");
				OutageMsg = rs.getString("OutageMsg");
				BackupEligible = rs.getString("BackupEligible");

			}

			String EndTimeString = null;

			// Get String representation of EndTime Date object
			// If End Time is NOT set but Duration is set then calculate the new published End Time...
			// else use the EndTime defined from the Sumbission of the ticket
			if (EndTime != null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				EndTimeString = dateFormat.format(EndTime);

			} else if (Duration != null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(StartTime); // sets calendar time/date
				cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(Duration));
				Date myActualEndTime = cal.getTime(); // returns new date object, one hour in the future

				EndTimeString = dateFormat.format(myActualEndTime);
			}

			logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - Found Affected DTH TV_ID: "
					+ TV_ID + " from Massive INC: " + IncidentID
					+ " | OutageID: " + OutageID + " | " + outageAffectedService + " | "
					+ OutageMsg + " | " + BackupEligible);

			return new ProductOfNLUActive(this.requestID, TV_ID, "Yes", IncidentID, "Critical",
					"IPTV", Scheduled, Duration, EndTimeString, Impact, OutageMsg, BackupEligible, "NULL");

		} catch (Exception e) {
			e.printStackTrace();
		}
		}

		dbs = null;
		s_dbs = null;
		requestID = null;


		// Default No Outage for TV_ID
		logger.info("SysID: " + systemID + " ReqID: " + RequestID + " - No Service affection for TV_ID: "
				+ TV_ID);

		return new ProductOfNLUActive(this.requestID, TV_ID, "No", "none", "none", "none", "none",
				"none", "none", "none", "NULL", "NULL", "NULL");
	}

}
