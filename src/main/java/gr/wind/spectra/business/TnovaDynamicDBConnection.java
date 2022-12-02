package gr.wind.spectra.business;

import gr.wind.spectra.web.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!
public class TnovaDynamicDBConnection implements iDynamicDBConnection {
	Connection conn = null;

	// Define a static logger variable so that it references the
	// Logger instance named "DB_Connection".
	Logger logger = LogManager.getLogger(TnovaDynamicDBConnection.class);

	@Override
	public Connection connect()
			throws InvalidInputException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
		try
		{
			// Check if we have a fresh dynamic database
			if (Help_Func.NovaPropertiesDBFileModified())
			{
				TnovaDynamicDataSource.getUpdatedResourceData();
			}

			conn = TnovaDynamicDataSource.ds.getConnection();

			if (conn != null)
			{
				logger.debug("DB Connection with Nova Dynamic database established!");
			} else
			{
				logger.fatal("Could not open connection with Nova Dynamic database!");
			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return conn;
	}

	@Override
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

	@Override
	public void closeDBConnection() throws Exception
	{
		logger.debug("Closing DB Connection with Nova Dynamic Database");
		try
		{
			conn.close();
		} catch (Exception ex)
		{
			logger.fatal("Could not open connection with Nova Dynamic Database !");
		}
	}
}