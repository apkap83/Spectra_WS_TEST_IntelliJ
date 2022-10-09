package gr.wind.spectra.business;

import java.sql.SQLException;

public class Update_ReallyAffectedTable extends Thread
{
	s_DB_Operations s_dbs;
	String foundIncidentID;
	String allAffectedServices;
	String foundScheduled;
	String CLIProvided;

	public Update_ReallyAffectedTable(s_DB_Operations s_dbs, String foundIncidentID, String allAffectedServices,
			String foundScheduled, String CLIProvided)
	{
		this.s_dbs = s_dbs;
		this.foundIncidentID = foundIncidentID;
		this.allAffectedServices = allAffectedServices;
		this.foundScheduled = foundScheduled;
		this.CLIProvided = CLIProvided;

	}

	@Override
	public void run()
	{
		String numOfTimesCliCalledForIncident = "0";

		// Check if CLI for this Incident exists
		// Check if we have at least one OPEN incident
		try
		{
			numOfTimesCliCalledForIncident = s_dbs.numberOfRowsFound("Test_Stats_Pos_NLU_Requests",
					new String[] { "IncidentID", "CliValue" }, new String[] { foundIncidentID, CLIProvided },
					new String[] { "String", "String" });

			// If CLI has not called again then insert line
			if (numOfTimesCliCalledForIncident.equals("0"))
			{
				s_dbs.insertValuesInTable("Test_Stats_Pos_NLU_Requests",
						new String[] { "IncidentID", "AffectedService", "Scheduled", "CliValue" },
						new String[] { foundIncidentID, allAffectedServices, foundScheduled, CLIProvided },
						new String[] { "String", "String", "String", "String", "String" });
			}
			// CLI has called again for this specific incident
			else
			{
				// Update value using LAST_INSERT_ID method of MySQL e.g. SET ModifyOutage = LAST_INSERT_ID(ModifyOutage+1)
				s_dbs.updateValuesBasedOnLastInsertID("Test_Stats_Pos_NLU_Requests", "TimesCalled",
						new String[] { "IncidentID", "CliValue" }, new String[] { foundIncidentID, CLIProvided },
						new String[] { "String", "String" });

			}

		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
