package gr.wind.spectra.business;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVWriter;

public class SQLStatementToCSV extends Thread
{
	// Define a static logger variable so that it references the
	// Logger instance named "DB_Connection".
	Logger logger = LogManager.getLogger(gr.wind.spectra.business.SQLStatementToCSV.class);

	private DB_Connection conObj;
	private Connection conn;

	private Connection s_conn;
	private s_DB_Connection s_conObj;
	private s_DB_Operations s_dbs;

	private String exportedFileName;
	private String[] predicateKeys;
	private String[] predicateValues;
	private String[] predicateTypes;
	private String ngaTypes;

	private String[] columnsForExport;
	String sqlQuery;

	public SQLStatementToCSV(String exportedFileName, String table, String[] columnsForExport, String[] predicateKeys,
			String[] predicateValues, String[] predicateTypes, String ngaTypes)
	{
		this.exportedFileName = exportedFileName;
		this.columnsForExport = columnsForExport;
		this.predicateKeys = predicateKeys;
		this.predicateValues = predicateValues;
		this.predicateTypes = predicateTypes;
		this.ngaTypes = ngaTypes;

		Help_Func hf = new Help_Func();

		// Convert NGA_TYPES to --> AND NGA_TYPE IN ('1', '2', '3')
		String ngaTypesToSQLPredicate = hf.ngaTypesToSqlInFormat(this.ngaTypes);

		// If NgaPredicate is ALL then dont's set [ ngapredicate IN ('value1', 'value2', 'value3',) ]
		if (ngaTypes.equals("ALL"))
		{
			sqlQuery = "SELECT DISTINCT " + hf.columnsWithCommas(columnsForExport) + " FROM " + table + " WHERE "
					+ hf.generateANDPredicateQuestionMarks(predicateKeys);

		} else
		{
			sqlQuery = "SELECT DISTINCT " + hf.columnsWithCommas(columnsForExport) + " FROM " + table + " WHERE "
					+ hf.generateANDPredicateQuestionMarks(predicateKeys) + " " + ngaTypesToSQLPredicate;
		}

		// logger.info("SQLStatementToCSV Query:" + sqlQuery);
	}

	public void establishDBConnection() throws Exception
	{
		try
		{
			this.conObj = new DB_Connection();
			this.conn = this.conObj.connect();
			//			this.dbs = new DB_Operations(conn);
		} catch (Exception ex)
		{
			throw new Exception(ex.getMessage());
		}
	}

	public void establishStaticTablesDBConnection() throws Exception
	{
		//System.out.println("Client IP = " + req.getRemoteAddr());

		if (s_conn == null)
		{
			try
			{
				this.s_conObj = new s_DB_Connection();
				this.s_conn = this.s_conObj.connect();
				this.s_dbs = new s_DB_Operations(s_conn);
			} catch (Exception ex)
			{
				logger.fatal("Could not open connection with database!");
				throw new Exception(ex.getMessage());
			}
		}
	}

	@Override
	public void run()
	{
		try
		{
			this.establishDBConnection();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		//logger.trace("Thread execution in ResultSetToCSV Class Started!");
		try
		{
			// System.out.println(sqlQuery);

			// Export Affected CLIs to File
			PreparedStatement pst = conn.prepareStatement(sqlQuery);
			for (int i = 0; i < predicateKeys.length; i++)
			{
				if (predicateTypes[i].equals("String"))
				{
					pst.setString(i + 1, predicateValues[i]);
				} else if (predicateTypes[i].equals("Integer"))
				{
					pst.setInt(i + 1, Integer.parseInt(predicateValues[i]));
				}
			}

			ResultSet rs = pst.executeQuery();
			CSVWriter csvWriter = new CSVWriter(new FileWriter(exportedFileName), ',');
			csvWriter.writeAll(rs, false);
			csvWriter.close();

			//logger.trace("Thread execution CLIs to CSV Finished!");

			// Export Affected CLIs to Database table
			PreparedStatement pst_forDB = conn.prepareStatement(sqlQuery);
			for (int i = 0; i < predicateKeys.length; i++)
			{
				if (predicateTypes[i].equals("String"))
				{
					pst_forDB.setString(i + 1, predicateValues[i]);
				} else if (predicateTypes[i].equals("Integer"))
				{
					pst_forDB.setInt(i + 1, Integer.parseInt(predicateValues[i]));
				}
			}

			// Establish Db Connection
			this.establishStaticTablesDBConnection();
			ResultSet rs_forDB = pst_forDB.executeQuery();

			// Remove Quotes from all columns for DB Export
			columnsForExport[1] = columnsForExport[1].replace("'", "");
			columnsForExport[2] = columnsForExport[2].replace("'", "");
			columnsForExport[3] = columnsForExport[3].replace("'", "");
			columnsForExport[4] = columnsForExport[4].replace("'", "");
			columnsForExport[5] = columnsForExport[5].replace("'", "");
			columnsForExport[6] = columnsForExport[6].replace("'", "");
			columnsForExport[7] = columnsForExport[7].replace("'", "");
			columnsForExport[8] = columnsForExport[8].replace("'", "");
			columnsForExport[9] = columnsForExport[9].replace("'", "");
			columnsForExport[10] = columnsForExport[10].replace("'", "");
			columnsForExport[11] = columnsForExport[11].replace("'", "");

			while (rs_forDB.next())
			{

				String CliValue = rs_forDB.getString("CliValue");

				// Check if it was null
				if (rs_forDB.wasNull())
				{
					CliValue = ""; // set it to empty string
				}

				String SiteName = rs_forDB.getString("SiteName");

				// Check if it was null
				if (rs_forDB.wasNull())
				{
					SiteName = ""; // set it to empty string
				}

				// Write to ClosedOutages_AffectedCLIs table only for CLOSED incident, (not for OPEN)
				if (!"null".equals(columnsForExport[6]))
				{
					// System.out.println("columnsForExport[6] = " + columnsForExport[6]);
					// Insert Values in Database
					s_dbs.insertValuesInTable("Test_ClosedOutages_AffectedCLIs",
							new String[] { "CliValue", "OutageID", "IncidentStatus", "IncidentID", "Scheduled",
									"StartTime", "EndTime", "AffectedService", "Impact", "Priority",
									"HierarchySelected", "Location" },
							new String[] { CliValue, columnsForExport[1], columnsForExport[2], columnsForExport[3],
									columnsForExport[4], columnsForExport[5], columnsForExport[6], columnsForExport[7],
									columnsForExport[8], columnsForExport[9], columnsForExport[10], SiteName },
							new String[] { "String", "String", "String", "String", "String", "DateTime", "DateTime",
									"String", "String", "String", "String", "String" });
				}
			}

			//logger.trace("Thread execution CLIs to DB Table Finished!");

			if (conObj != null)
			{
				conObj.closeDBConnection();
			}
			if (s_conObj != null)
			{
				s_conObj.closeDBConnection();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			conObj = null;
			conn = null;
			s_conn = null;
			s_conObj = null;
			s_dbs = null;
		}
	}

	public void saveResultSetToCSV() throws SQLException
	{

	}
}
