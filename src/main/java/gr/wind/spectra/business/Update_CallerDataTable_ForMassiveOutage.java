package gr.wind.spectra.business;

import gr.wind.spectra.cdrdbconsumernova.InsertCallerData;
import gr.wind.spectra.cdrdbconsumernova.WebCDRDBService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.*;

public class Update_CallerDataTable_ForMassiveOutage extends Thread
{
	iDB_Operations dbs;
	iStatic_DB_Operations s_dbs;
	String CLIProvided;
	String Company;
	String IncidentID;
	String allAffectedServices;
	String foundScheduled;
	String message;
	String backupEligible;
	String requestID;
	String systemID;

	private String tablePrefix;
	private final String windTableNamePrefix = "";
	private final String novaTableNamePrefix = "Nova_";

	public Update_CallerDataTable_ForMassiveOutage(iDB_Operations dbs, iStatic_DB_Operations s_dbs, String CLIProvided, String IncidentID,
                                                   String allAffectedServices, String foundScheduled, String message, String backupEligible, String requestID,
                                                   String systemID, String Company)
	{
		this.dbs = dbs;
		this.s_dbs = s_dbs;
		this.CLIProvided = CLIProvided;
		this.IncidentID = IncidentID;
		this.allAffectedServices = allAffectedServices;
		this.foundScheduled = foundScheduled;
		this.message = message;
		this.backupEligible = backupEligible;
		this.requestID = requestID;
		this.systemID = systemID;
		this.Company = Company;


		// Check if Export is for Nova or Wind
		if (dbs.getClass().toString().equals("class gr.wind.spectra.business.DB_Operations")) {
			this.tablePrefix = windTableNamePrefix;
		} else if (dbs.getClass().toString().equals("class gr.wind.spectra.business.TnovaDynamicDBOperations")) {
			this.tablePrefix = novaTableNamePrefix;

		}
	}

	@Override
	public void run()
	{
		// System.out.println("Running thread for Test Update_CallerDataTable...");
		Help_Func hf = new Help_Func();

		if (message == null) {
			message = "";
		}

		try
		{
				s_dbs.insertValuesInTable(tablePrefix + "Test_Caller_Data",
						new String[] { "Requestor", "CliValue", "DateTimeCalled", "Affected_by_IncidentID",
								"AffectedServices", "Scheduled", "Message", "BackupEligible", "CSSCOLLECTIONNAME",
								"PAYTVSERVICES", "NGA_TYPE", "GeneralArea", "SiteName", "Concentrator", "AccessService",
								"PoP_Name", "PoP_Code", "OltElementName", "OltRackNo", "OltSubRackNo", "OltSlot",
								"OltPort", "Onu", "KvCode", "CabinetCode", "ActiveElement", "Rack", "Subrack", "Slot",
								"Port", "PORT_LOCATION", "PORT_CABLE_CODE", "PORT_ID", "CLID", "Username",
								"PASPORT_COID", "LOOP_NUMBER", "CLI_TYPE", "Domain", "ServiceType", "BRASNAME" },
						new String[] { systemID, CLIProvided, hf.now(), IncidentID, allAffectedServices, foundScheduled, message, backupEligible, "", "", "", "", "", "", "",
								"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
								"", "", "", "" },
						new String[] { "String", "String", "DateTime", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String" });

				SendRequestToCDRDBNOTFoundCLI();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SendRequestToCDRDBNOTFoundCLI()
	{
		// How do I call some blocking method with a timeout in Java?
		// https://stackoverflow.com/questions/1164301/how-do-i-call-some-blocking-method-with-a-timeout-in-java

		ExecutorService executor = Executors.newCachedThreadPool();
		Callable<Object> task = new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				// ****************************************************
				// Send request CDR DB for Archiving the Caller request
				// *****************************************************
				try
				{
					WebCDRDBService myWebService = new WebCDRDBService();
					gr.wind.spectra.cdrdbconsumernova.InterfaceWebCDRDB iws = myWebService.getWebCDRDBPort();

					InsertCallerData icd = new InsertCallerData();

					icd.setRequestID(requestID);
					icd.setCompany(Company);
					icd.setUsername("");
					icd.setCli(CLIProvided);
					icd.setIncidentNumber(IncidentID);
					icd.setAffectedServices(allAffectedServices);
					icd.setPayTVServices("");
					icd.setIsScheduled(foundScheduled);
					icd.setBackupElegible(backupEligible);
					icd.setCSSCollectionName("");
					icd.setAccessService("");
					icd.setCLID("");
					icd.setActiveElement("");
					icd.setBRASName("");
					icd.setCOID("");
					icd.setAPIUser("spectra");
					icd.setAPIProcess(systemID);

					iws.insertCallerData(icd, "spectra", "YtfLwvEuCAly9fJS6R46");

				} catch (Exception e)
				{

					e.printStackTrace();
				}
				return null;

			};

		};

		Future<Object> future = executor.submit(task);
		try
		{
			Object result = future.get(300, TimeUnit.MILLISECONDS);
		} catch (TimeoutException ex)
		{
			// handle the timeout
			System.out.println("Update_CallerDataTable TimeoutException for CDRDB Insert Statement: " + requestID);
		} catch (InterruptedException e)
		{
			// handle the interrupts
			System.out.println("Update_CallerDataTable InterruptedException for CDRDB Insert Statement: " + requestID);
		} catch (ExecutionException e)
		{
			// handle other exceptions
			System.out.println("Update_CallerDataTable ExecutionException for CDRDB Insert Statement: " + requestID);
		} finally
		{
			future.cancel(true); // may or may not desire this
		}
	}

}