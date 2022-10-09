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

import gr.wind.spectra.consumer.oneway.NLUActive;
import gr.wind.spectra.consumer.oneway.WebSpectraService;

public class Async_NLUActive extends Thread
{
	String UserName;
	String Password;
	String RequestID;
	String RequestTimestamp;
	String SystemID;
	String CLI;
	String Service;
	String ServiceL2;
	String ServiceL3;

	public Async_NLUActive(String userName, String password, String requestID, String requestTimestamp, String systemID,
			String cLI, String service, String serviceL2, String serviceL3)
	{
		UserName = userName;
		Password = password;
		RequestID = requestID;
		RequestTimestamp = requestTimestamp;
		SystemID = systemID;
		CLI = cLI;
		Service = service;
		ServiceL2 = serviceL2;
		ServiceL3 = serviceL3;
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

		NLUActive nluA = new NLUActive();
		nluA.setRequestID(this.RequestID);
		nluA.setRequestTimestamp(this.RequestTimestamp);
		nluA.setSystemID(this.SystemID);
		nluA.setCLI(this.CLI);
		nluA.setService(this.Service);
		nluA.setServiceL2(this.ServiceL2);
		nluA.setServiceL3(this.ServiceL3);

		try
		{
			iws.nluActive(nluA, UserName, Password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
