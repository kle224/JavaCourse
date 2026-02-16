//package Section13;
//
//public class Excercise03 {
//	Class: OITC
//
//	package de.schoenn.oITCAPI;
//
//	import java.io.IOException;
//
//	import java.net.URI;
//	import java.net.URISyntaxException;
//	import java.security.KeyManagementException;
//	import java.security.NoSuchAlgorithmException;
//	import java.security.cert.CertificateException;
//	import java.security.cert.X509Certificate;
//	import java.util.HashMap;
//	import java.util.Map;
//	import java.util.Set;
//	import java.util.UUID;
//
//	import javax.net.ssl.HostnameVerifier;
//	import javax.net.ssl.HttpsURLConnection;
//	import javax.net.ssl.SSLContext;
//	import javax.net.ssl.SSLSession;
//	import javax.net.ssl.TrustManager;
//	import javax.net.ssl.X509TrustManager;
//
//	import org.json.JSONArray;
//	import org.json.JSONException;
//	import org.json.JSONObject;
//
//	import de.schoenn.oITCAPI.Acknowledgements.HostAcknowledgement;
//	import de.schoenn.oITCAPI.Acknowledgements.ServiceAcknowledgement;
//	import de.schoenn.oITCAPI.Containers.ContactGroup;
//	import de.schoenn.oITCAPI.Containers.HostGroup;
//	import de.schoenn.oITCAPI.Containers.Node;
//	import de.schoenn.oITCAPI.Entities.Contact;
//	import de.schoenn.oITCAPI.Entities.Satellite;
//	import de.schoenn.oITCAPI.Entities.Host.Host;
//	import de.schoenn.oITCAPI.Entities.Host.HostFilter;
//	import de.schoenn.oITCAPI.Entities.Host.HostStatus;
//	import de.schoenn.oITCAPI.Entities.Host.HostTemplate;
//	import de.schoenn.oITCAPI.Entities.Service.Service;
//	import de.schoenn.oITCAPI.Entities.Service.ServiceStatus;
//	import de.schoenn.oITCAPI.Enums.Priority;
//	import de.schoenn.oITCAPI.Utils.APIException;
//	import de.schoenn.oITCAPI.Utils.HTTPMethod;
//	import de.schoenn.oITCAPI.Utils.IOUtils;
//	import de.schoenn.oITCAPI.Utils.OitcHttpsClient;
//
//	public class OITC {
//
//		private final String endpoint;
//		private final String apiKey;
//
//		public OITC(String endpoint, String apiKey) {
//			this.endpoint = endpoint;
//			this.apiKey = apiKey;
//			
//			// Create a trust manager that does not validate certificate chains
//	        TrustManager[] trustAllCerts = new TrustManager[] {(TrustManager) new X509TrustManager() {
//	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//	                    return null;
//	                }
//	                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//	                }
//	                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//	                }
//	            }
//	        };
//	 
//	        // Install the all-trusting trust manager
//	        SSLContext sc = null;
//			try {
//				sc = SSLContext.getInstance("SSL");
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//	        try {
//				sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			} catch (KeyManagementException e) {
//				e.printStackTrace();
//			}
//	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//	 
//	        // Create all-trusting host name verifier
//	        HostnameVerifier allHostsValid = new HostnameVerifier() {
//	            public boolean verify(String hostname, SSLSession session) {
//	                return true;
//	            }
//	        };
//	 
//	        // Use the all-trusting host verifier
//	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//		}
//		
//		public String getEndpoint() {
//			return endpoint;
//		}
//
//		public String getApiKey() {
//			return apiKey;
//		}
//		
//		public Satellite[] getSatellites() throws APIException {
//			Satellite[] satellites = new Satellite[0];
//			try {
//				boolean hasNextPage = true;
//				int page = 1;
//				while (hasNextPage) {
//					Map<String, String> args = new HashMap<>();
//					args.put("page", String.valueOf(page));
//					args.put("scroll", "true");
//					OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/distribute_module/satellites/index.json"), apiKey, HTTPMethod.GET, args);
//					JSONObject response = https.getJSON();
//					JSONArray satellitesRaw = response.getJSONArray("all_satellites");
//					Satellite[] satellitesPart = new Satellite[satellitesRaw.length()];
//					for (int i = 0; i < satellitesRaw.length(); i++) {
//						JSONObject satelliteRaw = satellitesRaw.getJSONObject(i);
//						Satellite satellite = new Satellite(this, 
//								satelliteRaw.getInt("id"), 
//								satelliteRaw.getString("name"), 
//								satelliteRaw.getString("address"), 
//								satelliteRaw.optString("description"), 
//								satelliteRaw.getString("timezone"), 
//								satelliteRaw.getInt("container_id"), 
//								satelliteRaw.getString("created"), 
//								satelliteRaw.getString("modified"));
//						satellitesPart[i] = satellite;
//					}
//					satellites = IOUtils.concatArrays(satellites, satellitesPart);
//					hasNextPage = response.getJSONObject("scroll").getBoolean("hasNextPage");
//					page++;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return satellites;
//		}
//		
//		public Satellite getSatellite(int satelliteID) throws APIException {
//			if (satelliteID == 0) {
//				return new Satellite(this, 0, "Mastersystem", "127.0.0.1", "Mastersystem", "Europe/Berlin", 1, "1970-01-01T00:00:00+00:00", "1970-01-01T00:00:00+00:00");
//			}
//			Satellite satellite = new Satellite(this, satelliteID, "Error requesting satellite-details");
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/distribute_module/satellites/edit/" + satelliteID + ".json"), apiKey);
//				JSONObject satelliteRaw = https.getJSON().getJSONObject("satellite");
//				satellite = new Satellite(this, 
//						satelliteRaw.getInt("id"), 
//						satelliteRaw.getString("name"), 
//						satelliteRaw.getString("address"), 
//						satelliteRaw.optString("description"), 
//						satelliteRaw.getString("timezone"), 
//						satelliteRaw.getInt("container_id"), 
//						satelliteRaw.getString("created"), 
//						satelliteRaw.getString("modified"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return satellite;
//		}
//		
//		public Host[] getHosts() throws APIException {
//			return getHosts(new HostFilter());
//		}
//		
//		public Host[] getHosts(HostFilter filter) throws APIException {
//			Host[] hosts = new Host[0];
//			try {
//				boolean hasNextPage = true;
//				int page = 1;
//				while (hasNextPage) {
//					Map<String, String> args = new HashMap<>();
//					args.put("page", String.valueOf(page));
//					args.put("scroll", "true");
//					OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/hosts/index.json"), apiKey, HTTPMethod.GET, args, filter.build());
//					JSONObject response = https.getJSON();
//					JSONArray hostsRaw = response.getJSONArray("all_hosts");
//					Host[] hostsPart = new Host[hostsRaw.length()];
//					for (int i = 0; i < hostsRaw.length(); i++) {
//						JSONObject hostCompleteRaw = hostsRaw.getJSONObject(i);
//						JSONObject hostRaw = hostCompleteRaw.getJSONObject("Host");
//						JSONObject hostStatusRaw = hostCompleteRaw.getJSONObject("Hoststatus");
//						JSONArray sharedContainers = hostRaw.getJSONArray("containerIds");
//						int[] sharedContainerIDs = new int[sharedContainers.length()-1];
//						for(int j = 0; j < sharedContainers.length()-1; j++) {
//							sharedContainerIDs[j] = sharedContainers.getInt(j+1);
//						}
//						HostStatus status = new HostStatus(
//								hostStatusRaw.getInt("currentState"), 
//								hostStatusRaw.getInt("state_type"), 
//								hostStatusRaw.optBoolean("isFlapping"), 
//								hostStatusRaw.optBoolean("flap_detection_enabled"), 
//								hostStatusRaw.optBoolean("notifications_enabled"), 
//								hostStatusRaw.optBoolean("isInMonitoring"), 
//								hostStatusRaw.optString("lastHardStateChange"), 
//								hostStatusRaw.optString("last_time_up"), 
//								hostStatusRaw.optString("lastCheck"), 
//								hostStatusRaw.optString("nextCheck"), 
//								hostStatusRaw.optString("output"), 
//								hostStatusRaw.optString("long_output"), 
//								hostStatusRaw.getInt("acknowledgement_type") == 0 ? null : new HostAcknowledgement(this, hostStatusRaw.getInt("acknowledgement_type"), hostRaw.getInt("id")));
//						Host host = new Host(this, 
//								hostRaw.getInt("id"), 
//								UUID.fromString(hostRaw.getString("uuid")), 
//								hostRaw.getString("hostname"), 
//								hostRaw.getString("address"), 
//								hostRaw.getString("description"), 
//								hostRaw.getString("notes"), 
//								hostRaw.getBoolean("disabled"), 
//								hostRaw.getInt("satelliteId"), 
//								hostRaw.getString("satelliteName"), 
//								hostRaw.getInt("containerId"), 
//								sharedContainerIDs, 
//								hostRaw.optString("tags").split(", "), 
//								Priority.fromInt(hostRaw.getInt("priority")), 
//								status,
//								new HostTemplate(this, hostRaw.optInt("hosttemplate_id")));
//						hostsPart[i] = host;
//					}
//					hosts = IOUtils.concatArrays(hosts, hostsPart);
//					hasNextPage = response.getJSONObject("scroll").getBoolean("hasNextPage");
//					page++;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return hosts;
//		}
//		
//		public Host getHost(int hostID) throws APIException {
//			Host host = null;
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/hosts/browser/" + hostID + ".json"), apiKey);
//				JSONObject response = https.getJSON();
//				JSONObject hostRaw = response.getJSONObject("mergedHost");
//				JSONObject hostStatusRaw = response.getJSONObject("hoststatus");
//				
//				HostAcknowledgement acknowledgement = null;
//				if (hostStatusRaw.optBoolean("problemHasBeenAcknowledged")) {
//					JSONObject acknowledgementRaw = response.getJSONObject("acknowledgement");
//					acknowledgement = new HostAcknowledgement(this, 
//							acknowledgementRaw.getString("author_name"), 
//							acknowledgementRaw.getString("comment_data"), 
//							acknowledgementRaw.getString("entry_time"), 
//							acknowledgementRaw.getBoolean("is_sticky"), 
//							acknowledgementRaw.getBoolean("notify_contacts"), 
//							acknowledgementRaw.getBoolean("persistent_comment"), 
//							hostID);
//				}
//				
//				HostStatus status = new HostStatus(
//						hostStatusRaw.optInt("currentState"), 
//						hostStatusRaw.optBoolean("isHardstate") ? 1 : 0, 
//						hostStatusRaw.optBoolean("isFlapping"), 
//						hostStatusRaw.optBoolean("flap_detection_enabled"), 
//						hostStatusRaw.optBoolean("notifications_enabled"), 
//						hostStatusRaw.optBoolean("isInMonitoring"), 
//						hostStatusRaw.optString("lastHardStateChangeUser"), 
//						hostStatusRaw.optString("last_time_up"), 
//						hostStatusRaw.optString("lastCheckUser"), 
//						hostStatusRaw.optString("nextCheckUser"), 
//						hostStatusRaw.optString("output"), 
//						hostStatusRaw.optString("long_output"), 
//						acknowledgement);
//				int[] sharedContainerIDs = new int[0];
//				try {
//					JSONObject sharedContainers = response.getJSONObject("sharedContainers");
//					Set<String> sharedContainerIDsRaw = sharedContainers.keySet();
//					sharedContainerIDs = new int[sharedContainerIDsRaw.size()];
//					int i = 0;
//					for (String s : sharedContainerIDsRaw) {
//						sharedContainerIDs[i] = Integer.parseInt(s);
//						i++;
//					}
//				} catch (JSONException e) {}
//				host = new Host(this, 
//						hostRaw.getInt("id"), 
//						UUID.fromString(hostRaw.getString("uuid")), 
//						hostRaw.getString("name"), 
//						hostRaw.getString("address"), 
//						hostRaw.getString("description"), 
//						hostRaw.getString("notes"), 
//						hostRaw.optInt("disabled") == 0 ? false : true, 
//						getSatellite(hostRaw.getInt("satellite_id")), 
//						hostRaw.getInt("container_id"), 
//						sharedContainerIDs, 
//						hostRaw.optString("tags").split(", "), 
//						Priority.fromInt(hostRaw.getInt("priority")), 
//						status,
//						new HostTemplate(this, hostRaw.optInt("hosttemplate_id")));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return host;
//		}
//		
//		public Host createHost(String name, String description, String address, HostTemplate template, String notes, String[] tags, Node container, 
//				String hostURL, Satellite satellite) throws APIException {
//			return createHost(name, description, address, template, notes, tags, container, new Node[0], hostURL, satellite, new Contact[0], new ContactGroup[0], new HostGroup[0], new Host[0]);
//		}
//		
//		public Host createHost(String name, String description, String address, HostTemplate template, String notes, String[] tags, Node container, Node[] sharedContainers, 
//				String hostURL, Satellite satellite, Contact[] contacts, ContactGroup[] contactGroups, HostGroup[] hostGroups, Host[] parentHosts) throws APIException {
//		
//			JSONObject host = new JSONObject();
//			
//			host.put("name", name);
//			host.put("description", description);
//			host.put("hosttemplate_id", template.getId());
//			host.put("address", address);
//			host.put("notes", notes);
//			host.put("container_id", container.getId());
//			host.put("host_url", hostURL);
//			host.put("satellite_id", satellite.getId());
//			host.put("tags", String.join(",", tags));
//			
//			JSONArray contactsJ = new JSONArray();
//			for (Contact c : contacts) {
//				contactsJ.put(c.getId());
//			}
//			host.put("contacts", new JSONObject().put("_ids", contactsJ));
//
//			JSONArray contactGroupsJ = new JSONArray();
//			for (ContactGroup cg : contactGroups) {
//				contactGroupsJ.put(cg.getContactGroupID());
//			}
//			host.put("contactgroups", new JSONObject().put("_ids", contactGroupsJ));
//
//			JSONArray hostGroupsJ = new JSONArray();
//			for (HostGroup hg : hostGroups) {
//				hostGroupsJ.put(hg.getHostGroupID());
//			}
//			host.put("hostgroups", new JSONObject().put("_ids", hostGroupsJ));
//
//			JSONArray sharedContainersJ = new JSONArray();
//			for (Node n : sharedContainers) {
//				sharedContainersJ.put(n.getId());
//			}
//			host.put("hosts_to_containers_sharing", new JSONObject().put("_ids", sharedContainersJ));
//
//			JSONArray parentHostsJ = new JSONArray();
//			for (Host h : parentHosts) {
//				parentHostsJ.put(h.getId());
//			}
//			host.put("parenthosts", new JSONObject().put("_ids", parentHostsJ));
//			
//			JSONObject body = new JSONObject();
//			body.put("Host", host);
//			
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/hosts/add.json"), apiKey, HTTPMethod.POST, body);
//				JSONObject response = https.getJSON();
//				return getHost(response.getInt("id"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			
//			return new Host(this, 0);
//		}
//
//		public HostAcknowledgement getHostAcknowledgement(int hostID) throws APIException {
//			HostAcknowledgement acknowledgement = null;
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/hosts/browser/" + hostID + ".json"), apiKey);
//				try {
//					JSONObject acknowledgementRaw = https.getJSON().getJSONObject("acknowledgement");
//					acknowledgement = new HostAcknowledgement(this, 
//							acknowledgementRaw.getString("author_name"), 
//							acknowledgementRaw.getString("comment_data"), 
//							acknowledgementRaw.getString("entry_time"), 
//							acknowledgementRaw.getBoolean("is_sticky"), 
//							acknowledgementRaw.getBoolean("notify_contacts"), 
//							acknowledgementRaw.getBoolean("persistent_comment"), 
//							hostID);
//				} catch (JSONException e) {}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return acknowledgement;
//		}
//
//		public ServiceAcknowledgement getServiceAcknowledgement(int serviceID) throws APIException {
//			ServiceAcknowledgement acknowledgement = null;
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/services/browser/" + serviceID + ".json"), apiKey);
//				try {
//					JSONObject acknowledgementRaw = https.getJSON().getJSONObject("acknowledgement");
//					acknowledgement = new ServiceAcknowledgement(this, 
//							acknowledgementRaw.getString("author_name"), 
//							acknowledgementRaw.getString("comment_data"), 
//							acknowledgementRaw.getString("entry_time"), 
//							acknowledgementRaw.getBoolean("is_sticky"), 
//							acknowledgementRaw.getBoolean("notify_contacts"), 
//							acknowledgementRaw.getBoolean("persistent_comment"), 
//							serviceID);
//				} catch (JSONException e) {}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return acknowledgement;
//		}
//		
//		public Service[] getServices() throws APIException {
//			Service[] services = new Service[0];
//			try {
//				boolean hasNextPage = true;
//				int page = 1;
//				while (hasNextPage) {
//					Map<String, String> args = new HashMap<>();
//					args.put("page", String.valueOf(page));
//					args.put("scroll", "true");
//					OitcHttpsClient https = new OitcHttpsClient(new URI(endpoint + "/services/index.json"), apiKey, HTTPMethod.GET, args);
//					JSONObject response = https.getJSON();
//					JSONArray servicesRaw = response.getJSONArray("all_services");
//					Service[] servicesPart = new Service[servicesRaw.length()];
//					for (int i = 0; i < servicesRaw.length(); i++) {
//						JSONObject serviceCompleteRaw = servicesRaw.getJSONObject(i);
//						JSONObject serviceRaw = serviceCompleteRaw.getJSONObject("Service");
//						JSONObject serviceStatusRaw = serviceCompleteRaw.getJSONObject("Servicestatus");
//						ServiceStatus status = new ServiceStatus(this,
//								serviceStatusRaw.getInt("currentState"), 
//								serviceStatusRaw.optBoolean("isHardstate"), 
//								serviceStatusRaw.optBoolean("isFlapping"), 
//								serviceStatusRaw.optBoolean("notifications_enabled"), 
//								serviceStatusRaw.optBoolean("isInMonitoring"), 
//								serviceStatusRaw.optInt("acknowledgement_type"),
//								serviceRaw.getInt("id"),
//								serviceStatusRaw.optString("lastCheck"), 
//								serviceStatusRaw.optString("nextCheck"), 
//								serviceStatusRaw.optString("lastHardStateChange"), 
//								serviceStatusRaw.optString("last_state_change"), 
//								serviceStatusRaw.optString("last_time_ok"), 
//								serviceStatusRaw.optString("output"), 
//								serviceStatusRaw.optString("long_output") 
//								);
//						Service service = new Service(this, 
//								serviceRaw.getInt("id"), 
//								serviceRaw.getString("uuid"), 
//								serviceRaw.getString("servicename"), 
//								serviceRaw.getString("description"), 
//								serviceRaw.optBoolean("disabled"), 
//								serviceRaw.optString("tags"), 
//								serviceRaw.getInt("priority"), 
//								status,
//								new Host(this, serviceRaw.getJSONObject("host").getJSONObject("Host").getInt("id")));
//						servicesPart[i] = service;
//					}
//					services = IOUtils.concatArrays(services, servicesPart);
//					hasNextPage = response.getJSONObject("scroll").getBoolean("hasNextPage");
//					page++;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return services;
//		}
//		
//		
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Acknowledgement
//
//	package de.schoenn.oITCAPI.Acknowledgements;
//
//	import java.time.LocalDateTime;
//	import java.time.format.DateTimeFormatter;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Utils.APIException;
//
//	public abstract class Acknowledgement {
//		
//		protected String author;
//		protected String comment;
//		protected LocalDateTime time;
//		protected boolean isSticky, notifyContacts, persistentComment, retrieved;
//		protected OITC oitc;
//		
//		public Acknowledgement(OITC oitc, int acknowledgementType) {
//			this.oitc = oitc;
//			this.isSticky = acknowledgementType == 2 ? true : false;
//			this.retrieved = false;
//		}
//		
//		public Acknowledgement(OITC oitc, String author, String comment, String time, 
//				boolean isSticky, boolean notifyContacts, boolean persistentComment) {
//			this.oitc = oitc;
//			this.author = author;
//			this.comment = comment;
//			this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.isSticky = isSticky;
//			this.notifyContacts = notifyContacts;
//			this.persistentComment = persistentComment;
//		}
//		
//		public abstract void retrieve() throws APIException;
//
//		public String getAuthor() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return author;
//		}
//
//		public String getComment() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return comment;
//		}
//
//		public LocalDateTime getTime() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return time;
//		}
//
//		public boolean isSticky() {
//			return isSticky;
//		}
//
//		public boolean isNotifyContacts() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return notifyContacts;
//		}
//
//		public boolean isPersistentComment() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return persistentComment;
//		}
//
//		@Override
//		public String toString() {
//			return "Acknowledgement [author=" + author + ", comment=" + comment + ", time=" + time + ", isSticky="
//					+ isSticky + ", notifyContacts=" + notifyContacts + ", persistentComment=" + persistentComment
//					+ ", retrieved=" + retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostAcknowledgement
//
//	package de.schoenn.oITCAPI.Acknowledgements;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Utils.APIException;
//
//	public class HostAcknowledgement extends Acknowledgement {
//
//		private int hostID;
//		
//		public HostAcknowledgement(OITC oitc, int acknowledgementType, int hostID) {
//			super(oitc, acknowledgementType);
//			this.hostID = hostID;
//		}
//
//		public HostAcknowledgement(OITC oitc, String author, String comment, String time, boolean isSticky,
//				boolean notifyContacts, boolean persistentComment, int hostID) {
//			super(oitc, author, comment, time, isSticky, notifyContacts, persistentComment);
//			this.hostID = hostID;
//		}
//		
//		@Override
//		public void retrieve() throws APIException {
//			HostAcknowledgement acknowledgement = oitc.getHostAcknowledgement(hostID);
//			this.author = acknowledgement.author;
//			this.comment = acknowledgement.comment;
//			this.time = acknowledgement.time;
//			this.isSticky = acknowledgement.isSticky;
//			this.notifyContacts = acknowledgement.notifyContacts;
//			this.persistentComment = acknowledgement.persistentComment;
//			this.retrieved = true;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: SrviceAckowledgement
//
//	package de.schoenn.oITCAPI.Acknowledgements;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Utils.APIException;
//
//	public class ServiceAcknowledgement extends Acknowledgement {
//
//		private int serviceID;
//		
//		public ServiceAcknowledgement(OITC oitc, int acknowledgementType, int serviceID) {
//			super(oitc, acknowledgementType);
//			// TODO Auto-generated constructor stub
//		}
//
//		public ServiceAcknowledgement(OITC oitc, String author, String comment, String time, boolean isSticky,
//				boolean notifyContacts, boolean persistentComment, int serviceID) {
//			super(oitc, author, comment, time, isSticky, notifyContacts, persistentComment);
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//		public void retrieve() throws APIException {
//			ServiceAcknowledgement acknowledgement = oitc.getServiceAcknowledgement(serviceID);
//			this.author = acknowledgement.author;
//			this.comment = acknowledgement.comment;
//			this.time = acknowledgement.time;
//			this.isSticky = acknowledgement.isSticky;
//			this.notifyContacts = acknowledgement.notifyContacts;
//			this.persistentComment = acknowledgement.persistentComment;
//			this.retrieved = true;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ContactGroup
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.util.Arrays;
//	import java.util.UUID;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Entities.Contact;
//
//	public class ContactGroup extends Node {
//
//		private int contactGroupID;
//		private UUID uuid;
//		private String description;
//		private Contact[] contacts;
//
//		/**
//		 * Minimal Constructor for a ContactGroup. Used when only referenced by its ID.
//		 * @param oitc
//		 * @param contactGroupID
//		 */
//		public ContactGroup(OITC oitc, int contactGroupID) {
//			super(oitc);
//			this.contactGroupID = contactGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a Contact Group. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param contactGroupID
//		 */
//		public ContactGroup(OITC oitc, int containerID, String name, Node parent, int contactGroupID) {
//			super(oitc, containerID, name, parent, null);
//			this.contactGroupID = contactGroupID;
//			this.retrieved = false;
//		}
//
//		
//		/**
//		 * Partial Constructor for a Contact Group. Used by https://{itchost}/contactgroups/index.json and https://{itchost}/hosts/browser/{HostID}.json
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param contactGroupID
//		 * @param uuid
//		 * @param description
//		 */
//		public ContactGroup(OITC oitc, int containerID, String name, Node parent, int contactGroupID, String uuid, String description) {
//			this(oitc, containerID, name, parent, contactGroupID);
//			this.uuid = UUID.fromString(uuid);
//			this.description = description;
//			this.retrieved = false;
//		}
//
//		/**
//		 * Full Constructor for a ContactGroup. Used by https://{itchost}/contactgroups/edit/{ContactGroupID}.json
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param contactGroupID
//		 * @param uuid
//		 * @param description
//		 * @param contactIDs
//		 */
//		public ContactGroup(OITC oitc, int containerID, String name, Node parent, int contactGroupID, String uuid, String description, int[] contactIDs) {
//			this(oitc, containerID, name, parent, contactGroupID, uuid, description);
//			this.contacts = new Contact[contactIDs.length];
//			for (int i = 0; i < contactIDs.length; i++) {
//				this.contacts[i] = new Contact(oitc, contactIDs[i]);
//			}
//			this.retrieved = true;
//		}
//
//		public int getContactGroupID() {
//			return contactGroupID;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public Contact[] getContacts() {
//			return contacts;
//		}
//
//		@Override
//		public String toString() {
//			return "ContactGroup [contactGroupID=" + contactGroupID + ", uuid=" + uuid + ", description=" + description
//					+ ", contacts=" + Arrays.toString(contacts) + ", id=" + id + ", name=" + name + ", parent=" + parent
//					+ ", children=" + Arrays.toString(children) + ", retrieved=" + retrieved
//					+ "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostGroup
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.util.Arrays;
//	import java.util.UUID;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Entities.Host.Host;
//	import de.schoenn.oITCAPI.Entities.Host.HostTemplate;
//
//	public class HostGroup extends Node {
//
//		private int hostGroupID;
//		private UUID uuid;
//		private String description, hostGroupURL;
//		private HostTemplate[] hostTemplates;
//		private Host[] hosts;
//		
//		/**
//		 * Minimal Constructor for a HostGroup. Used when only referenced by its ID.
//		 * @param oitc
//		 * @param hostGroupID
//		 */
//		public HostGroup(OITC oitc, int hostGroupID) {
//			super(oitc);
//			this.hostGroupID = hostGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a HostGroup. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param hostGroupID
//		 * @param name
//		 * @param parent
//		 */
//		public HostGroup(OITC oitc, int containerID, int hostGroupID, String name, Node parent) {
//			super(oitc, containerID, name, parent, null);
//			this.hostGroupID = hostGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a HostGroup. Used by https://{itchost}/hostgroups/index.json
//		 * @param oitc
//		 * @param containerID
//		 * @param hostGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param hostGroupURL
//		 */
//		public HostGroup(OITC oitc, int containerID, int hostGroupID, String name, Node parent, String uuid, String description, String hostGroupURL) {
//			this(oitc, containerID, hostGroupID, name, parent);
//			this.uuid = UUID.fromString(uuid);
//			this.description = description;
//			this.hostGroupURL = hostGroupURL;
//		}
//		
//		/**
//		 * Full Constructor for a HostGroup. Used by https://{itchost}/hostgroups/loadHostgroupWithHostsById/{HostGroupID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param hostGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param hostGroupURL
//		 * @param hostTemplateIDs
//		 * @param hostIDs
//		 */
//		public HostGroup(OITC oitc, int containerID, int hostGroupID, String name, Node parent, String uuid, String description, String hostGroupURL,
//				int[] hostTemplateIDs, int[] hostIDs) {
//			this(oitc, containerID, hostGroupID, name, parent, uuid, description, hostGroupURL);
//			this.hostTemplates = new HostTemplate[hostTemplateIDs.length];
//			for (int i = 0; i < hostTemplateIDs.length; i++) {
//				this.hostTemplates[i] = new HostTemplate(oitc, hostTemplateIDs[i]);
//			}
//			this.hosts = new Host[hostIDs.length];
//			for (int i = 0; i < hostIDs.length; i++) {
//				this.hosts[i] = new Host(oitc, hostIDs[i]);
//			}
//			this.retrieved = true;
//		}
//
//		public int getHostGroupID() {
//			return hostGroupID;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public String getHostGroupURL() {
//			return hostGroupURL;
//		}
//
//		public HostTemplate[] getHostTemplates() {
//			return hostTemplates;
//		}
//
//		public Host[] getHosts() {
//			return hosts;
//		}
//
//		@Override
//		public String toString() {
//			return "HostGroup [hostGroupID=" + hostGroupID + ", uuid=" + uuid + ", description=" + description
//					+ ", hostGroupURL=" + hostGroupURL + ", hostTemplates=" + Arrays.toString(hostTemplates) + ", hosts="
//					+ Arrays.toString(hosts) + ", oitc=" + oitc + ", id=" + id + ", name=" + name + ", parent=" + parent
//					+ ", children=" + Arrays.toString(children) + ", retrieved=" + retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Location
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.time.OffsetDateTime;
//	import java.util.Arrays;
//	import java.util.TimeZone;
//	import java.util.UUID;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class Location extends Node {
//
//		private int locationID;
//		private UUID uuid;
//		private String description;
//		private double latitude, longitude;
//		private TimeZone timezone;
//		private OffsetDateTime created, modified;
//		
//		/**
//		 * Partial Constructor for a Location. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param locationID
//		 */
//		public Location(OITC oitc, int containerID, String name, Node parent, int locationID) {
//			super(oitc, containerID);
//			this.parent = parent;
//			this.locationID = locationID;
//			this.retrieved = false;
//		}
//
//		/**
//		 * Partial Constructor for a Location. Used by https://{itchost}/locations/index.json and https://{itchost}/locations/edit/{LocationID}.json
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param locationID
//		 * @param uuid
//		 * @param description
//		 * @param latitude
//		 * @param longitude
//		 * @param timezone
//		 * @param created
//		 * @param modified
//		 */
//		public Location(OITC oitc, int containerID, String name, Node parent, int locationID, String uuid, String description, 
//				double latitude, double longitude, String timezone, String created, String modified) {
//			this(oitc, containerID, name, parent, locationID);
//			this.uuid = UUID.fromString(uuid);
//			this.description = description;
//			this.latitude = latitude;
//			this.longitude = longitude;
//			this.timezone = TimeZone.getTimeZone(timezone);
//			this.created = OffsetDateTime.parse(created);
//			this.modified = OffsetDateTime.parse(modified);
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Full Constructor for a Location. Currently unused.
//		 * @param containerID
//		 * @param name
//		 * @param parent
//		 * @param children
//		 * @param locationID
//		 * @param uuid
//		 * @param description
//		 * @param latitude
//		 * @param longitude
//		 * @param timezone
//		 * @param created
//		 * @param modified
//		 */
//		public Location(OITC oitc, int containerID, String name, Node parent,
//				Node[] children, int locationID, String uuid, String description, 
//				double latitude, double longitude, String timezone, String created, String modified) {
//			this(oitc, containerID, name, parent, locationID, uuid, description, latitude, longitude, timezone, created, modified);
//			this.children = children;
//			this.retrieved = true;
//		}
//
//		public int getLocationID() {
//			return locationID;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public double getLatitude() {
//			return latitude;
//		}
//
//		public double getLongitude() {
//			return longitude;
//		}
//
//		public TimeZone getTimezone() {
//			return timezone;
//		}
//
//		public OffsetDateTime getCreated() {
//			return created;
//		}
//
//		public OffsetDateTime getModified() {
//			return modified;
//		}
//
//		@Override
//		public String toString() {
//			return "Location [locationID=" + locationID + ", uuid=" + uuid + ", description=" + description + ", latitude="
//					+ latitude + ", longitude=" + longitude + ", timezone=" + timezone + ", created=" + created
//					+ ", modified=" + modified + ", id=" + id + ", name=" + name + ", parent=" + parent + ", children="
//					+ Arrays.toString(children) + ", retrieved=" + retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Node
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.util.Arrays;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class Node {
//
//		protected OITC oitc;
//		protected int id;
//		protected String name;
//		protected Node parent;
//		protected Node[] children;
//		protected boolean retrieved;
//		
//		/**
//		 * Minimal Constructor for a Node. Used only when sub-classes are referenced by their specific ID.
//		 * @param oitc
//		 */
//		public Node(OITC oitc) {
//			this.oitc = oitc;
//		}
//		
//		/**
//		 * Minimal Constructor for a Node. Used when referenced in the API by only its ID.
//		 * @param id
//		 */
//		public Node(OITC oitc, int id) {
//			this.oitc = oitc;
//			this.id = id;
//		}
//		
//		/**
//		 * Partial Constructor for a Node. Used by https://{itchost}/hosts/browser/{HostID}.json
//		 * @param id
//		 * @param name
//		 */
//		public Node(OITC oitc, int id, String name) {
//			this(oitc, id);
//			this.name = name;
//		}
//		
//		/**
//		 * Full Constructor for a Node. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param id
//		 * @param name
//		 * @param parent
//		 * @param children
//		 */
//		public Node(OITC oitc, int id, String name, Node parent, Node[] children) {
//			this(oitc, id, name);
//			this.parent = parent;
//			this.children = children;
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public Node getParent() {
//			return parent;
//		}
//
//		public Node[] getChildren() {
//			return children;
//		}
//
//		@Override
//		public String toString() {
//			return "Node [id=" + id + ", name=" + name + ", parent=" + parent + ", children="
//					+ Arrays.toString(children) + ", retrieved=" + retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: RootContainer
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class RootContainer extends Node {
//
//		/**
//		 * Minimal Constructor for the Root-Container. Used when not listing all Children.
//		 */
//		public RootContainer(OITC oitc) {
//			super(oitc, 1, "root");
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Full Constructor for the Root-Container. Used when listing the whole container-tree.
//		 * @param children
//		 */
//		public RootContainer(OITC oitc, Node[] children) {
//			super(oitc, 1, "root", null, children);
//			this.retrieved = true;
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ServiceGroup
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.util.Arrays;
//	import java.util.UUID;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Entities.Host.Host;
//	import de.schoenn.oITCAPI.Entities.Service.Service;
//	import de.schoenn.oITCAPI.Entities.Service.ServiceTemplate;
//
//	public class ServiceGroup extends Node {
//
//		private int serviceGroupID;
//		private UUID uuid;
//		private String description, serviceGroupURL;
//		private Service[] services;
//		private ServiceTemplate[] serviceTemplates;
//		
//		/**
//		 * Minimal Constructor for a ServiceGroup. Used when only referenced by its ID.
//		 * @param oitc
//		 * @param hostGroupID
//		 */
//		public ServiceGroup(OITC oitc, int serviceGroupID) {
//			super(oitc);
//			this.serviceGroupID = serviceGroupID;
//			this.retrieved = false;
//		}
//
//		/**
//		 * Partial Constructor for a ServiceGroup. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceGroupID
//		 * @param name
//		 * @param parent
//		 */
//		public ServiceGroup(OITC oitc, int containerID, int serviceGroupID, String name, Node parent) {
//			super(oitc, containerID, name, parent, null);
//			this.serviceGroupID = serviceGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a ServiceGroup. Used by https://{itchost}/servicegroups/index.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param serviceGroupURL
//		 */
//		public ServiceGroup(OITC oitc, int containerID, int serviceGroupID, String name, Node parent, String uuid, String description, String serviceGroupURL) {
//			this(oitc, containerID, serviceGroupID, name, parent);
//			this.uuid = UUID.fromString(uuid);
//			this.description = description;
//			this.serviceGroupURL = serviceGroupURL;
//		}
//
//		/**
//		 * Full Constructor for a ServiceGroup. Used by https://{itchost}/servicegroups/edit/{ServiceGroupID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param serviceGroupURL
//		 * @param serviceIDs
//		 * @param serviceTemplateIDs
//		 */
//		public ServiceGroup(OITC oitc, int containerID, int serviceGroupID, String name, Node parent, String uuid, String description, String serviceGroupURL, 
//				int[] serviceIDs, int[] serviceTemplateIDs) {
//			this(oitc, containerID, serviceGroupID, name, parent, uuid, description, serviceGroupURL);
//			this.services = new Service[serviceIDs.length];
//			for (int i = 0; i < serviceIDs.length; i++) {
//				//TODO: Fix getting Host
//				this.services[i] = new Service(oitc, serviceIDs[i], new Host(oitc, 0));
//			}
//			this.serviceTemplates = new ServiceTemplate[serviceTemplateIDs.length];
//			for (int i = 0; i < serviceTemplateIDs.length; i++) {
//				this.serviceTemplates[i] = new ServiceTemplate(oitc, serviceTemplateIDs[i]);
//			}
//			this.retrieved = true;
//		}
//
//		public int getServiceGroupID() {
//			return serviceGroupID;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public String getServiceGroupURL() {
//			return serviceGroupURL;
//		}
//
//		public Service[] getServices() {
//			return services;
//		}
//
//		public ServiceTemplate[] getServiceTemplates() {
//			return serviceTemplates;
//		}
//
//		@Override
//		public String toString() {
//			return "ServiceGroup [serviceGroupID=" + serviceGroupID + ", uuid=" + uuid + ", description=" + description
//					+ ", serviceGroupURL=" + serviceGroupURL + ", services=" + Arrays.toString(services)
//					+ ", serviceTemplates=" + Arrays.toString(serviceTemplates) + ", oitc=" + oitc + ", id=" + id
//					+ ", name=" + name + ", parent=" + parent + ", children=" + Arrays.toString(children) + ", retrieved="
//					+ retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ServiceTemplateGroup
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.time.OffsetDateTime;
//	import java.util.Arrays;
//	import java.util.UUID;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Entities.Service.ServiceTemplate;
//
//	public class ServiceTemplateGroup extends Node {
//
//		private int serviceTemplateGroupID;
//		private UUID uuid;
//		private String description;
//		private OffsetDateTime created, modified;
//		private ServiceTemplate[] serviceTemplates;
//		
//		/**
//		 * Minimal Constructor for a ServiceTemplateGroup. Used when only referenced by its ID.
//		 * @param oitc
//		 * @param serviceTemplateGroupID
//		 */
//		public ServiceTemplateGroup(OITC oitc, int serviceTemplateGroupID) {
//			super(oitc);
//			this.serviceTemplateGroupID = serviceTemplateGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a ServiceTemplateGroup. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceTemplateGroupID
//		 * @param name
//		 * @param parent
//		 */
//		public ServiceTemplateGroup(OITC oitc, int containerID, int serviceTemplateGroupID, String name, Node parent) {
//			super(oitc, containerID, name, parent, null);
//			this.serviceTemplateGroupID = serviceTemplateGroupID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a ServiceTemplateGroup. Used by https://{itchost}/servicetemplategroups/index.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceTemplateGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param created
//		 * @param modified
//		 */
//		public ServiceTemplateGroup(OITC oitc, int containerID, int serviceTemplateGroupID, String name, Node parent, 
//				String uuid, String description, String created, String modified) {
//			this(oitc, containerID, serviceTemplateGroupID, name, parent);
//			this.uuid = UUID.fromString(uuid);
//			this.description = description;
//			this.created = OffsetDateTime.parse(created);
//			this.modified = OffsetDateTime.parse(modified);
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Full Constructor for a ServiceTemplateGroup. Used by https://{itchost}/servicetemplategroups/edit/{ServiceTemplateGroupID}.json
//		 * @param oitc
//		 * @param containerID
//		 * @param serviceTemplateGroupID
//		 * @param name
//		 * @param parent
//		 * @param uuid
//		 * @param description
//		 * @param created
//		 * @param modified
//		 * @param serviceTemplateIDs
//		 */
//		public ServiceTemplateGroup(OITC oitc, int containerID, int serviceTemplateGroupID, String name, Node parent, 
//				String uuid, String description, String created, String modified, int[] serviceTemplateIDs) {
//			this(oitc, containerID, serviceTemplateGroupID, name, parent, uuid, description, created, modified);
//			this.serviceTemplates = new ServiceTemplate[serviceTemplateIDs.length];
//			for (int i = 0; i < serviceTemplateIDs.length; i++) {
//				this.serviceTemplates[i] = new ServiceTemplate(oitc, serviceTemplateIDs[i]);
//			}
//			this.retrieved = true;
//		}
//
//		public int getServiceTemplateGroupID() {
//			return serviceTemplateGroupID;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public OffsetDateTime getCreated() {
//			return created;
//		}
//
//		public OffsetDateTime getModified() {
//			return modified;
//		}
//
//		public ServiceTemplate[] getServiceTemplates() {
//			return serviceTemplates;
//		}
//
//		@Override
//		public String toString() {
//			return "ServiceTemplateGroup [serviceTemplateGroupID=" + serviceTemplateGroupID + ", uuid=" + uuid
//					+ ", description=" + description + ", created=" + created + ", modified=" + modified
//					+ ", serviceTemplates=" + Arrays.toString(serviceTemplates) + ", oitc=" + oitc + ", id=" + id
//					+ ", name=" + name + ", parent=" + parent + ", children=" + Arrays.toString(children) + ", retrieved="
//					+ retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Tenant
//
//	package de.schoenn.oITCAPI.Containers;
//
//	import java.time.OffsetDateTime;
//	import java.util.Arrays;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class Tenant extends Node {
//
//		private int tenantID;
//		private String description, firstName, lastName, street, zipCode, city;
//		private OffsetDateTime created, modified;
//		
//		/**
//		 * Partial Constructor for a Tenant. Used by https://{itchost}/containers/loadContainersByContainerId/{ContainerID}.json
//		 * @param containerID
//		 * @param tenantID
//		 * @param name
//		 */
//		public Tenant(OITC oitc, int containerID, int tenantID, String name, Node[] children) {
//			super(oitc, containerID, name, new RootContainer(oitc), children);
//			this.tenantID = tenantID;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Partial Constructor for a Tenant. Used by https://{itchost}/tenants/index.json and https://{itchost}/tenants/edit/{TenantID}.json
//		 * @param containerID
//		 * @param tenantID
//		 * @param name
//		 * @param parent
//		 * @param description
//		 * @param firstName
//		 * @param lastName
//		 * @param street
//		 * @param zipCode
//		 * @param city
//		 * @param created
//		 * @param modified
//		 */
//		public Tenant(OITC oitc, int containerID, int tenantID, String name, 
//				String description, String firstName, String lastName, String street, String zipCode, String city, String created, String modified) {
//			super(oitc, containerID, name, new RootContainer(oitc), null);
//			this.tenantID = tenantID;
//			this.description = description;
//			this.firstName = firstName;
//			this.lastName = lastName;
//			this.street = street;
//			this.zipCode = zipCode;
//			this.city = city;
//			this.created = OffsetDateTime.parse(created);
//			this.modified = OffsetDateTime.parse(modified);
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Full Constructor for a Tenant. Not currently used.
//		 * @param containerID
//		 * @param tenantID
//		 * @param name
//		 * @param parent
//		 * @param children
//		 * @param description
//		 * @param firstName
//		 * @param lastName
//		 * @param street
//		 * @param zipCode
//		 * @param city
//		 * @param created
//		 * @param modified
//		 */
//		public Tenant(OITC oitc, int containerID, int tenantID, String name, Node[] children, 
//				String description, String firstName, String lastName, String street, String zipCode, String city, String created, String modified) {
//			this(oitc, containerID, tenantID, name, description, firstName, lastName, street, zipCode, city, created, modified);
//			this.children = children;
//			this.retrieved = true;
//		}
//
//		public int getTenantID() {
//			return tenantID;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public String getFirstName() {
//			return firstName;
//		}
//
//		public String getLastName() {
//			return lastName;
//		}
//
//		public String getStreet() {
//			return street;
//		}
//
//		public String getZipCode() {
//			return zipCode;
//		}
//
//		public String getCity() {
//			return city;
//		}
//
//		public OffsetDateTime getCreated() {
//			return created;
//		}
//
//		public OffsetDateTime getModified() {
//			return modified;
//		}
//
//		@Override
//		public String toString() {
//			return "Tenant [tenantID=" + tenantID + ", description=" + description + ", firstName=" + firstName
//					+ ", lastName=" + lastName + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city
//					+ ", created=" + created + ", modified=" + modified + ", id=" + id + ", name=" + name + ", parent="
//					+ parent + ", children=" + Arrays.toString(children) + ", retrieved="
//					+ retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Contact
//
//	package de.schoenn.oITCAPI.Entities;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class Contact {
//
//		private OITC oitc;
//		private int id;
//		
//		public Contact(OITC oitc, int id) {
//			this.oitc = oitc;
//			this.id = id;
//		}
//		
//		public int getId() {
//			return id;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Sattelite
//
//	package de.schoenn.oITCAPI.Entities;
//
//	import java.time.OffsetDateTime;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Containers.Node;
//	import de.schoenn.oITCAPI.Utils.APIException;
//
//	public class Satellite {
//		
//		private int id;
//		private String name, address, description, timezone;
//		private Node container;
//		private OffsetDateTime created, modified;
//		private boolean retrieved;
//		private OITC oitc;
//		
//		public Satellite(OITC oitc, int id, String name) {
//			this.oitc = oitc;
//			this.id = id;
//			this.name = name;
//			this.retrieved = false;
//		}
//		
//		public Satellite(OITC oitc, int id, String name, String address, String description, String timezone, int containerID, String created, String modified) {
//			this.oitc = oitc;
//			this.id = id;
//			this.name = name;
//			this.address = address;
//			this.description = description;
//			this.timezone = timezone;
//			this.container = new Node(oitc, containerID);
//			this.created = OffsetDateTime.parse(created);
//			this.modified = OffsetDateTime.parse(modified);
//			this.retrieved = true;
//		}
//		
//		public void retrieve() throws APIException {
//			Satellite satellite = oitc.getSatellite(id);
//			this.name = satellite.name;
//			this.address = satellite.address;
//			this.description = satellite.description;
//			this.timezone = satellite.timezone;
//			this.container = satellite.container;
//			this.created = satellite.created;
//			this.modified = satellite.modified;
//			this.retrieved = true;
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public String getAddress() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return address;
//		}
//
//		public String getDescription() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return description;
//		}
//
//		public String getTimezone() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return timezone;
//		}
//
//		public Node getContainer() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return container;
//		}
//
//		public OffsetDateTime getCreated() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return created;
//		}
//
//		public OffsetDateTime getModified() {
//			if (!retrieved) {
//				try {
//					retrieve();
//				} catch (APIException e) {
//					e.printStackTrace();
//				}
//			}
//			return modified;
//		}
//
//		@Override
//		public String toString() {
//			return "Satellite [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
//					+ ", timezone=" + timezone + ", container=" + container + ", created=" + created + ", modified="
//					+ modified + ", retrieved=" + retrieved + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Host
//
//	package de.schoenn.oITCAPI.Entities.Host;
//
//	import java.io.IOException;
//	import java.net.URI;
//	import java.net.URISyntaxException;
//	import java.util.Arrays;
//	import java.util.HashMap;
//	import java.util.Map;
//	import java.util.UUID;
//
//	import org.json.JSONArray;
//	import org.json.JSONObject;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Containers.Node;
//	import de.schoenn.oITCAPI.Entities.Satellite;
//	import de.schoenn.oITCAPI.Entities.Service.Service;
//	import de.schoenn.oITCAPI.Entities.Service.ServiceStatus;
//	import de.schoenn.oITCAPI.Enums.Priority;
//	import de.schoenn.oITCAPI.Utils.APIException;
//	import de.schoenn.oITCAPI.Utils.HTTPMethod;
//	import de.schoenn.oITCAPI.Utils.IOUtils;
//	import de.schoenn.oITCAPI.Utils.OitcHttpsClient;
//
//	public class Host {
//
//		private OITC oitc;
//		private int id;
//		private UUID uuid;
//		private String hostname, address, description, notes;
//		private boolean disabled, retrieved, servicesRetrieved;
//		private Satellite satellite;
//		private Node container;
//		private Node[] sharedContainers;
//		private String[] tags;
//		private Priority priority;
//		private HostStatus status;
//		private Service[] services;
//		private HostTemplate template;
//		
//		public Host(OITC oitc, int id) {
//			this.oitc = oitc;
//			this.id = id;
//			this.services = new Service[0];
//			this.retrieved = false;
//			this.servicesRetrieved = false;
//		}
//		
//		public Host(OITC oitc, int id, UUID uuid, String hostname, String address, String description, String notes, 
//				boolean disabled, int satelliteID, String satelliteName, int containerID,
//				int[] sharedContainerIDs, String[] tags, Priority priority, HostStatus status, HostTemplate template) {
//			this(oitc, id);
//			this.uuid = uuid;
//			this.hostname = hostname;
//			this.address = address;
//			this.description = description;
//			this.notes = notes;
//			this.disabled = disabled;
//			this.satellite = new Satellite(oitc, satelliteID, satelliteName);
//			this.container = new Node(oitc, containerID);
//			this.sharedContainers = new Node[sharedContainerIDs.length];
//			for (int i = 0; i < sharedContainerIDs.length; i++) {
//				this.sharedContainers[i] = new Node(oitc, sharedContainerIDs[i]);
//			}
//			this.tags = tags;
//			this.priority = priority;
//			this.status = status;
//			this.retrieved = true;
//			this.servicesRetrieved = false;
//			this.template = template;
//		}
//		
//		public Host(OITC oitc, int id, UUID uuid, String hostname, String address, String description, String notes, 
//				boolean disabled, Satellite satellite, int containerID,
//				int[] sharedContainerIDs, String[] tags, Priority priority, HostStatus status, HostTemplate template) {
//			this(oitc, id);
//			this.uuid = uuid;
//			this.hostname = hostname;
//			this.address = address;
//			this.description = description;
//			this.notes = notes;
//			this.disabled = disabled;
//			this.satellite = satellite;
//			this.container = new Node(oitc, containerID);
//			this.sharedContainers = new Node[sharedContainerIDs.length];
//			for (int i = 0; i < sharedContainerIDs.length; i++) {
//				this.sharedContainers[i] = new Node(oitc, sharedContainerIDs[i]);
//			}
//			this.tags = tags;
//			this.priority = priority;
//			this.status = status;
//			this.retrieved = true;
//			this.servicesRetrieved = false;
//			this.template = template;
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getHostname() {
//			return hostname;
//		}
//		
//		public Host setHostname(String hostname) {
//			this.hostname = hostname;
//			return this;
//		}
//
//		public String getAddress() {
//			return address;
//		}
//		
//		public Host setAddress(String address) {
//			this.address = address;
//			return this;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//		
//		public Host setDescription(String description) {
//			this.description = description;
//			return this;
//		}
//
//		public String getNotes() {
//			return notes;
//		}
//		
//		public Host setNotes(String notes) {
//			this.notes = notes;
//			return this;
//		}
//
//		public boolean isDisabled() {
//			return disabled;
//		}
//
//		public Satellite getSatellite() {
//			return satellite;
//		}
//		
//		public Host setSatellite(Satellite satellite) {
//			this.satellite = satellite;
//			return this;
//		}
//
//		public Node getContainer() {
//			return container;
//		}
//		
//		public Host setContainer(Node container) {
//			this.container = container;
//			return this;
//		}
//
//		public Node[] getSharedContainers() {
//			return sharedContainers;
//		}
//		
//		public Host clearSharedContainers() {
//			this.sharedContainers = new Node[0];
//			return this;
//		}
//		
//		public Host addSharedContainer(Node container) {
//			Node[] temp = sharedContainers.clone();
//			sharedContainers = new Node[temp.length+1];
//			for (int i = 0; i < temp.length; i++) {
//				sharedContainers[i] = temp[i];
//			}
//			sharedContainers[temp.length] = container;
//			return this;
//		}
//		
//		public Host removeSharedContainer(Node container) {
//			int toRemove = -1;
//			for (int i = 0; i < sharedContainers.length; i++) {
//				if (container.getId() == sharedContainers[i].getId()) {
//					toRemove = i;
//					break;
//				}
//			}
//			if (toRemove >= 0) {
//				Node[] temp = sharedContainers.clone();
//				sharedContainers = new Node[temp.length-1];
//				for (int i = 0; i < temp.length; i++) {
//					if (i == toRemove) continue;
//					sharedContainers[i >= toRemove ? i-1 : i] = temp[i];
//				}
//			}
//			return this;
//		}
//		
//		public Host setSharedContainers(Node[] sharedContainers) {
//			this.sharedContainers = sharedContainers;
//			return this;
//		}
//
//		public String[] getTags() {
//			return tags;
//		}
//		
//		public Host clearTags() {
//			this.tags = new String[0];
//			return this;
//		}
//		
//		public Host addTag(String tag) {
//			String[] temp = tags.clone();
//			tags = new String[temp.length+1];
//			for (int i = 0; i < temp.length; i++) {
//				tags[i] = temp[i];
//			}
//			tags[temp.length] = tag;
//			return this;
//		}
//		
//		public Host removeTag(String tag) {
//			int toRemove = -1;
//			for (int i = 0; i < tags.length; i++) {
//				if (tag.equals(tags[i])) {
//					toRemove = i;
//					break;
//				}
//			}
//			if (toRemove >= 0) {
//				String[] temp = tags.clone();
//				tags = new String[temp.length-1];
//				for (int i = 0; i < temp.length; i++) {
//					if (i == toRemove) continue;
//					tags[i >= toRemove ? i-1 : i] = temp[i];
//				}
//			}
//			return this;
//		}
//		
//		public Host setTags(String[] tags) {
//			this.tags = tags;
//			return this;
//		}
//
//		public Priority getPriority() {
//			return priority;
//		}
//		
//		public Host setPriority(Priority priority) {
//			this.priority = priority;
//			return this;
//		}
//
//		public HostStatus getStatus() {
//			return status;
//		}
//		
//		public HostTemplate getTemplate() {
//			return template;
//		}
//		
//		public Host setTemplate(HostTemplate template) {
//			this.template = template;
//			return this;
//		}
//		
//		public void acknowledge(boolean recurse, String author, String comment, boolean notify, boolean sticky) throws APIException {
//			try {
//				JSONObject ack = new JSONObject();
//				ack.put("command", "submitHoststateAck");
//				ack.put("hostUuid", getUuid().toString());
//				ack.put("hostAckType", recurse ? "hostAndServices" : "hostOnly");
//				ack.put("author", author);
//				ack.put("comment", comment);
//				ack.put("notify", notify);
//				ack.put("sticky", sticky ? 2 : 0);
//				JSONArray payload = new JSONArray();
//				payload.put(ack);
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/nagios_module/cmd/submit_bulk_naemon.json"), oitc.getApiKey(), HTTPMethod.POST, payload);
//				JSONObject response = https.getJSON();
//				System.out.println(response.toString(2));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		public void removeAcknowledgement(boolean recurse) throws APIException {
//			try {
//				if (recurse) {
//					for (Service service : getServices()) {
//						service.removeAcknowledgement();
//					}
//				}
//				JSONObject payload = new JSONObject();
//				payload.put("hostId", getId());
//				payload.put("serviceId", JSONObject.NULL);
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/acknowledgements/delete/.json"), oitc.getApiKey(), HTTPMethod.POST, payload);
//				JSONObject response = https.getJSON();
//				System.out.println(response.toString(2));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		public Service[] getServices() throws APIException {
//			if (!servicesRetrieved) {
//				services = new Service[0];
//				try {
//					boolean hasNextPage = true;
//					int page = 1;
//					while (hasNextPage) {
//						Map<String, String> args = new HashMap<>();
//						args.put("page", String.valueOf(page));
//						args.put("scroll", "true");
//						args.put("filter%5BHosts.id%5D", String.valueOf(getId()));
//						// filter%5Bservicename%5D=&filter%5Bservicename_regex%5D=false&filter%5BServicestatus.output%5D=
//						OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/services/index.json"), oitc.getApiKey(), HTTPMethod.GET, args);
//						JSONObject response = https.getJSON();
//						JSONArray servicesRaw = response.getJSONArray("all_services");
//						Service[] servicesPart = new Service[servicesRaw.length()];
//						for (int i = 0; i < servicesRaw.length(); i++) {
//							JSONObject serviceCompleteRaw = servicesRaw.getJSONObject(i);
//							JSONObject serviceRaw = serviceCompleteRaw.getJSONObject("Service");
//							JSONObject serviceStatusRaw = serviceCompleteRaw.getJSONObject("Servicestatus");
//							ServiceStatus status = new ServiceStatus(oitc,
//									serviceStatusRaw.getInt("currentState"), 
//									serviceStatusRaw.optBoolean("isHardstate"), 
//									serviceStatusRaw.optBoolean("isFlapping"), 
//									serviceStatusRaw.optBoolean("notifications_enabled"), 
//									serviceStatusRaw.optBoolean("isInMonitoring"), 
//									serviceStatusRaw.optInt("acknowledgement_type"),
//									serviceRaw.getInt("id"),
//									serviceStatusRaw.optString("lastCheck"), 
//									serviceStatusRaw.optString("nextCheck"), 
//									serviceStatusRaw.optString("lastHardStateChange"), 
//									serviceStatusRaw.optString("last_state_change"), 
//									serviceStatusRaw.optString("last_time_ok"), 
//									serviceStatusRaw.optString("output"), 
//									serviceStatusRaw.optString("long_output") 
//									);
//							Service service = new Service(oitc, 
//									serviceRaw.getInt("id"), 
//									serviceRaw.getString("uuid"), 
//									serviceRaw.getString("servicename"), 
//									serviceRaw.getString("description"), 
//									serviceRaw.optBoolean("disabled"), 
//									serviceRaw.optString("tags"), 
//									serviceRaw.getInt("priority"), 
//									status,
//									this);
//							servicesPart[i] = service;
//						}
//						services = IOUtils.concatArrays(services, servicesPart);
//						hasNextPage = response.getJSONObject("scroll").getBoolean("hasNextPage");
//						page++;
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (URISyntaxException e) {
//					e.printStackTrace();
//				}
//				this.servicesRetrieved = true;
//			}
//			return services;
//		}
//		
//		public boolean enable() throws APIException {
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/hosts/enable/" + getId() + ".json"), oitc.getApiKey(), HTTPMethod.POST);
//				JSONObject response = https.getJSON();
//				return response.optBoolean("success");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//		
//		public boolean disable() throws APIException {
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/hosts/deactivate/" + getId() + ".json"), oitc.getApiKey(), HTTPMethod.POST);
//				JSONObject response = https.getJSON();
//				return response.optBoolean("success");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//		
//		public boolean delete() throws APIException {
//			try {
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/hosts/delete/" + getId() + ".json"), oitc.getApiKey(), HTTPMethod.POST);
//				JSONObject response = https.getJSON();
//				return response.optBoolean("success");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//		
//		public boolean push() throws APIException {
//			try {
//				JSONObject host = new JSONObject();
//				host.put("hosttemplate_id", getTemplate().getId());
//				host.put("name", getHostname());
//				host.put("address", getAddress());
//				host.put("description", getDescription());
//				host.put("notes", getNotes());
//				host.put("satellite_id", getSatellite().getId());
//				host.put("container_id", getContainer().getId());
//				host.put("priority", getPriority().getID());
//				host.put("tags", String.join(",", tags));
//				JSONArray sharedCont = new JSONArray();
//				for (Node node : sharedContainers) {
//					sharedCont.put(node.getId());
//				}
//				host.put("hosts_to_containers_sharing", new JSONObject().put("_ids", sharedCont));
//				JSONObject payload = new JSONObject();
//				payload.put("Host", host);
//				System.out.println(payload.toString(2));
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/hosts/edit/" + getId() + ".json"), oitc.getApiKey(), HTTPMethod.POST, payload);
//				JSONObject response = https.getJSON();
//				System.out.println(response.toString(2));
//				return response.optInt("id") == getId();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//		
//		public void pull() throws APIException {
//			Host temp = oitc.getHost(getId());
//			hostname = temp.hostname;
//			address = temp.address;
//			description = temp.description;
//			notes = temp.description;
//			disabled = temp.disabled;
//			retrieved = temp.retrieved;
//			servicesRetrieved = temp.servicesRetrieved;
//			satellite = temp.satellite;
//			container = temp.container;
//			sharedContainers = temp.sharedContainers;
//			tags = temp.tags;
//			priority = temp.priority;
//			status = temp.status;
//			services = temp.services;
//			template = temp.template;
//			getServices();
//		}
//		
//		@Override
//		public String toString() {
//			return "Host [oitc=" + oitc + ", id=" + id + ", uuid=" + uuid + ", hostname=" + hostname + ", address="
//					+ address + ", description=" + description + ", notes=" + notes + ", disabled=" + disabled
//					+ ", retrieved=" + retrieved + ", satellite=" + satellite + ", container=" + container
//					+ ", sharedContainers=" + Arrays.toString(sharedContainers) + ", tags=" + Arrays.toString(tags)
//					+ ", priority=" + priority + ", status=" + status + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostFilter
//
//	package de.schoenn.oITCAPI.Entities.Host;
//
//	import java.util.Locale;
//
//	import org.json.JSONArray;
//	import org.json.JSONObject;
//
//	import de.schoenn.oITCAPI.Entities.Satellite;
//	import de.schoenn.oITCAPI.Enums.Priority;
//	import de.schoenn.oITCAPI.Enums.StateType;
//
//	public class HostFilter {
//
//		private String name, address, description, output, isAcknowledged, isInDowntime, notificationsEnabled;
//		private boolean isNameRegex, isAddressRegex;
//		private String[] tags, excludedTags;
//		private int[] satelliteIDs;
//		private HostState[] states;
//		private StateType stateType;
//		private Priority[] priorities;
//		
//		public HostFilter() {
//			this.name = "";
//			this.address = "";
//			this.description = "";
//			this.output = "";
//			this.isAcknowledged = "";
//			this.isInDowntime = "";
//			this.notificationsEnabled = "";
//			this.isNameRegex = false;
//			this.isAddressRegex = false;
//			this.tags = new String[0];
//			this.excludedTags = new String[0];
//			this.satelliteIDs = new int[0];
//			this.states = new HostState[0];
//			this.stateType = StateType.UNDEFINED;
//			this.priorities = new Priority[0];
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public void setAddress(String address) {
//			this.address = address;
//		}
//
//		public void setDescription(String description) {
//			this.description = description;
//		}
//
//		public void setOutput(String output) {
//			this.output = output;
//		}
//
//		public void setNameRegex(boolean isNameRegex) {
//			this.isNameRegex = isNameRegex;
//		}
//
//		public void setAddressRegex(boolean isAddressRegex) {
//			this.isAddressRegex = isAddressRegex;
//		}
//
//		public void setAcknowledged(boolean isAcknowledged) {
//			this.isAcknowledged = String.valueOf(isAcknowledged);
//		}
//
//		public void setInDowntime(boolean isInDowntime) {
//			this.isInDowntime = String.valueOf(isInDowntime);
//		}
//
//		public void setNotificationsEnabled(boolean notificationsEnabled) {
//			this.notificationsEnabled = String.valueOf(notificationsEnabled);
//		}
//
//		public void setTags(String[] tags) {
//			this.tags = tags;
//		}
//
//		public void setExcludedTags(String[] excludedTags) {
//			this.excludedTags = excludedTags;
//		}
//		
//		public void setSatellites(int[] satelliteIDs) {
//			this.satelliteIDs = satelliteIDs;
//		}
//
//		public void setSatellites(Satellite[] satellites) {
//			this.satelliteIDs = new int[satellites.length];
//			for (int i = 0; i < satellites.length; i++) {
//				this.satelliteIDs[i] = satellites[i].getId();
//			}
//		}
//
//		public void setStates(HostState[] states) {
//			this.states = states;
//		}
//
//		public void setStateType(StateType stateType) {
//			this.stateType = stateType;
//		}
//
//		public void setPriorities(Priority[] priorities) {
//			this.priorities = priorities;
//		}
//		
//		public JSONObject build() {
//			JSONObject returns = new JSONObject();
//			JSONObject filter = new JSONObject();
//			filter.put("Hosts.id", new JSONArray());
//			filter.put("Hosts.name", name);
//			filter.put("Hosts.name_regex", isNameRegex);
//			filter.put("Hosts.keywords", new JSONArray(tags));
//			filter.put("Hosts.not_keywords", new JSONArray(excludedTags));
//			filter.put("Hosts.address", address);
//			filter.put("Hosts.address_regex", isAddressRegex);
//			filter.put("Hosts.satellite_id", new JSONArray(satelliteIDs));
//			filter.put("Hosts.host_type", new JSONArray());
//			filter.put("hostdescription", description);
//			filter.put("Hoststatus.output", output);
//			JSONArray hostStates = new JSONArray();
//			for (HostState state : states) {
//				hostStates.put(state.toString().toLowerCase(Locale.ROOT));
//			}
//			filter.put("Hoststatus.current_state", hostStates);
//			filter.put("Hoststatus.problem_has_been_acknowledged", isAcknowledged);
//			filter.put("Hoststatus.scheduled_downtime_depth", isInDowntime);
//			filter.put("Hoststatus.notifications_enabled", notificationsEnabled);
//			filter.put("Hoststatus.is_hardstate", stateType == StateType.HARD ? "1" : (stateType == StateType.SOFT ? "0" : ""));
//			filter.put("Hoststatus.active_checks_enabled", "");
//			JSONArray priorities = new JSONArray();
//			for (Priority priority : this.priorities) {
//				priorities.put(priority.getID());
//			}
//			filter.put("hostpriority", priorities);
//			returns.put("filter", filter);
//			return returns;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostState
//
//	package de.schoenn.oITCAPI.Entities.Host;
//
//	public enum HostState {
//
//		UNDEFINED,
//		UP,
//		DOWN,
//		UNREACHABLE;
//		
//		public static HostState fromInt(int hostState) {
//			if (hostState == 0) return UP;
//			if (hostState == 1) return DOWN;
//			if (hostState == 2) return UNREACHABLE;
//			return UNDEFINED;
//		}
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostStatus
//
//	package de.schoenn.oITCAPI.Entities.Host;
//
//	import java.time.LocalDateTime;
//	import java.time.format.DateTimeFormatter;
//
//	import de.schoenn.oITCAPI.Acknowledgements.Acknowledgement;
//	import de.schoenn.oITCAPI.Acknowledgements.HostAcknowledgement;
//	import de.schoenn.oITCAPI.Enums.StateType;
//
//	public class HostStatus {
//
//		private HostState currentState;
//		private StateType stateType;
//		private boolean isFlapping, flapDetectionEnabled, notificationsEnabled, isInMonitoring;
//		private LocalDateTime lastHardStateChange, lastUp, lastCheck, nextCheck;
//		private String output, longOutput;
//		private HostAcknowledgement acknowledgement;
//		
//		public HostStatus(int currentState, 
//				int stateType, 
//				boolean isFlapping, 
//				boolean flapDetectionEnabled, 
//				boolean notificationsEnabled, 
//				boolean isInMonitoring, 
//				String lastHardStateChange, 
//				String lastUp, 
//				String lastCheck, 
//				String nextCheck, 
//				String output, 
//				String longOutput, 
//				HostAcknowledgement acknowledgement) {
//			this.currentState = HostState.fromInt(currentState);
//			this.stateType = StateType.fromInt(stateType);
//			this.isFlapping = isFlapping;
//			this.flapDetectionEnabled = flapDetectionEnabled;
//			this.notificationsEnabled = notificationsEnabled;
//			this.isInMonitoring = isInMonitoring;
//			this.lastHardStateChange = lastHardStateChange.isEmpty() ? null : LocalDateTime.parse(lastHardStateChange, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.lastUp = lastUp.isEmpty() ? null : LocalDateTime.parse(lastUp, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.lastCheck = lastCheck.isEmpty() ? null : LocalDateTime.parse(lastCheck, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.nextCheck = nextCheck.isEmpty() ? null : LocalDateTime.parse(nextCheck, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.output = output;
//			this.longOutput = longOutput;
//			this.acknowledgement = acknowledgement;
//		}
//
//		public HostState getCurrentState() {
//			return currentState;
//		}
//
//		public StateType getStateType() {
//			return stateType;
//		}
//
//		public boolean isFlapping() {
//			return isFlapping;
//		}
//
//		public boolean isFlapDetectionEnabled() {
//			return flapDetectionEnabled;
//		}
//
//		public boolean isNotificationsEnabled() {
//			return notificationsEnabled;
//		}
//
//		public boolean isInMonitoring() {
//			return isInMonitoring;
//		}
//
//		public LocalDateTime getLastHardStateChange() {
//			return lastHardStateChange;
//		}
//
//		public LocalDateTime getLastUp() {
//			return lastUp;
//		}
//
//		public LocalDateTime getLastCheck() {
//			return lastCheck;
//		}
//
//		public LocalDateTime getNextCheck() {
//			return nextCheck;
//		}
//
//		public String getOutput() {
//			return output;
//		}
//
//		public String getLongOutput() {
//			return longOutput;
//		}
//
//		public Acknowledgement getAcknowledgement() {
//			return acknowledgement;
//		}
//
//		@Override
//		public String toString() {
//			return "HostStatus [currentState=" + currentState + ", stateType=" + stateType + ", isFlapping=" + isFlapping
//					+ ", flapDetectionEnabled=" + flapDetectionEnabled + ", notificationsEnabled=" + notificationsEnabled
//					+ ", isInMonitoring=" + isInMonitoring + ", lastHardStateChange=" + lastHardStateChange + ", lastUp="
//					+ lastUp + ", lastCheck=" + lastCheck + ", nextCheck=" + nextCheck + ", output=" + output
//					+ ", longOutput=" + longOutput + ", acknowledgement=" + acknowledgement + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HostTemplate
//
//	package de.schoenn.oITCAPI.Entities.Host;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class HostTemplate {
//
//		private OITC oitc;
//		private int id;
//		
//		public HostTemplate(OITC oitc, int id) {
//			this.oitc = oitc;
//			this.id = id;
//		}
//		
//		public int getId() {
//			return id;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Service
//
//	package de.schoenn.oITCAPI.Entities.Service;
//
//	import java.io.IOException;
//	import java.net.URI;
//	import java.net.URISyntaxException;
//	import java.time.OffsetDateTime;
//	import java.util.Arrays;
//	import java.util.UUID;
//
//	import org.json.JSONObject;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Containers.ContactGroup;
//	import de.schoenn.oITCAPI.Entities.Contact;
//	import de.schoenn.oITCAPI.Entities.Host.Host;
//	import de.schoenn.oITCAPI.Enums.Priority;
//	import de.schoenn.oITCAPI.Utils.APIException;
//	import de.schoenn.oITCAPI.Utils.HTTPMethod;
//	import de.schoenn.oITCAPI.Utils.OitcHttpsClient;
//
//	public class Service {
//
//		private OITC oitc;
//		private int id;
//		private UUID uuid;
//		private String name, description;
//		private boolean disabled, retrieved;
//		private String[] tags;
//		private Priority priority;
//		private ServiceStatus status;
//		
//		private ServiceTemplate template;
//		private int checkInterval, retryInterval, maxCheckAttempts, notificationInterval;
//		private boolean notifyOnWarning, notifyOnUnknown, notifyOnCritical, notifyOnRecovery, notifyOnFlapping, notifyOnDowntime;
//		private String notes, serviceURL;
//		private OffsetDateTime created, modified;
//		private Contact[] contacts;
//		private ContactGroup[] contactGroups;
//		private Host host;
//		
//		/**
//		 * Minimal Constructor for a Service. Used when referenced by its ID only.
//		 * @param oitc
//		 * @param id
//		 */
//		public Service(OITC oitc, int id, Host host) {
//			this.oitc = oitc;
//			this.id = id;
//			this.host = host;
//		}
//		
//		/**
//		 * Partial Constructor for a Service. Used by https://{itchost}/services/index.json
//		 * @param oitc
//		 * @param id
//		 * @param uuid
//		 * @param name
//		 * @param description
//		 * @param activeChecksEnabled
//		 * @param disabled
//		 * @param tags
//		 * @param priority
//		 * @param status
//		 * @param host
//		 */
//		public Service(OITC oitc, int id, String uuid, String name, String description, boolean disabled, String tags, int priority, 
//				ServiceStatus status, Host host) {
//			this(oitc, id, host);
//			this.uuid = UUID.fromString(uuid);
//			this.name = name;
//			this.description = description;
//			this.disabled = disabled;
//			this.tags = tags.split(", ");
//			this.priority = Priority.fromInt(priority);
//			this.status = status;
//			this.retrieved = false;
//		}
//		
//		/**
//		 * Full Constructor for a Service. Used by https://{itchost}/services/browser/{ServiceID}.json
//		 * @param oitc
//		 * @param id
//		 * @param uuid
//		 * @param name
//		 * @param description
//		 * @param disabled
//		 * @param tags
//		 * @param priority
//		 * @param status
//		 * @param host
//		 * @param templateID
//		 * @param checkInterval
//		 * @param retryInterval
//		 * @param maxCheckAttempts
//		 * @param notificationInterval
//		 * @param notifyOnWarning
//		 * @param notifyOnUnknown
//		 * @param notifyOnCritical
//		 * @param notifyOnRecovery
//		 * @param notifyOnFlapping
//		 * @param notifyOnDowntime
//		 * @param notes
//		 * @param serviceURL
//		 * @param created
//		 * @param modified
//		 * @param contacts
//		 * @param contactGroups
//		 */
//		public Service(OITC oitc, int id, String uuid, String name, String description, boolean disabled, String tags, int priority, 
//				ServiceStatus status, Host host, int templateID, int checkInterval, int retryInterval, int maxCheckAttempts, int notificationInterval,
//				int notifyOnWarning, int notifyOnUnknown, int notifyOnCritical, int notifyOnRecovery, int notifyOnFlapping, int notifyOnDowntime,
//				String notes, String serviceURL, String created, String modified, Contact[] contacts, ContactGroup[] contactGroups) {
//			this(oitc, id, uuid, name, description, disabled, tags, priority, status, host);
//			this.template = new ServiceTemplate(oitc, templateID);
//			this.checkInterval = checkInterval;
//			this.retryInterval = retryInterval;
//			this.maxCheckAttempts = maxCheckAttempts;
//			this.notificationInterval = notificationInterval;
//			this.notifyOnWarning = notifyOnWarning == 1 ? true : false;
//			this.notifyOnUnknown = notifyOnUnknown == 1 ? true : false;
//			this.notifyOnCritical = notifyOnCritical == 1 ? true : false;
//			this.notifyOnRecovery = notifyOnRecovery == 1 ? true : false;
//			this.notifyOnFlapping = notifyOnFlapping == 1 ? true : false;
//			this.notifyOnDowntime = notifyOnDowntime == 1 ? true : false;
//			this.notes = notes;
//			this.serviceURL = serviceURL;
//			this.created = OffsetDateTime.parse(created);
//			this.modified = OffsetDateTime.parse(modified);
//			this.contacts = contacts;
//			this.contactGroups = contactGroups;
//			this.retrieved = true;
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public UUID getUuid() {
//			return uuid;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public boolean isDisabled() {
//			return disabled;
//		}
//
//		public boolean isRetrieved() {
//			return retrieved;
//		}
//
//		public String[] getTags() {
//			return tags;
//		}
//
//		public Priority getPriority() {
//			return priority;
//		}
//
//		public ServiceStatus getStatus() {
//			return status;
//		}
//
//		public ServiceTemplate getTemplate() {
//			return template;
//		}
//
//		public int getCheckInterval() {
//			return checkInterval;
//		}
//
//		public int getRetryInterval() {
//			return retryInterval;
//		}
//
//		public int getMaxCheckAttempts() {
//			return maxCheckAttempts;
//		}
//
//		public int getNotificationInterval() {
//			return notificationInterval;
//		}
//
//		public boolean isNotifyOnWarning() {
//			return notifyOnWarning;
//		}
//
//		public boolean isNotifyOnUnknown() {
//			return notifyOnUnknown;
//		}
//
//		public boolean isNotifyOnCritical() {
//			return notifyOnCritical;
//		}
//
//		public boolean isNotifyOnRecovery() {
//			return notifyOnRecovery;
//		}
//
//		public boolean isNotifyOnFlapping() {
//			return notifyOnFlapping;
//		}
//
//		public boolean isNotifyOnDowntime() {
//			return notifyOnDowntime;
//		}
//
//		public String getNotes() {
//			return notes;
//		}
//
//		public String getServiceURL() {
//			return serviceURL;
//		}
//
//		public OffsetDateTime getCreated() {
//			return created;
//		}
//
//		public OffsetDateTime getModified() {
//			return modified;
//		}
//
//		public Contact[] getContacts() {
//			return contacts;
//		}
//
//		public ContactGroup[] getContactGroups() {
//			return contactGroups;
//		}
//		
//		public Host getHost() {
//			return host;
//		}
//		
//		public void removeAcknowledgement() throws APIException {
//			try {
//				JSONObject payload = new JSONObject();
//				payload.put("hostId", getHost().getId());
//				payload.put("serviceId", getId());
//				OitcHttpsClient https = new OitcHttpsClient(new URI(oitc.getEndpoint() + "/acknowledgements/delete/.json"), oitc.getApiKey(), HTTPMethod.POST, payload);
//				JSONObject response = https.getJSON();
//				System.out.println(response.toString(2));
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Override
//		public String toString() {
//			return "Service [oitc=" + oitc + ", id=" + id + ", uuid=" + uuid + ", name=" + name + ", description="
//					+ description + ", disabled=" + disabled + ", retrieved=" + retrieved + ", tags="
//					+ Arrays.toString(tags) + ", priority=" + priority + ", status=" + status + ", template=" + template
//					+ ", checkInterval=" + checkInterval + ", retryInterval=" + retryInterval + ", maxCheckAttempts="
//					+ maxCheckAttempts + ", notificationInterval=" + notificationInterval + ", notifyOnWarning="
//					+ notifyOnWarning + ", notifyOnUnknown=" + notifyOnUnknown + ", notifyOnCritical=" + notifyOnCritical
//					+ ", notifyOnRecovery=" + notifyOnRecovery + ", notifyOnFlapping=" + notifyOnFlapping
//					+ ", notifyOnDowntime=" + notifyOnDowntime + ", notes=" + notes + ", serviceURL=" + serviceURL
//					+ ", created=" + created + ", modified=" + modified + ", contacts=" + Arrays.toString(contacts)
//					+ ", contactGroups=" + Arrays.toString(contactGroups) + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ServiceState
//
//	package de.schoenn.oITCAPI.Entities.Service;
//
//	public enum ServiceState {
//
//		UNDEFINED,
//		OK,
//		WARNING,
//		CRITICAL,
//		UNKNOWN;
//		
//		public static ServiceState fromInt(int serviceState) {
//			if (serviceState == 0) return OK;
//			if (serviceState == 1) return WARNING;
//			if (serviceState == 2) return CRITICAL;
//			if (serviceState == 3) return UNKNOWN;
//			return UNDEFINED;
//		}
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ServiceStatus
//
//	package de.schoenn.oITCAPI.Entities.Service;
//
//	import java.time.LocalDateTime;
//	import java.time.format.DateTimeFormatter;
//
//	import de.schoenn.oITCAPI.OITC;
//	import de.schoenn.oITCAPI.Acknowledgements.ServiceAcknowledgement;
//	import de.schoenn.oITCAPI.Enums.StateType;
//
//	public class ServiceStatus {
//
//		private OITC oitc;
//		private ServiceState state;
//		private StateType type;
//		private boolean isFlapping, notificationsEnabled, isInMonitoring;
//		private ServiceAcknowledgement acknowledgement;
//		private LocalDateTime lastCheck, nextCheck, lastHardStateChange, lastStateChange, lastTimeOK;
//		private String output, longOutput;
//		
//		/**
//		 * Full Constructor for a ServiceStatus. Used by https://{itchost}/services/browser/{serviceID}.json and https://{itchost}/services/index.json
//		 * @param oitc
//		 * @param state
//		 * @param isHardState
//		 * @param isFlapping
//		 * @param notificationsEnabled
//		 * @param isInMonitoring
//		 * @param acknowledgementType
//		 * @param serviceID
//		 * @param lastCheck
//		 * @param nextCheck
//		 * @param lastHardStateChange
//		 * @param lastStateChange
//		 * @param lastTimeOK
//		 * @param output
//		 * @param longOutput
//		 */
//		public ServiceStatus(OITC oitc, int state, boolean isHardState, boolean isFlapping, boolean notificationsEnabled, boolean isInMonitoring, 
//				int acknowledgementType, int serviceID, 
//				String lastCheck, String nextCheck, String lastHardStateChange, String lastStateChange, String lastTimeOK, String output, String longOutput) {
//			this.oitc = oitc;
//			this.state = ServiceState.fromInt(state);
//			this.type = isHardState ? StateType.HARD : StateType.SOFT;
//			this.isFlapping = isFlapping;
//			this.notificationsEnabled = notificationsEnabled;
//			this.isInMonitoring = isInMonitoring;
//			this.acknowledgement = new ServiceAcknowledgement(oitc, acknowledgementType, serviceID);
//			this.lastCheck = lastCheck.isEmpty() ? null : LocalDateTime.parse(lastCheck, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.nextCheck = nextCheck.isEmpty() ? null : LocalDateTime.parse(nextCheck, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.lastHardStateChange = lastHardStateChange.isEmpty() ? null : LocalDateTime.parse(lastHardStateChange, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.lastStateChange = lastStateChange.isEmpty() ? null : LocalDateTime.parse(lastStateChange, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.lastTimeOK = lastTimeOK.isEmpty() ? null : LocalDateTime.parse(lastTimeOK, DateTimeFormatter.ofPattern("HH:mm:ss - dd.MM.yyyy"));
//			this.output = output;
//			this.longOutput = longOutput;
//		}
//
//		public OITC getOitc() {
//			return oitc;
//		}
//
//		public ServiceState getState() {
//			return state;
//		}
//
//		public StateType getType() {
//			return type;
//		}
//
//		public boolean isFlapping() {
//			return isFlapping;
//		}
//
//		public boolean isNotificationsEnabled() {
//			return notificationsEnabled;
//		}
//
//		public boolean isInMonitoring() {
//			return isInMonitoring;
//		}
//
//		public ServiceAcknowledgement getAcknowledgement() {
//			return acknowledgement;
//		}
//
//		public LocalDateTime getLastCheck() {
//			return lastCheck;
//		}
//
//		public LocalDateTime getNextCheck() {
//			return nextCheck;
//		}
//
//		public LocalDateTime getLastHardStateChange() {
//			return lastHardStateChange;
//		}
//
//		public LocalDateTime getLastStateChange() {
//			return lastStateChange;
//		}
//
//		public LocalDateTime getLastTimeOK() {
//			return lastTimeOK;
//		}
//
//		public String getOutput() {
//			return output;
//		}
//
//		public String getLongOutput() {
//			return longOutput;
//		}
//
//		@Override
//		public String toString() {
//			return "ServiceStatus [oitc=" + oitc + ", state=" + state + ", type=" + type + ", isFlapping=" + isFlapping
//					+ ", notificationsEnabled=" + notificationsEnabled + ", isInMonitoring=" + isInMonitoring
//					+ ", acknowledgement=" + acknowledgement + ", lastCheck=" + lastCheck + ", nextCheck=" + nextCheck
//					+ ", lastHardStateChange=" + lastHardStateChange + ", lastStateChange=" + lastStateChange
//					+ ", lastTimeOK=" + lastTimeOK + ", output=" + output + ", longOutput=" + longOutput + "]";
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: ServiceTemplate
//
//	package de.schoenn.oITCAPI.Entities.Service;
//
//	import de.schoenn.oITCAPI.OITC;
//
//	public class ServiceTemplate {
//
//		private OITC oitc;
//		private int id;
//		
//		public ServiceTemplate(OITC oitc, int id) {
//			this.oitc = oitc;
//			this.id = id;
//		}
//
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: Priority
//
//	package de.schoenn.oITCAPI.Enums;
//
//	public enum Priority {
//		
//		UNDEFINED(0),
//		VERY_LOW(1),
//		LOW(2),
//		NORMAL(3),
//		HIGH(4),
//		VERY_HIGH(5);
//		
//		private int prio;
//		
//		private Priority(int prio) {
//			this.prio = prio;
//		}
//		
//		public static Priority fromInt(int priority) {
//			if (priority == 1) return VERY_LOW;
//			if (priority == 2) return LOW;
//			if (priority == 3) return NORMAL;
//			if (priority == 4) return HIGH;
//			if (priority == 5) return VERY_HIGH;
//			return UNDEFINED;
//		}
//		
//		public int getID() {
//			return this.prio;
//		}
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: StateType
//
//	package de.schoenn.oITCAPI.Enums;
//
//	public enum StateType {
//
//		UNDEFINED,
//		SOFT,
//		HARD;
//		
//		public static StateType fromInt(int stateType) {
//			if (stateType == 0) return SOFT;
//			if (stateType == 1) return HARD;
//			return UNDEFINED;
//		}
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: APIException
//
//	package de.schoenn.oITCAPI.Utils;
//
//	import java.net.URL;
//
//	public class APIException extends Exception {
//
//		private static final long serialVersionUID = 353009900541790159L;
//		private URL url;
//		private int code;
//		
//		public APIException(String message, URL url, int code) {
//			super(message);
//			this.url = url;
//			this.code = code;
//		}
//		
//		public URL getURL() {
//			return url;
//		}
//		
//		public int getCode() {
//			return code;
//		}
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: HTTPMethod
//
//	package de.schoenn.oITCAPI.Utils;
//
//	public enum HTTPMethod {
//		
//		GET,
//		POST,
//		PUT,
//		DELETE;
//		
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: IOUtils
//
//	package de.schoenn.oITCAPI.Utils;
//
//	import java.io.ByteArrayOutputStream;
//	import java.io.IOException;
//	import java.io.InputStream;
//	import java.lang.reflect.Array;
//
//	public class IOUtils{
//
//		/**
//		 * Gets the contents of an {@link InputStream} as a <code>byte[]</code>.
//		 * <p>
//		 * This method buffers the input internally, so there is no need to use a
//		 * {@link BufferedInputStream}.
//		 * 
//		 * @param input the {@link InputStream} to read from
//		 * @return the requested byte array
//		 * @throws IOException if an I/O error occurs
//		 */
//		public static byte[] toByteArray(InputStream input) throws IOException{
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			byte[] buffer = new byte[4096];
//			int n = 0;
//			while(-1 != (n = input.read(buffer))){
//				output.write(buffer, 0, n);
//			}
//			return output.toByteArray();
//		}
//		
//		public static <T> T concatArrays(T array1, T array2) {
//			if (!array1.getClass().isArray() || !array2.getClass().isArray()) {
//				throw new IllegalArgumentException("Only arrays are accepted.");
//			}
//			
//			Class<?> compType = array1.getClass().getComponentType();
//			if (!compType.equals(array2.getClass().getComponentType())) {
//				throw new IllegalArgumentException("Arrays have to be of same type.");
//			}
//			
//			int len1 = Array.getLength(array1);
//			int len2 = Array.getLength(array2);
//			
//			@SuppressWarnings("unchecked")
//			T result = (T) Array.newInstance(compType, len1 + len2);
//			
//			System.arraycopy(array1, 0, result, 0, len1);
//			System.arraycopy(array2, 0, result, len1, len2);
//			
//			return result;
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//
//	Class: OitcHttpsClient
//
//	package de.schoenn.oITCAPI.Utils;
//
//	import java.io.IOException;
//	import java.io.OutputStreamWriter;
//	import java.net.MalformedURLException;
//	import java.net.URI;
//	import java.net.URISyntaxException;
//	import java.net.URL;
//	import java.net.URLEncoder;
//	import java.util.HashMap;
//	import java.util.Map;
//
//	import javax.net.ssl.HttpsURLConnection;
//
//	import org.json.JSONArray;
//	import org.json.JSONObject;
//
//	public class OitcHttpsClient{
//
//		private final URL					url;
//		private final String				token;
//		private final HTTPMethod			method;
//		private final Map<String, String>	args;
//		private final String				payload;
//		private int							responseCode	= -1;
//		private byte[]						content;
//
//		public OitcHttpsClient(URI uri, String token) throws IOException {
//			this(uri, token, HTTPMethod.GET, new HashMap<String, String>());
//		}
//
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method) throws IOException {
//			this(uri, token, method, new HashMap<String, String>(), "");
//		}
//
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, Map<String, String> args) throws IOException {
//			this(uri, token, method, args, "");
//		}
//		
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, JSONObject payload) throws IOException {
//			this(uri, token, method, new HashMap<String, String>(), payload.toString());
//		}
//		
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, JSONArray payload) throws IOException {
//			this(uri, token, method, new HashMap<String, String>(), payload.toString());
//		}
//		
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, Map<String, String> args, JSONObject payload) throws IOException {
//			this(uri, token, method, args, payload.toString());
//		}
//		
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, Map<String, String> args, JSONArray payload) throws IOException {
//			this(uri, token, method, args, payload.toString());
//		}
//		
//		public OitcHttpsClient(URI uri, String token, HTTPMethod method, Map<String, String> args, String payload) throws IOException {
//			this.url = uri.toURL();
//			this.token = token;
//			this.method = method;
//			this.args = args;
//			this.payload = payload;
//		}
//
//		private void getContent() throws IOException, APIException {
//			URL url = this.url;
//			String rawArgs = "";
//			args.put("angular", "true");
//			for(String key : args.keySet())
//				rawArgs += (key + "=" + URLEncoder.encode(args.get(key), "UTF-8") + "&");
//			rawArgs = rawArgs.substring(0, rawArgs.length() - 1);
//			if(args != null && !args.isEmpty()) {
//				try {
//					url = new URI(this.url.toString() + "?" + rawArgs).toURL();
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (URISyntaxException e) {
//					e.printStackTrace();
//				}
//			}
//			int connectErrors = 0;
//			while(connectErrors < 3){
//				try{
//					System.out.println(method + " " + url.toString());
//					HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//					conn.setConnectTimeout(5000);
//					conn.setDoInput(true);
//					conn.setDoOutput(true);
//					conn.setUseCaches(false);
//					conn.setRequestMethod(method.name());
//					conn.setRequestProperty("User-Agent", "Mozilla/5.0");
//					conn.setRequestProperty("Content-Type", "application/json");
//					conn.setRequestProperty("Accept", "application/json");
//					conn.setRequestProperty("Authorization", "X-OITC-API " + token);
//					if(method == HTTPMethod.POST) {
//						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//						writer.write(payload);
//						writer.close();
//					} else conn.connect();
//					responseCode = conn.getResponseCode();
//					if(responseCode >= HttpsURLConnection.HTTP_BAD_REQUEST) {
//						content = IOUtils.toByteArray(conn.getErrorStream());
//						JSONObject errorjson = new JSONObject(new String(content));
//						throw new APIException(errorjson.optString("message", "Unknown Error"), url, responseCode);
//					} else {
//						content = IOUtils.toByteArray(conn.getInputStream());
//					}
//					conn.disconnect();
//					return;
//				} catch(IOException e) {
//					connectErrors++;
//					if(connectErrors >= 3){
//						throw e;
//					}
//				}
//			}
//		}
//		
//		public int getResponseCode() {
//			return this.responseCode;
//		}
//
//		public JSONObject getJSON() throws IOException, APIException {
//			if(responseCode == -1) getContent();
//			return new JSONObject(new String(content));
//		}
//	}
//
//
//	----------------------------------------------------------------------------------
//}