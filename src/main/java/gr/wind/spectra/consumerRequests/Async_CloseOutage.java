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

import gr.wind.spectra.consumer.oneway.CloseOutage;
import gr.wind.spectra.consumer.oneway.WebSpectraService;

public class Async_CloseOutage extends Thread
{
	String UserName;
	String Password;
	String RequestID;
	String RequestTimestamp;
	String SystemID;
	String UserID;
	String IncidentID;
	String OutageID;

	public Async_CloseOutage(String UserName, String Password, String RequestID, String RequestTimestamp,
			String SystemID, String UserID, String IncidentID, String OutageID)
	{
		this.UserName = UserName;
		this.Password = Password;
		this.RequestID = RequestID;
		this.RequestTimestamp = RequestTimestamp;
		this.SystemID = SystemID;
		this.UserID = UserID;
		this.IncidentID = IncidentID;
		this.OutageID = OutageID;
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

		CloseOutage ca = new CloseOutage();
		ca.setRequestID(this.RequestID);
		ca.setRequestTimestamp(this.RequestTimestamp);
		ca.setSystemID(this.SystemID);
		ca.setUserID(this.UserID);
		ca.setIncidentID(this.IncidentID);
		ca.setOutageID(this.OutageID);

		try
		{
			iws.closeOutage(ca, UserName, Password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		//System.out.println(ghR.toString());
	}
}
