package gr.wind.spectra.business;

import java.sql.Connection;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class s_MyDataSource
{
	private static String DATABASE_URL1;// = "jdbc:mysql://localhost:3306/SmartOutageDB?";
	private static String USERNAME1;// = "root";
	private static String PASSWORD1;// = "password";

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	// Define a static logger variable so that it references the
	// Logger instance named "MyDataSource".
	private final Logger logger = LogManager.getLogger(gr.wind.spectra.business.s_MyDataSource.class);

	static
	{
		// Resource is obtained from file:
		// /opt/glassfish5/glassfish/domains/domain1/lib/classes/database.properties

		DATABASE_URL1 = ResourceBundle.getBundle("static_tables_database").getString("DATABASE_URL");
		USERNAME1 = ResourceBundle.getBundle("database_credentials").getString("USERNAME");
		PASSWORD1 = ResourceBundle.getBundle("database_credentials").getString("PASSWORD");

		config.setJdbcUrl(DATABASE_URL1);
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setUsername(USERNAME1);
		config.setPassword(PASSWORD1);
		config.setMaxLifetime(600000);
		config.setConnectionTimeout(30000);
		config.setMaximumPoolSize(4);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "700");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		config.addDataSourceProperty("useServerPrepStmts", "true");

		ds = new HikariDataSource(config);
	}

	public s_MyDataSource()
	{
	}

	public Connection getConnection() throws Exception
	{
		Connection con = null;
		try
		{
			con = ds.getConnection();
		} catch (Exception ex)
		{
			logger.fatal("Could not open connection with database!");
		}
		return con;
	}
}