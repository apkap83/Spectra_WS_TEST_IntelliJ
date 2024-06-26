package gr.wind.spectra.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.wind.spectra.web.InvalidInputException;

public class DB_Operations implements iDB_Operations {
	// Logger instance
	private final Logger logger = LogManager.getLogger(gr.wind.spectra.business.DB_Operations.class.getName());

	Connection conn;
	Statement stmt = null;
	ResultSet rs = null;

	public DB_Operations(Connection conn)
	{
		this.conn = conn;
	}

	@Override
	public boolean checkIfStringExistsInSpecificColumn(String table, String columnName, String searchValue)
			throws SQLException
	{
		boolean found = false;

		String sqlString = "SELECT `" + columnName + "` FROM `" + table + "` WHERE `" + columnName + "` = ?";
		logger.trace(sqlString);
		PreparedStatement pst = conn.prepareStatement(sqlString);
		pst.setString(1, searchValue);
		pst.execute();

		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			String current = rs.getString(columnName);

			if (current.contentEquals(searchValue))
			{
				found = true;
			}
		}
		return found;
	}

	@Override
	public boolean insertValuesInTable(String table, String[] columnNames, String[] columnValues, String[] types)
			throws SQLException, ParseException
	{
		Help_Func hf = new Help_Func();

		boolean statusOfOperation = false;
		String sqlString = "INSERT INTO " + table + hf.columnsToInsertStatement(columnNames)
				+ hf.valuesToInsertStatement(columnValues);
		logger.trace(sqlString);
		PreparedStatement pst = conn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);

		for (int i = 0; i < columnNames.length; i++)
		{
			if (columnValues[i].equals(""))
			{
				pst.setNull(i + 1, Types.NULL);
			} else
			{
				if (types[i].equals("String"))
				{
					pst.setString(i + 1, columnValues[i]);
				} else if (types[i].equals("DateTime"))
				{
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.parse(columnValues[i], formatter);
					pst.setObject(i + 1, dateTime);
				} else if (types[i].equals("Integer"))
				{
					pst.setInt(i + 1, Integer.parseInt(columnValues[i]));
				}

			}
		}

		try
		{
			pst.executeUpdate();
			statusOfOperation = true;
			// Code to get Generated Key
			// ResultSet tableKeys = pst.getGeneratedKeys();
			// tableKeys.next();
			// autoGeneratedID = Integer.toString(tableKeys.getInt(1));
		} catch (SQLException e)
		{
			statusOfOperation = false;
			e.printStackTrace();

		}

		return statusOfOperation;
	}

	@Override
	public int getMaxIntegerValue(String table, String columnName) throws SQLException
	{
		int returnValue = 0;
		String sqlString = "SELECT MAX(" + columnName + ") FROM " + table;
		logger.trace(sqlString);
		PreparedStatement pst = conn.prepareStatement(sqlString);
		pst.execute();
		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			returnValue = rs.getInt(1);
		}

		return returnValue;
	}

	@Override
	public Map<String, String> getCDRDB_Parameters(String table1, String table2, String[] columnNames, String cliValue)
			throws SQLException
	{
		// Get Lines with IncidentStatus = "OPEN"
		/*
		 * SELECT
			A.CliValue,
			A.Username,
			B.Active_Element as "AAA DLSAM Name",
			A.ActiveElement as "WindOwnedElement",
			A.PASPORT_COID
			FROM SmartOutageDB_15Jun2021.Prov_Internet_Resource_Path as A
			left join SmartOutageDB_15Jun2021.AAA21_NMAP as B
			on A.Username=B.Username
			where A.CliValue ='2114045866';
		 *
		 *
		 */

		Map<String, String> fields = new LinkedHashMap<String, String>();

		String sqlString = "SELECT " + String.join(", ", columnNames) + " FROM " + table1 + " as A " + "LEFT JOIN "
				+ table2 + " as B on A.Username=B.Username WHERE A.CliValue = ?";

		System.out.println("Get Correct DSLAM Query " + sqlString);

		PreparedStatement pst = conn.prepareStatement(sqlString);
		pst.setString(1, cliValue);
		pst.execute();
		ResultSet rs = pst.executeQuery();

		if (!rs.next())
		{
			fields.put("CliValue", null);
			fields.put("Username", null);
			fields.put("AAA DLSAM Name", null);
			fields.put("WindOwnedElement", null);
			fields.put("PASPORT_COID", null);

		} else
		{
			fields.put("CliValue", rs.getString("CliValue"));
			fields.put("Username", rs.getString("Username"));
			fields.put("AAA DLSAM Name", rs.getString("AAA DLSAM Name"));
			fields.put("WindOwnedElement", rs.getString("WindOwnedElement"));
			fields.put("PASPORT_COID", rs.getString("PASPORT_COID"));
		}

		return fields;
	}

	@Override
	public boolean checkIfCriteriaExists(String table, String[] predicateKeys, String[] predicateValues,
										 String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		boolean criteriaIfExists = false;

		String sqlQuery = "SELECT COUNT(*) as Result FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);
		logger.trace(sqlQuery);
		PreparedStatement pst = conn.prepareStatement(sqlQuery);
		ResultSet rs = null;
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

		rs = pst.executeQuery();
		rs.next();
		String numOfRowsFound = rs.getString("Result");

		if (!numOfRowsFound.equals("0"))
		{
			criteriaIfExists = true;
		}

		return criteriaIfExists;
	}

	@Override
	public String getOneValue(String table, String columnName, String[] predicateKeys, String[] predicateValues,
							  String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();
		String output;
		String sqlQuery = "SELECT " + columnName + " FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);
		logger.trace(sqlQuery);
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
		// pst.execute();
		ResultSet rs = pst.executeQuery();
		if (rs.next())
		{
			output = rs.getString(columnName);
		} else
		{
			output = "";
		}
		rs.close();

		return output;
	}

	@Override
	public List<String> getOneColumnUniqueResultSet(String table, String columnName, String[] predicateKeys,
													String[] predicateValues, String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		// Example: select DISTINCT ID from table where a = 2 and b = 3
		List<String> myList = new ArrayList<String>();

		String sqlString = "SELECT DISTINCT `" + columnName + "` FROM `" + table + "` WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);
		logger.trace(sqlString);

		PreparedStatement pst = conn.prepareStatement(sqlString);

		for (int i = 0; i < predicateKeys.length; i++)
		{
			if (predicateTypes[0].equals("String"))
			{
				pst.setString(i + 1, predicateValues[i]);
			} else if (predicateTypes[1].equals("Integer"))
			{
				pst.setString(i + 1, predicateValues[i]);
			}
		}

		pst.execute();
		ResultSet rs = null;
		rs = pst.executeQuery();

		try
		{
			while (rs.next())
			{
				String current = rs.getString(columnName);
				if (!(current == null || current.isEmpty()))
				{
					myList.add(current);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Sort list alphabetically
		java.util.Collections.sort(myList);

		return myList;
	}

	@Override
	public int updateValuesForOneColumn(String table, String setColumnName, String newValue, String[] predicateKeys,
										String[] predicateValues, String[] predicateTypes) throws SQLException
	{
		// Example: update TestTable set `Name` = 100 where Surname = "Kapetanios";

		Help_Func hf = new Help_Func();

		String sqlString = "update `" + table + "` set `" + setColumnName + "` = '" + newValue + "' WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);
		logger.trace(sqlString);
		PreparedStatement pst = conn.prepareStatement(sqlString);

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

		int rowsAffected = pst.executeUpdate();

		return rowsAffected;

	}

	@Override
	public String numberOfRowsFound(String table, String[] predicateKeys, String[] predicateValues,
									String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		int numOfRows = 0;
		String sqlQuery = "SELECT *" + " FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);
		logger.trace(sqlQuery);
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

		pst.execute();
		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			numOfRows++;
		}

		return Integer.toString(numOfRows);
	}

	@Override
	public String countDistinctRowsForSpecificColumn(String table, String column, String[] predicateKeys,
													 String[] predicateValues, String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		String numOfRows = "";
		String sqlQuery = "SELECT COUNT(DISTINCT(" + column + ")) as " + column + " FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);

		logger.trace(sqlQuery);
		PreparedStatement pst = conn.prepareStatement(sqlQuery);

		for (int i = 0; i < predicateKeys.length; i++)
		{
			if (predicateTypes[i].equals("String"))
			{
				pst.setString(i + 1, predicateValues[i]);
			} else if (predicateTypes[i].equals("String"))
			{
				pst.setString(i + 1, predicateValues[i]);
			}
		}

		pst.execute();
		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			numOfRows = rs.getString(column);
			// numOfRows++;
		}

		return numOfRows;
	}

	/*
	 * SELECT COUNT(*) FROM ( SELECT DISTINCT ActiveElement,Subrack,Slot,Port,PON
	 * FROM Voice_Resource_Path WHERE SiteName = 'ACHARNAI' AND ActiveElement =
	 * 'ATHOACHRNAGW01' AND Subrack = '2' AND Slot = '04' ) as AK;
	 *
	 */

	@Override
	public String countDistinctRowsForSpecificColumnsNGAIncluded(String table, String[] columns, String[] predicateKeys,
																 String[] predicateValues, String[] predicateTypes, String ngaTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		String numOfRows = "";
		String sqlQuery = "SELECT COUNT(*) AS Result FROM (SELECT DISTINCT ";

		// Convert NGA_TYPES to --> AND NGA_TYPE IN ('1', '2', '3')
		String ngaTypesToSQLPredicate = hf.ngaTypesToSqlInFormat(ngaTypes);

		for (int i = 0; i < columns.length; i++)
		{
			if (i < columns.length - 1)
			{
				sqlQuery += columns[i] + ",";
			} else
			{
				sqlQuery += columns[i];
			}
		}

		// If NgaPredicate is ALL then dont's set [ ngapredicate IN ('value1', 'value2', 'value3',) ]
		if (ngaTypes.equals("ALL"))
		{
			sqlQuery += " FROM " + table + " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + ") as AK ";
		} else
		{
			sqlQuery += " FROM " + table + " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
					+ ngaTypesToSQLPredicate + ") as AK ";
		}

		logger.trace(sqlQuery);
		// System.out.println("366 : sqlQuery = " + sqlQuery);
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

		pst.execute();
		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			numOfRows = rs.getString("Result");
		}

		return numOfRows;
	}

	@Override
	public String determineWSAffected(String hierarchyGiven) throws SQLException
	{
		/*
		String output = "";
		Pattern.compile("^Cabinet_Code");
		Pattern.compile("Wind_FTTX");
		Pattern.compile("^FTTC_Location_Element");
		
		boolean b1, b2, b3;
		b1 = b2 = b3 = false;
		
		if (hierarchyGiven.startsWith("Cabinet_Code"))
		{
			b1 = true;
		}
		if (hierarchyGiven.startsWith("Wind_FTTX"))
		{
			b2 = true;
		}
		if (hierarchyGiven.startsWith("FTTC_Location_Element"))
		{
			b3 = true;
		}
		
		if (b1 || b2 || b3)
		{
			output = "Yes";
		} else
		{
			output = "No";
		}
		
		return output;
		*/

		Help_Func hf = new Help_Func();

		// Get root hierarchy String
		String rootElementInHierarchy = hf.getRootHierarchyNode(hierarchyGiven);

		// Based on root hierarchy get value of WsAffected column
		String wsAffectedValue = getOneValue("HierarchyTablePerTechnology2", "WsAffected",
				new String[] { "RootHierarchyNode" }, new String[] { rootElementInHierarchy },
				new String[] { "String" });

		return wsAffectedValue;
	}

	@Override
	public String countDistinctRowsForSpecificColumns(String table, String[] columns, String[] predicateKeys,
													  String[] predicateValues, String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		String numOfRows = "";
		String sqlQuery = "SELECT COUNT(*) AS Result FROM(SELECT DISTINCT ";

		for (int i = 0; i < columns.length; i++)
		{
			if (i < columns.length - 1)
			{
				sqlQuery += columns[i] + ",";
			} else
			{
				sqlQuery += columns[i];
			}
		}

		sqlQuery += " FROM " + table + " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + ") as AK ";

		logger.trace(sqlQuery);
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

		pst.execute();
		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			numOfRows = rs.getString("Result");
		}
		return numOfRows;
	}

	@Override
	public String countDistinctCLIsAffected(String[] distinctColumns, String[] predicateKeys, String[] predicateValues,
											String[] predicateTypes, String ngaTypes, String serviceType, String voiceSubsTable, String dataSubsTable,
											String IPTVSubsTable) throws SQLException
	{
		/*	Example of Query that is implemented here
		 *
			SELECT COUNT(DISTINCT PASPORT_COID) AS Result FROM
			(
				SELECT DISTINCT (PASPORT_COID) from Prov_Voice_Resource_Path WHERE `OltElementName` = ? AND `OltRackNo` = ? AND `NGA_TYPE` IN ('WIND_FTTH','WIND_FTTC')
		
			    UNION ALL
		
			    SELECT DISTINCT (PASPORT_COID) from Prov_Internet_Resource_Path WHERE `OltElementName` = ? AND `OltRackNo` = ? AND `NGA_TYPE` IN ('WIND_FTTH','WIND_FTTC')
		
			    UNION ALL
		
			    SELECT DISTINCT (PASPORT_COID) from Prov_IPTV_Resource_Path WHERE `OltElementName` = ? AND `OltRackNo` = ? AND `NGA_TYPE` IN ('WIND_FTTH','WIND_FTTC')
		
			) as AK;
		
		 */

		Help_Func hf = new Help_Func();

		if (serviceType.equals("NotSpecificService"))
		{
			String sqlQueryForVoice = "";
			String sqlQueryForData = "";
			String sqlQueryForIPTV = "";

			// Convert NGA_TYPES to --> AND NGA_TYPE IN ('1', '2', '3')
			String ngaTypesToSQLPredicate = hf.ngaTypesToSqlInFormat(ngaTypes);
			String totalQuery = "SELECT COUNT(DISTINCT " + String.join(", ", distinctColumns) + ") AS Result FROM (";
			// If NgaPredicate is ALL then dont's set [ ngapredicate IN ('value1', 'value2', 'value3',) ]
			if (ngaTypes.equals("ALL"))
			{
				sqlQueryForVoice = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + voiceSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);

				sqlQueryForData = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + dataSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);

				sqlQueryForIPTV = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + IPTVSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);
			} else
			{
				sqlQueryForVoice = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + voiceSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;

				sqlQueryForData = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + dataSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;

				sqlQueryForIPTV = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + IPTVSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;
			}

			totalQuery += sqlQueryForVoice + " UNION ALL " + sqlQueryForData + " UNION ALL " + sqlQueryForIPTV
					+ ") as AK";

			// System.out.println("544: SqlQuery: " + totalQuery);
			logger.trace(totalQuery);
			PreparedStatement pst = conn.prepareStatement(totalQuery);

			// 3 Queries iterations x number of predicates
			int count = 0;
			int totalQuestionMarks = predicateKeys.length * 3;
			for (int i = 0; i < totalQuestionMarks; i++)
			{
				for (int j = 0; j < predicateKeys.length; j++)
				{
					if (predicateTypes[j].equals("String"))
					{
						int num = j + 1 + count;

						if (num <= totalQuestionMarks)
						{
							pst.setString(j + 1 + count, predicateValues[j]);
						}

					} else if (predicateTypes[j].equals("Integer"))
					{
						pst.setInt(j + 1 + count, Integer.parseInt(predicateValues[j]));
					}
				}
				count += predicateKeys.length;
			}

			pst.execute();
			ResultSet rs = pst.executeQuery();
			String numOfRows = "0";

			while (rs.next())
			{
				numOfRows = rs.getString("Result");
			}

			return numOfRows;
		} else
		{
			// Services affected
			String[] servicesAffected = serviceType.split("\\|");

			int numOfServicesAffected = servicesAffected.length;

			boolean voiceServiceAffection = false;
			boolean dataServiceAffection = false;
			boolean iptvServiceAffection = false;

			if ((Arrays.stream(servicesAffected).anyMatch("Voice"::equals)))
			{
				voiceServiceAffection = true;
			}
			if ((Arrays.stream(servicesAffected).anyMatch("Data"::equals)))
			{
				dataServiceAffection = true;
			}
			if ((Arrays.stream(servicesAffected).anyMatch("IPTV"::equals)))
			{
				iptvServiceAffection = true;
			}

			String sqlQueryForVoice = "";
			String sqlQueryForData = "";
			String sqlQueryForIPTV = "";

			// Convert NGA_TYPES to --> AND NGA_TYPE IN ('1', '2', '3')
			String ngaTypesToSQLPredicate = hf.ngaTypesToSqlInFormat(ngaTypes);
			String totalQuery = "SELECT COUNT(DISTINCT " + String.join(", ", distinctColumns) + ") AS Result FROM (";
			// If NgaPredicate is ALL then dont's set [ ngapredicate IN ('value1', 'value2', 'value3',) ]
			if (ngaTypes.equals("ALL"))
			{
				sqlQueryForVoice = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + voiceSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);

				sqlQueryForData = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + dataSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);

				sqlQueryForIPTV = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + IPTVSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys);
			} else
			{
				sqlQueryForVoice = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + voiceSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;

				sqlQueryForData = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + dataSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;

				sqlQueryForIPTV = "SELECT DISTINCT (" + String.join(", ", distinctColumns) + ") from " + IPTVSubsTable
						+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateKeys) + " "
						+ ngaTypesToSQLPredicate;
			}

			if (voiceServiceAffection)
			{
				totalQuery += sqlQueryForVoice + " UNION ALL ";
			}
			if (dataServiceAffection)
			{
				totalQuery += sqlQueryForData + " UNION ALL ";
			}
			if (iptvServiceAffection)
			{
				totalQuery += sqlQueryForIPTV + " UNION ALL ";
			}

			// totalQuery += sqlQueryForVoice + " UNION ALL " + sqlQueryForData + " UNION ALL " + sqlQueryForIPTV;

			// Remove last " UNION ALL "
			totalQuery = totalQuery.substring(0, totalQuery.length() - 11);

			// Add ") as AK" to close the query
			totalQuery += ") as AK";

			logger.trace(totalQuery);
			// System.out.println("544: SqlQuery: " + totalQuery);
			PreparedStatement pst = conn.prepareStatement(totalQuery);

			// X Queries iterations x number of predicates
			int count = 0;

			int totalQuestionMarks = predicateKeys.length * numOfServicesAffected;

			for (int i = 0; i < totalQuestionMarks; i++)
			{
				for (int j = 0; j < predicateKeys.length; j++)
				{
					if (predicateTypes[j].equals("String"))
					{
						int num = j + 1 + count;

						if (num <= totalQuestionMarks)
						{
							pst.setString(j + 1 + count, predicateValues[j]);
						}

					} else if (predicateTypes[j].equals("Integer"))
					{
						pst.setInt(j + 1 + count, Integer.parseInt(predicateValues[j]));
					}
				}
				count += predicateKeys.length;
			}

			pst.execute();
			ResultSet rs = pst.executeQuery();
			String numOfRows = "0";

			while (rs.next())
			{
				numOfRows = rs.getString("Result");
			}

			return numOfRows;
		}

	}

	@Override
	public ResultSet getRows(String table, String[] columnNames, String[] predicateKeys, String[] predicateValues,
							 String[] predicateTypes) throws SQLException
	{
		Help_Func hf = new Help_Func();

		String sqlQuery = "SELECT " + hf.columnsWithCommas(columnNames) + " FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateKeys);

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

		logger.trace(sqlQuery);

		pst.execute();
		ResultSet rs = pst.executeQuery();
		return rs;
	}

	@Override
	public boolean authenticateRequest(String userName, String password) throws SQLException
	{
		boolean found = false;
		String table = "WSAccounts";
		String sqlString = "SELECT * FROM `" + table + "` WHERE `UserName` = ?";
		logger.trace(sqlString);
		PreparedStatement pst = conn.prepareStatement(sqlString);
		pst.setString(1, userName);
		// pst.setString(2, password);
		pst.execute();

		ResultSet rs = pst.executeQuery();

		while (rs.next())
		{
			rs.getString("UserName");
			String r_password = rs.getString("Password");

			boolean passwordIsCorrect = false;
			try
			{
				/**
				 * Complete Implementation of Password hashing
				 * https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java
				 * We are storing 'salt$iterated_hash(password, salt)'. The salt are 32 random
				 * bytes and it's purpose is that if two different people choose the same
				 * password, the stored passwords will still look different.
				 */
				Password psw = new Password();
				passwordIsCorrect = psw.check(password, r_password);

			} catch (Exception e)
			{
				e.printStackTrace();
			}

			if (passwordIsCorrect)
			{
				found = true;
			} else
			{
				found = false;
			}
		}
		return found;
	}

	@Override
	public String maxNumberOfCustomersAffected(String table, String SumOfColumn, String[] predicateColumns,
											   String[] predicateValues) throws SQLException
	{
		Help_Func hf = new Help_Func();

		String numOfRows = "";
		String sqlQuery = "SELECT MAX(" + SumOfColumn + ") as Result FROM " + table + " WHERE "
				+ hf.generateANDPredicateQuestionMarks(predicateColumns);
		logger.trace(sqlQuery);
		PreparedStatement pst = conn.prepareStatement(sqlQuery);

		if (predicateColumns.length == predicateValues.length)
		{
			for (int i = 0; i < predicateColumns.length; i++)
			{
				pst.setString(i + 1, predicateValues[i]);
			}
		}

		ResultSet rs = pst.executeQuery();
		rs.next();
		numOfRows = rs.getString("Result");

		return numOfRows;
	}

	@Override
	public int updateColumnOnSpecificCriteria(String tableName, String[] columnNamesForUpdate,
											  String[] columnValuesForUpdate, String[] setColumnDataTypes, String[] predicateColumns,
											  String[] predicateValues, String[] predicateColumnsDataTypes) throws SQLException, InvalidInputException
	{
		Help_Func hf = new Help_Func();

		int numOfRowsUpdated = 0;
		// update SmartOutageDB.Test_SubmittedIncidents set Duration = '2' where OutageID =
		// '5' and IncidentID = 'INC1';
		String sqlQuery = "UPDATE " + tableName + " SET " + hf.generateCommaPredicateQuestionMarks(columnNamesForUpdate)
				+ " WHERE " + hf.generateANDPredicateQuestionMarks(predicateColumns);
		logger.trace(sqlQuery);

		PreparedStatement pst = conn.prepareStatement(sqlQuery);

		// If no predicates are provided then SQL update command will update all
		// (something VERY undesirable)
		if (columnNamesForUpdate.length == 0)
		{
			throw new InvalidInputException("No predicated provided for SQL update - Aborting Operation", "Error 702");
		} else
		{
			// Set Values for the Updated Columns (SET Part of SQL Expresion)
			for (int i = 0; i < columnNamesForUpdate.length; i++)
			{
				if (setColumnDataTypes[i].equals("String"))
				{
					pst.setString(i + 1, columnValuesForUpdate[i]);
				} else if (setColumnDataTypes[i].equals("Integer"))
				{
					pst.setInt(i + 1, Integer.parseInt(columnValuesForUpdate[i]));
				} else if (setColumnDataTypes[i].equals("Date"))
				{
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.parse(columnValuesForUpdate[i], formatter);

					pst.setObject(i + 1, dateTime);
				}

			}

			// Set Values for the predicate Columns (WHERE Part of SQL Expresion)
			if (predicateColumns.length == predicateValues.length)
			{
				for (int i = 0; i < predicateColumns.length; i++)
				{
					if (predicateColumnsDataTypes[i].equals("String"))
					{
						pst.setString(i + columnNamesForUpdate.length + 1, predicateValues[i]);
					} else if (predicateColumnsDataTypes[i].equals("Integer"))
					{
						pst.setInt(i + columnNamesForUpdate.length + 1, Integer.parseInt(predicateValues[i]));
					} else if (predicateColumnsDataTypes[i].equals("Date"))
					{
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						LocalDateTime dateTime = LocalDateTime.parse(predicateValues[i], formatter);

						pst.setObject(i + columnNamesForUpdate.length + 1, dateTime);
					}
				}
			}
		}

		numOfRowsUpdated = pst.executeUpdate();
		return numOfRowsUpdated;
	}

}
