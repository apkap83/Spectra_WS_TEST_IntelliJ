package gr.wind.spectra.consumerRequests;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import gr.wind.spectra.consumer.oneway.ModifyOutage;
import gr.wind.spectra.consumer.oneway.WebSpectraService;

public class Async_ModifyOutage extends Thread
{
	String UserName;
	String Password;
	String RequestID;
	String RequestTimestamp;
	String SystemID;
	String UserID;
	String IncidentID;
	String OutageID;
	String StartTime;
	String EndTime;
	String Duration;
	String Impact;

	public Async_ModifyOutage(String userName, String password, String requestID, String requestTimestamp,
			String systemID, String userID, String incidentID, String outageID, String startTime, String endTime,
			String duration, String impact)
	{
		UserName = userName;
		Password = password;
		RequestID = requestID;
		RequestTimestamp = requestTimestamp;
		SystemID = systemID;
		UserID = userID;
		IncidentID = incidentID;
		OutageID = outageID;
		StartTime = startTime;
		EndTime = endTime;
		Duration = duration;
		Impact = impact;
	}

	@Override
	public void run()
	{
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
		{
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType)
			{
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType)
			{
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try
		{
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier()
		{
			@Override
			public boolean verify(String hostname, SSLSession session)
			{
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		// Send request to Spectra_Reporting
		WebSpectraService myWebService = new WebSpectraService();
		gr.wind.spectra.consumer.oneway.InterfaceWebSpectra iws = myWebService.getWebSpectraPort();

		ModifyOutage ma = new ModifyOutage();
		ma.setRequestID(this.RequestID);
		ma.setRequestTimestamp(this.RequestTimestamp);
		ma.setSystemID(this.SystemID);
		ma.setUserID(this.UserID);
		ma.setIncidentID(this.IncidentID);
		ma.setOutageID(this.OutageID);
		ma.setStartTime(this.StartTime);
		ma.setEndTime(this.EndTime);
		ma.setDuration(this.Duration);
		ma.setImpact(this.Impact);

		try
		{
			iws.modifyOutage(ma, UserName, Password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
