package gr.wind.spectra.business;

import gr.wind.spectra.web.InvalidInputException;

import java.sql.Connection;

public interface iDynamicDBConnection {
    Connection connect()
            throws InvalidInputException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    boolean isActive() throws Exception;

    void closeDBConnection() throws Exception;
}
