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

import gr.wind.spectra.consumer.oneway.SubmitOutage;
import gr.wind.spectra.consumer.oneway.WebSpectraService;

public class Async_SubmitOutage extends Thread
{
	String UserName;
	String Password;
	String RequestID;
	String RequestTimestamp;
	String SystemID;
	String UserID;
	String IncidentID;
	String Scheduled;
	String StartTime;
	String EndTime;
	String Duration;
	String AffectedServices;
	String Impact;
	String Priority;
	String HierarchySelected;

	public Async_SubmitOutage(String UserName, String Password, String RequestID, String RequestTimestamp,
			String SystemID, String UserID, String IncidentID, String Scheduled, String StartTime, String EndTime,
			String Duration, String AffectedServices, String Impact, String Priority, String HierarchySelected)
	{
		this.UserName = UserName;
		this.Password = Password;
		this.RequestID = RequestID;
		this.RequestTimestamp = RequestTimestamp;
		this.SystemID = SystemID;
		this.UserID = UserID;
		this.IncidentID = IncidentID;
		this.Scheduled = Scheduled;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Duration = Duration;
		this.AffectedServices = AffectedServices;
		this.Impact = Impact;
		this.Priority = Priority;
		this.HierarchySelected = HierarchySelected;
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

		SubmitOutage sa = new SubmitOutage();
		sa.setRequestID(this.RequestID);
		sa.setRequestTimestamp(this.RequestTimestamp);
		sa.setSystemID(this.SystemID);
		sa.setUserID(this.UserID);
		sa.setIncidentID(this.IncidentID);
		sa.setScheduled(this.Scheduled);
		sa.setStartTime(this.StartTime);
		sa.setEndTime(this.EndTime);
		sa.setDuration(this.Duration);
		sa.setAffectedServices(this.AffectedServices);
		sa.setImpact(this.Impact);
		sa.setPriority(this.Priority);
		sa.setHierarchySelected(this.HierarchySelected);

		try
		{
			iws.submitOutage(sa, UserName, Password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
