package gr.wind.spectra.business;

import java.sql.Connection;
import java.util.ArrayList;

// Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.wind.spectra.web.InvalidInputException;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!
public class s_DB_Connection
{
	java.sql.Connection conn = null;

	// Define a static logger variable so that it references the
	// Logger instance named "DB_Connection".
	Logger logger = LogManager.getLogger(gr.wind.spectra.business.s_DB_Connection.class);

	public Connection connect()
			throws InvalidInputException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
		// database...");

		/**
		 * Use property file with glassfish Place your property files in the
		 * <glassfish-install-dir>/glassfish/domains/<domain-name>/lib/classes directory
		 * and they will be accessible from within your applications via the
		 * ResourceBundle class.
		 */

		try
		{
			/**
			 * Direct Connection to MySQL
			 */
			/*
			 * Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); conn =
			 * DriverManager.getConnection(DATABASE_URL + "user=" + USERNAME + "&" +
			 * "password=" + PASSWORD + "&" +
			 * "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Athens&autoReconnect=true"
			 * );
			 */

			// Using HikariCP - MyDataSource Class (fastest Java JDBC datasource!)

			//if (Help_Func.PropertiesDBFileModified())
			//{
			//	MyDataSource.getUpdatedResourceData();
			//}
			s_MyDataSource smds = new s_MyDataSource();
			conn = smds.getConnection();
			/**
			 * Implemented Connection Pooling! Requires Glassfish configuration (JDBC
			 * Connection Pool + JDBC Resource)
			 */
			// InitialContext ctx = new InitialContext();
			// The JDBC Data source that we just created
			// DataSource ds = (DataSource) ctx.lookup("mySQLJDBCResource");
			// conn = ds.getConnection();

			if (conn != null)
			{
				logger.debug("DB Connection established!");
			} else
			{
				logger.fatal("Could not open connection with database!");
			}

			// Do something with the Connection

		} catch (Exception ex)
		{
			conn = null;
			throw new InvalidInputException("DB Connection Error", "Could not connect to database!");

		}
		return conn;

	}

	public boolean isActive() throws Exception
	{
		if (conn.isValid(0))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public void closeDBConnection() throws Exception
	{
		logger.debug("Closing DB Connection");
		try
		{
			conn.close();
		} catch (Exception ex)
		{
			logger.fatal("Could not open connection with database!");
		}
	}

	public static void main(String[] args) throws Exception, InvalidInputException, InstantiationException,
			IllegalAccessException, ClassNotFoundException
	{

		new ArrayList<Integer>();
		new ArrayList<String>();

		s_DB_Connection conObj = new s_DB_Connection();
		Connection conn = conObj.connect();
		new DB_Operations(conn);

		// O P E R A T I O N S
		// ------------------------------------------------------
		// boolean result = dbs.checkIfStringExistsInSpecificColumn("TestTable", "Name",
		// "Apostolis Kapetanios");
		// ------------------------------------------------------
		// boolean result = dbs.InsertValuesInTable("TestTable", new String[] {"ID",
		// "Name"}, new String[] {"3", "Nikos Zorzos"});

		/*
		 * String result = dbs.InsertValuesInTableGetSequence("Test_SubmittedIncidents", new
		 * String[] {"DateTime", "RequestID", "RequestTimestamp", "SystemID", "UserID",
		 * "IncidentID", "Scheduled", "StartTime", "EndTime", "Duration",
		 * "AffectedServices", "Impact", "Priority", "HierarchySelected"}, new String[]
		 * { "2019-01-01 00:01:00", "R20", "2019-01-01 00:01:00", "Remedy", "akapetan",
		 * "Incident1", "N", "2019-01-01 00:01:00", "2019-01-01 00:01:00", "1", "TV",
		 * "Quality", "Major", "FTTX=1&amp?OLTElementName=Tolis" });
		 *
		 * System.out.println("Result ID: " + result);
		 */
		// ------------------------------------------------------
		//    	List<String> myList = new ArrayList<String>();
		//    	myList = new ArrayList<String>();
		//    	myList = dbs.GetOneColumnResultSet("TestTable", "ID", "Name = 'Nikos Zorzos'");
		//    	for (String item : myList)
		//    	{
		//    		System.out.println(item);
		//    	}
		//    	conObj.closeDBConnection();

		// ------------------------------------------------------
		// int rowsAffected = dbs.UpdateValuesForOneColumn("TestTable", "Surname",
		// "Vernikos", "Name = 'Apostolis' or Name = 'Manos'");
		// System.out.println("Rows affected: " + rowsAffected);

		/*
		 * List<String> myList = new ArrayList<String>(); myList =
		 * dbs.GetOneLineResultSet("Test_SubmittedIncidents",new String[] {"SystemID",
		 * "UserID"}, "OutageID = '1'");
		 *
		 * for (String item : myList) { System.out.println(item); }
		 */

		// int numOfRows = dbs.NumberOfRowsFound("Test_SubmittedIncidents", "IncidentID =
		// 'Incident1' AND IncidentStatus = 'OPEN'");
		// System.out.println(numOfRows);

		// Authenticate
		// boolean found = dbs.AuthenticateRequest("admin", "1234");
		// System.out.println(found);
		// dbs.CountDistinctRowsForSpecificColumns("Voice_Resource_Path", new String[]
		// {"ActiveElement","Subrack","Slot","Port","PON"}, "SiteName = 'ACHARNAI' AND
		// ActiveElement = 'ATHOACHRNAGW01' AND Subrack = '2' AND Slot = '04'");

	}

}

// System.out.println(sqlString);
// rs = stmt.executeQuery("sqlString");
/*
 * if (rs.next()) { found = true; }
 */
// or alternatively, if you don't know ahead of time that
// the query will be a SELECT...

// if (stmt.execute("SELECT " + field + " FROM " + table + " WHERE " + field +
// "= " + searchValue))

// if (stmt.execute("SELECT Name from TestTable where Name = \"Apostolis
// Kapetanios\""))
// if (stmt.execute(sqlString))
// {
// rs = stmt.getResultSet();
// }
// if (rs.next())
// {
// found = true;
// }

// Now do something with the ResultSet ....
/*
 * while (rs.next()) { /* String coffeeName = rs.getString("COF_NAME"); int
 * supplierID = rs.getInt("SUP_ID"); float price = rs.getFloat("PRICE"); int
 * sales = rs.getInt("SALES"); int total = rs.getInt("TOTAL");
 *
 * ## Time java.sql.Time dbSqlTime = rs.getTime(1); java.sql.Date dbSqlDate =
 * rs.getDate(2); java.sql.Timestamp dbSqlTimestamp = rs.getTimestamp();
 * java.sql.Date dbSqlDate = rs.getDate("CurrentDate");
 *
 * int myID = rs.getInt("ID"); String myString = rs.getString("Name");
 *
 *
 * String CurrentValue = rs.getString("Name");
 *
 *
 * if (CurrentValue.equals(anObject) )
 *
 * String }
 */
