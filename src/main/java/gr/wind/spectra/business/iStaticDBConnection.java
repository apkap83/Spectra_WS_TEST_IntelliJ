package gr.wind.spectra.business;

import gr.wind.spectra.web.InvalidInputException;

import java.sql.Connection;

public interface iStaticDBConnection {
    Connection connect()
            throws InvalidInputException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    boolean isActive() throws Exception;

    void closeDBConnection() throws Exception;
}
