package gr.wind.spectra.business;

import gr.wind.spectra.web.InvalidInputException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Interface_DB_Operations {
    boolean checkIfStringExistsInSpecificColumn(String table, String columnName, String searchValue)
            throws SQLException;

    boolean insertValuesInTable(String table, String[] columnNames, String[] columnValues, String[] types)
            throws SQLException, ParseException;

    int getMaxIntegerValue(String table, String columnName) throws SQLException;

    Map<String, String> getCDRDB_Parameters(String table1, String table2, String[] columnNames, String cliValue)
            throws SQLException;

    boolean checkIfCriteriaExists(String table, String[] predicateKeys, String[] predicateValues,
                                  String[] predicateTypes) throws SQLException;

    String getOneValue(String table, String columnName, String[] predicateKeys, String[] predicateValues,
                       String[] predicateTypes) throws SQLException;

    List<String> getOneColumnUniqueResultSet(String table, String columnName, String[] predicateKeys,
                                             String[] predicateValues, String[] predicateTypes) throws SQLException;

    int updateValuesForOneColumn(String table, String setColumnName, String newValue, String[] predicateKeys,
                                 String[] predicateValues, String[] predicateTypes) throws SQLException;

    String numberOfRowsFound(String table, String[] predicateKeys, String[] predicateValues,
                             String[] predicateTypes) throws SQLException;

    String countDistinctRowsForSpecificColumn(String table, String column, String[] predicateKeys,
                                              String[] predicateValues, String[] predicateTypes) throws SQLException;

    String countDistinctRowsForSpecificColumnsNGAIncluded(String table, String[] columns, String[] predicateKeys,
                                                          String[] predicateValues, String[] predicateTypes, String ngaTypes) throws SQLException;

    String determineWSAffected(String hierarchyGiven) throws SQLException;

    String countDistinctRowsForSpecificColumns(String table, String[] columns, String[] predicateKeys,
                                               String[] predicateValues, String[] predicateTypes) throws SQLException;

    String countDistinctCLIsAffected(String[] distinctColumns, String[] predicateKeys, String[] predicateValues,
                                     String[] predicateTypes, String ngaTypes, String serviceType, String voiceSubsTable, String dataSubsTable,
                                     String IPTVSubsTable) throws SQLException;

    ResultSet getRows(String table, String[] columnNames, String[] predicateKeys, String[] predicateValues,
                      String[] predicateTypes) throws SQLException;

    boolean authenticateRequest(String userName, String password) throws SQLException;

    String maxNumberOfCustomersAffected(String table, String SumOfColumn, String[] predicateColumns,
                                        String[] predicateValues) throws SQLException;

    int updateColumnOnSpecificCriteria(String tableName, String[] columnNamesForUpdate,
                                       String[] columnValuesForUpdate, String[] setColumnDataTypes, String[] predicateColumns,
                                       String[] predicateValues, String[] predicateColumnsDataTypes) throws SQLException, InvalidInputException;
}
