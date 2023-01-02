package gr.wind.spectra.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import gr.wind.spectra.cdrdbconsumernova.InsertCallerData;
import gr.wind.spectra.cdrdbconsumernova.WebCDRDBService;

public class Update_CallerDataTable extends Thread
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

	public Update_CallerDataTable(iDB_Operations dbs, iStatic_DB_Operations s_dbs, String CLIProvided, String IncidentID,
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

		try
		{
			// Check if cli value exists in Prov_Internet_Resource_Path CliValue column
			//boolean cliValueExistsInResourceTable = dbs.checkIfStringExistsInSpecificColumn("Prov_Internet_Resource_Path",
			//		"CliValue", CLIProvided);

			// Get number of rows
			String numOfRowsFound = dbs.numberOfRowsFound(tablePrefix + "Prov_Internet_Resource_Path", new String[] { "CliValue" },
					new String[] { CLIProvided }, new String[] { "String" });

			if (Integer.parseInt(numOfRowsFound) > 0) // CLI was found in Internet_Resource_Path
			{
				ResultSet rs = null;

				// Get Lines from Internet Resource Path
				rs = dbs.getRows(tablePrefix + "Prov_Internet_Resource_Path",
						new String[] { "NGA_TYPE", "GeneralArea", "SiteName", "Concentrator", "AccessService",
								"PoP_Name", "PoP_Code", "OltElementName", "OltRackNo", "OltSubRackNo", "OltSlot",
								"OltPort", "Onu", "KvCode", "CabinetCode", "ActiveElement", "Rack", "Subrack", "Slot",
								"Port", "PORT_LOCATION", "PORT_CABLE_CODE", "PORT_ID", "CLID", "Username",
								"PASPORT_COID", "LOOP_NUMBER", "CLI_TYPE", "Domain", "ServiceType", "BRASNAME" },
						new String[] { "CliValue" }, new String[] { CLIProvided }, new String[] { "String" });

				int timesFound = 0;
				while (rs.next())
				{
					timesFound += 1;
					if (timesFound > 1)
					{
						break; // Only 1 entry will be logged (even if subscriber belongs to 2 BRAS)
					}

					String NGA_TYPE = rs.getString("NGA_TYPE");
					String GeneralArea = rs.getString("GeneralArea");
					String SiteName = rs.getString("SiteName");
					String Concentrator = rs.getString("Concentrator");
					String AccessService = rs.getString("AccessService");
					String PoP_Name = rs.getString("PoP_Name");
					String PoP_Code = rs.getString("PoP_Code");
					String OltElementName = rs.getString("OltElementName");
					String OltRackNo = rs.getString("OltRackNo");
					String OltSubRackNo = rs.getString("OltSubRackNo");
					String OltSlot = rs.getString("OltSlot");
					String OltPort = rs.getString("OltPort");
					String Onu = rs.getString("Onu");
					String KvCode = rs.getString("KvCode");
					String CabinetCode = rs.getString("CabinetCode");
					String ActiveElement = rs.getString("ActiveElement");
					String Rack = rs.getString("Rack");
					String Subrack = rs.getString("Subrack");
					String Slot = rs.getString("Slot");
					String Port = rs.getString("Port");
					String PORT_LOCATION = rs.getString("PORT_LOCATION");
					String PORT_CABLE_CODE = rs.getString("PORT_CABLE_CODE");
					String PORT_ID = rs.getString("PORT_ID");
					String CLID = rs.getString("CLID");
					String Username = rs.getString("Username");
					String PASPORT_COID = rs.getString("PASPORT_COID");
					String LOOP_NUMBER = rs.getString("LOOP_NUMBER");
					String CLI_TYPE = rs.getString("CLI_TYPE");
					String Domain = rs.getString("Domain");
					String ServiceType = rs.getString("ServiceType");
					String BRASNAME = rs.getString("BRASNAME");
					String CSSCOLLECTIONNAME = "";
					String PAYTVSERVICES = "";

					if (NGA_TYPE == null)
					{
						NGA_TYPE = "";
					}
					if (GeneralArea == null)
					{
						GeneralArea = "";
					}
					if (SiteName == null)
					{
						SiteName = "";
					}
					if (Concentrator == null)
					{
						Concentrator = "";
					}
					if (AccessService == null)
					{
						AccessService = "";
					}
					if (PoP_Name == null)
					{
						PoP_Name = "";
					}
					if (PoP_Code == null)
					{
						PoP_Code = "";
					}
					if (OltElementName == null)
					{
						OltElementName = "";
					}
					if (OltRackNo == null)
					{
						OltRackNo = "";
					}
					if (OltSubRackNo == null)
					{
						OltSubRackNo = "";
					}
					if (OltSlot == null)
					{
						OltSlot = "";
					}
					if (OltPort == null)
					{
						OltPort = "";
					}
					if (Onu == null)
					{
						Onu = "";
					}
					if (KvCode == null)
					{
						KvCode = "";
					}
					if (CabinetCode == null)
					{
						CabinetCode = "";
					}
					if (ActiveElement == null)
					{
						ActiveElement = "";
					}
					if (Rack == null)
					{
						Rack = "";
					}
					if (Subrack == null)
					{
						Subrack = "";
					}
					if (Slot == null)
					{
						Slot = "";
					}
					if (Port == null)
					{
						Port = "";
					}
					if (PORT_LOCATION == null)
					{
						PORT_LOCATION = "";
					}
					if (PORT_CABLE_CODE == null)
					{
						PORT_CABLE_CODE = "";
					}
					if (PORT_ID == null)
					{
						PORT_ID = "";
					}
					if (CLID == null)
					{
						CLID = "";
					}
					if (Username == null)
					{
						Username = "";
					}
					if (PASPORT_COID == null)
					{
						PASPORT_COID = "";
					}
					if (LOOP_NUMBER == null)
					{
						LOOP_NUMBER = "";
					}
					if (CLI_TYPE == null)
					{
						CLI_TYPE = "";
					}
					if (Domain == null)
					{
						Domain = "";
					}
					if (ServiceType == null)
					{
						ServiceType = "";
					}
					if (BRASNAME == null)
					{
						BRASNAME = "";
					}
					if (message == null)
					{
						message = "";
					}
					if (backupEligible == null)
					{
						backupEligible = "";
					}

					// Get CSSCOLLECTIONNAME from AAA21_NMAP based on the Username from Prov_Internet_Resource_Path

					if (!Username.equals("")) // Only if user name is not empty
					{
						CSSCOLLECTIONNAME = dbs.getOneValue(tablePrefix + "AAA21_NMAP", "CSSCOLLECTIONNAME",
								new String[] { "USERNAME" }, new String[] { Username }, new String[] { "String" });

						PAYTVSERVICES = dbs.getOneValue(tablePrefix + "Prov_IPTV_Resource_Path", "PAYTVSERVICES",
								new String[] { "Username" }, new String[] { Username }, new String[] { "String" });
					} else
					{
						CSSCOLLECTIONNAME = "";
						PAYTVSERVICES = "";
					}

					if (CSSCOLLECTIONNAME == null)
					{
						CSSCOLLECTIONNAME = "";
					}
					if (PAYTVSERVICES == null)
					{
						PAYTVSERVICES = "";
					}

					s_dbs.insertValuesInTable(tablePrefix + "Test_Caller_Data", new String[] { "Requestor", "CliValue", "DateTimeCalled",
									"Affected_by_IncidentID", "AffectedServices", "Scheduled", "Message", "BackupEligible",
									"CSSCOLLECTIONNAME", "PAYTVSERVICES", "NGA_TYPE", "GeneralArea", "SiteName", "Concentrator",
									"AccessService", "PoP_Name", "PoP_Code", "OltElementName", "OltRackNo", "OltSubRackNo",
									"OltSlot", "OltPort", "Onu", "KvCode", "CabinetCode", "ActiveElement", "Rack", "Subrack",
									"Slot", "Port", "PORT_LOCATION", "PORT_CABLE_CODE", "PORT_ID", "CLID", "Username",
									"PASPORT_COID", "LOOP_NUMBER", "CLI_TYPE", "Domain", "ServiceType", "BRASNAME" },
							new String[] { systemID, CLIProvided, hf.now(), IncidentID, allAffectedServices,
									foundScheduled, message, backupEligible, CSSCOLLECTIONNAME, PAYTVSERVICES, NGA_TYPE,
									GeneralArea, SiteName, Concentrator, AccessService, PoP_Name, PoP_Code,
									OltElementName, OltRackNo, OltSubRackNo, OltSlot, OltPort, Onu, KvCode, CabinetCode,
									ActiveElement, Rack, Subrack, Slot, Port, PORT_LOCATION, PORT_CABLE_CODE, PORT_ID,
									CLID, Username, PASPORT_COID, LOOP_NUMBER, CLI_TYPE, Domain, ServiceType,
									BRASNAME },
							new String[] { "String", "String", "DateTime", "String", "String", "String", "String",
									"String", "String", "String", "String", "String", "String", "String", "String",
									"String", "String", "String", "String", "String", "String", "String", "String",
									"String", "String", "String", "String", "String", "String", "String", "String",
									"String", "String", "String", "String", "String", "String", "String", "String",
									"String", "String" });

					SendRequestToCDRDBFoundCLI(Username, PAYTVSERVICES, CSSCOLLECTIONNAME, AccessService, CLID,
							ActiveElement, BRASNAME, PASPORT_COID);

				}

			} else /// CLI was not found in Internet_Resource_Path
			{
				s_dbs.insertValuesInTable(tablePrefix + "Test_Caller_Data",
						new String[] { "Requestor", "CliValue", "DateTimeCalled", "Affected_by_IncidentID",
								"AffectedServices", "Scheduled", "Message", "BackupEligible", "CSSCOLLECTIONNAME",
								"PAYTVSERVICES", "NGA_TYPE", "GeneralArea", "SiteName", "Concentrator", "AccessService",
								"PoP_Name", "PoP_Code", "OltElementName", "OltRackNo", "OltSubRackNo", "OltSlot",
								"OltPort", "Onu", "KvCode", "CabinetCode", "ActiveElement", "Rack", "Subrack", "Slot",
								"Port", "PORT_LOCATION", "PORT_CABLE_CODE", "PORT_ID", "CLID", "Username",
								"PASPORT_COID", "LOOP_NUMBER", "CLI_TYPE", "Domain", "ServiceType", "BRASNAME" },
						new String[] { systemID, CLIProvided, hf.now(), "", "", "", "", "", "", "", "", "", "", "", "",
								"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
								"", "", "", "" },
						new String[] { "String", "String", "DateTime", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String", "String", "String", "String", "String", "String", "String", "String",
								"String" });

				SendRequestToCDRDBNOTFoundCLI();
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SendRequestToCDRDBFoundCLI(String Username, String PAYTVSERVICES, String CSSCOLLECTIONNAME,
											String AccessService, String CLID, String ActiveElement, String BRASNAME, String PASPORT_COID)
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
					icd.setUsername(Username);
					icd.setCli(CLIProvided);
					icd.setIncidentNumber(IncidentID);
					icd.setAffectedServices(allAffectedServices);
					icd.setPayTVServices(PAYTVSERVICES);
					icd.setIsScheduled(foundScheduled);
					icd.setBackupElegible(backupEligible);
					icd.setCSSCollectionName(CSSCOLLECTIONNAME);
					icd.setAccessService(AccessService);
					icd.setCLID(CLID);
					icd.setActiveElement(ActiveElement);
					icd.setBRASName(BRASNAME);
					icd.setCOID(PASPORT_COID);
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