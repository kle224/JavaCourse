//package Section13;
//
//public class Exercise02 {
//	public static void run() {
//		int number = 1234512346;
//		
//		int counter = numberOfDigits(number, 0, 0);
//		System.out.println(counter);
//	}
//	
//	public static int numberOfDigits(int storage, int sum, int counter) {
//		if (storage == 0) {
//			return counter;
//		}
//		
//		return numberOfDigits(storage /= 10, sum += storage % 10, ++counter);
//	}
//}

Class: APIExeption.java

package de.schoenn.ServiceNow4J;

import java.net.URL;

public class APIException extends Exception {

	private static final long serialVersionUID = 123; // 123 = Placeholder
	private URL url;
	private int code;
	
	public APIException(String message, URL url, int code) {
		super(message);
		this.url = url;
		this.code = code;
	}
	
	public URL getURL() {
		return url;
	}
	
	public int getCode() {
		return code;
	}
	
}

----------------------------------------------------------------------------------

Class: AssignmentGroup.java

package de.schoenn.ServiceNow4J;

public class AssignmentGroup {

	public AssignmentGroup() {
		// TODO Auto-generated constructor stub
	}
}

----------------------------------------------------------------------------------

Class: HTTPMethod.java

package de.schoenn.ServiceNow4J;

public enum HTTPMethod {
	GET,
	POST,
	PUT,
	DELETE;	
}

----------------------------------------------------------------------------------

Class: Impact.java

package de.schoenn.ServiceNow4J;

public enum Impact {
	HIGH(1, "High"),
	MEDIUM(2, "Medium"),
	LOW(3, "Low");
	
	private int id;
	private String name;
	
	Impact(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Impact fromID(int id) {
		if (id == 1) return HIGH;
		if (id == 2) return MEDIUM;
		return LOW;
	}
}

----------------------------------------------------------------------------------

Class: Incident.java

package de.schoenn.ServiceNow4J;

public class Incident extends TableEntry {

	String incNumber;
	
	public Incident(String sysID) {
		super(sysID);
	}
	
	public Incident(String sysID, String incNumber) {
		this(sysID);
		this.incNumber = incNumber;
	}

	public String getIncNumber() {
		return incNumber;
	}
}

----------------------------------------------------------------------------------

Class: IOUtils.java

package de.schoenn.ServiceNow4J;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public class IOUtils{

	/**
	 * Gets the contents of an {@link InputStream} as a <code>byte[]</code>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * {@link BufferedInputStream}.
	 * 
	 * @param input the {@link InputStream} to read from
	 * @return the requested byte array
	 * @throws IOException if an I/O error occurs
	 */
	public static byte[] toByteArray(InputStream input) throws IOException{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while(-1 != (n = input.read(buffer))){
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
	
	public static <T> T concatArrays(T array1, T array2) {
		if (!array1.getClass().isArray() || !array2.getClass().isArray()) {
			throw new IllegalArgumentException("Only arrays are accepted.");
		}
		
		Class<?> compType = array1.getClass().getComponentType();
		if (!compType.equals(array2.getClass().getComponentType())) {
			throw new IllegalArgumentException("Arrays have to be of same type.");
		}
		
		int len1 = Array.getLength(array1);
		int len2 = Array.getLength(array2);
		
		@SuppressWarnings("unchecked")
		T result = (T) Array.newInstance(compType, len1 + len2);
		
		System.arraycopy(array1, 0, result, 0, len1);
		System.arraycopy(array2, 0, result, len1, len2);
		
		return result;
	}
}

----------------------------------------------------------------------------------

Class: ServiceNow.java

package de.schoenn.ServiceNow4J;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ServiceNow {

	private final String endpoint;
	private final String user, pass;

	public ServiceNow(String endpoint, String user, String pass) {
		this.endpoint = endpoint;
		this.user = user;
		this.pass = pass;
		
		// Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {(TrustManager) new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Use the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
	
	public Incident getIncident(String sysID) {
		Incident incident = new Incident(sysID);
		try {
			ServiceNowHttpsClient https = new ServiceNowHttpsClient(new URI(endpoint + "/api/now/table/incident/" + sysID), user, pass);
			//TODO: JSON Parsen
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return incident;
	}
	
//	public Incident createIncident(String shortDesc, String longDesc, ConfigurationItem ci, AssignmentGroup group, Impact impact, Urgency urgency, String caller, String affectedUser) throws APIException {
//		
//	}
}

----------------------------------------------------------------------------------

Class: ServiceNowHTTPSClient.java

package de.schoenn.ServiceNow4J;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class ServiceNowHttpsClient{

	private final URL					url;
	private final String				user, pass;
	private final HTTPMethod			method;
	private final Map<String, String>	args;
	private final JSONObject			payload;
	private int							responseCode	= -1;
	private byte[]						content;

	public ServiceNowHttpsClient(URI uri, String user, String pass) throws IOException {
		this(uri, user, pass, HTTPMethod.GET, new HashMap<String, String>());
	}

	public ServiceNowHttpsClient(URI uri, String user, String pass, HTTPMethod method, Map<String, String> args) throws IOException {
		this(uri, user, pass, method, args, new JSONObject());
	}
	
	public ServiceNowHttpsClient(URI uri, String user, String pass, HTTPMethod method, Map<String, String> args, JSONObject payload) throws IOException {
		this.url = uri.toURL();
		this.user = user;
		this.pass = pass;
		this.method = method;
		this.args = args;
		this.payload = payload;
	}

	private void getContent() throws IOException, APIException {
		URL url = this.url;
		String rawArgs = "";
		args.put("angular", "true");
		for(String key : args.keySet())
			rawArgs += (key + "=" + URLEncoder.encode(args.get(key), "UTF-8") + "&");
		rawArgs = rawArgs.substring(0, rawArgs.length() - 1);
		if(args != null && !args.isEmpty()) {
			try {
				url = new URI(this.url.toString() + "?" + rawArgs).toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		int connectErrors = 0;
		while(connectErrors < 3){
			try{
				System.out.println(method + " " + url.toString());
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setRequestMethod(method.name());
				conn.setRequestProperty("User-Agent", "Mozilla/5.0");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");
				String token = user + ":" + pass;
				token = new String(Base64.getEncoder().encode(token.getBytes()));
				conn.setRequestProperty("Authorization", "Basic " + token);
				if(method == HTTPMethod.POST) {
					OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
					writer.write(payload.toString());
					writer.close();
				} else conn.connect();
				responseCode = conn.getResponseCode();
				if(responseCode >= HttpsURLConnection.HTTP_BAD_REQUEST) {
					content = IOUtils.toByteArray(conn.getErrorStream());
					JSONObject errorjson = new JSONObject(new String(content));
					throw new APIException(errorjson.optString("message", "Unknown Error"), url, responseCode);
				} else {
					content = IOUtils.toByteArray(conn.getInputStream());
				}
				conn.disconnect();
				return;
			} catch(IOException e) {
				connectErrors++;
				if(connectErrors >= 3){
					throw e;
				}
			}
		}
	}
	
	public int getResponseCode() {
		return this.responseCode;
	}

	public JSONObject getJSON() throws IOException, APIException {
		if(responseCode == -1) getContent();
		return new JSONObject(new String(content));
	}
}

----------------------------------------------------------------------------------

Class: TableEntry.java

package de.schoenn.ServiceNow4J;

public class TableEntry {

	private String sysID;
	
	public TableEntry(String sysID) {
		this.sysID = sysID;
	}

	public String getSysID() {
		return sysID;
	}

	public void setSysID(String sysID) {
		this.sysID = sysID;
	}
}

----------------------------------------------------------------------------------

Class: Urgency

package de.schoenn.ServiceNow4J;

public enum Urgency {

	HIGH(1, "High"),
	MEDIUM(2, "Medium"),
	LOW(3, "Low");
	
	private int id;
	private String name;
	
	Urgency(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Urgency fromID(int id) {
		if (id == 1) return HIGH;
		if (id == 2) return MEDIUM;
		return LOW;
	}
}

----------------------------------------------------------------------------------