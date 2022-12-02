package gr.wind.spectra.business;

import gr.wind.spectra.web.InvalidInputException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface iStatic_DB_Operations {
    boolean checkIfStringExistsInSpecificColumn(String table, String columnName, String searchValue)
            throws SQLException;

    void updateUsageStatisticsForMethod(String methodName) throws SQLException, ParseException;

    boolean insertValuesInTable(String table, String[] columnNames, String[] columnValues, String[] types)
            throws SQLException;

    int getMaxIntegerValue(String table, String columnName) throws SQLException;

    boolean checkIfCriteriaExists(String table, String[] predicateKeys, String[] predicateValues,
                                  String[] predicateTypes) throws SQLException;

    String getOneValue(String table, String columnName, String[] predicateKeys, String[] predicateValues,
                       String[] predicateTypes) throws SQLException;

    List<String> getOneColumnUniqueResultSet(String table, String columnName, String[] predicateKeys,
                                             String[] predicateValues, String[] predicateTypes) throws SQLException;

    int updateValuesBasedOnLastInsertID(String table, String setColumnName, String[] predicateKeys,
                                        String[] predicateValues, String[] predicateTypes) throws SQLException;

    int updateValuesForOneColumn(String table, String setColumnName, String newValue, String[] predicateKeys,
                                 String[] predicateValues, String[] predicateTypes) throws SQLException;

    String numberOfRowsFound(String table, String[] predicateKeys, String[] predicateValues,
                             String[] predicateTypes) throws SQLException;

    String countDistinctRowsForSpecificColumn(String table, String column, String[] predicateKeys,
                                              String[] predicateValues, String[] predicateTypes) throws SQLException;

    String countDistinctRowsForSpecificColumns(String table, String[] columns, String[] predicateKeys,
                                               String[] predicateValues, String[] predicateTypes) throws SQLException;

    ResultSet getRows(String table, String[] columnNames, String[] predicateKeys, String[] predicateValues,
                      String[] predicateTypes) throws SQLException;

    boolean authenticateRequest(String userName, String password, String serviceName) throws SQLException;

    String maxNumberOfCustomersAffected(String table, String SumOfColumn, String[] predicateColumns,
                                        String[] predicateValues) throws SQLException;

    int updateColumnOnSpecificCriteria(String tableName, String[] columnNamesForUpdate,
                                       String[] columnValuesForUpdate, String[] setColumnDataTypes, String[] predicateColumns,
                                       String[] predicateValues, String[] predicateColumnsDataTypes) throws SQLException, InvalidInputException;
}
