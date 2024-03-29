package dao;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import session.UserGlobal;
import util.HttpPostAux;
import business.Service;
import conexion.RemoteConexion;

public class ServiceDAO {

	private Service service;
	private ArrayList<Service> services;
	private HttpPostAux post;
	private int respond;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ServiceDAO() {
		service = new Service();
	}

	public int insertService() {
		respond = 0;
		
		

		String name = service.getName();
		String address = service.getAddress();
		String description = service.getDescription();
		String telephone = service.getTelephone();
		String category = service.getCategory();
		String username = UserGlobal.usersession.getUser();  //assigning proprietary of service
		String city = service.getCity();
		String email = service.getEmail();
		String webpage = service.getWebpage();
		/*String admin_state = service.getAdmin_state();
		Date date_start = service.getDate_start();
		Date date_end = service.getDate_end();
		int available = service.getAvailability();
		int numrating = service.getNum_rating();
		int ratingacum = service.getRating_acum();*/

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		poststring.add(new BasicNameValuePair("name", name));
		poststring.add(new BasicNameValuePair("description", description));
		poststring.add(new BasicNameValuePair("category", category));
		poststring.add(new BasicNameValuePair("city", city));
		poststring.add(new BasicNameValuePair("telephone", telephone));
		poststring.add(new BasicNameValuePair("address", address));
		poststring.add(new BasicNameValuePair("webpage", webpage));
		poststring.add(new BasicNameValuePair("email", email));
		poststring.add(new BasicNameValuePair("user", username));
		
		poststring.add(new BasicNameValuePair("type", "INSERT"));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "addservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;
			try {
				json_data = jdata.getJSONObject(0);
				respond = json_data.getInt("respond");

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return respond;

		} else {
			return 0;
		}
	}

	
	public int updateService(Service serv) {
		respond = 0;


		

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();


		poststring.add(new BasicNameValuePair("cod", serv.getCod()+""));
		poststring.add(new BasicNameValuePair("numrating", serv.getNum_rating()+""));
		poststring.add(new BasicNameValuePair("rating", serv.getRating_acum()+""));
		poststring.add(new BasicNameValuePair("calpuntualidad", serv.getCalpuntualidad()+""));
		poststring.add(new BasicNameValuePair("calcosto", serv.getCalcosto() + ""));
		poststring.add(new BasicNameValuePair("calatencion", serv.getCalatencion()+""));
		poststring.add(new BasicNameValuePair("calculminacion", serv.getCalculminacion()+""));
		poststring.add(new BasicNameValuePair("calcalidad", serv.getCalcalidad()+""));
		
		poststring.add(new BasicNameValuePair("type", "UPDATE"));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "addservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;
			try {
				json_data = jdata.getJSONObject(0);
				respond = json_data.getInt("respond");

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return respond;

		} else {
			return 0;
		}
	}
	
	/*public void deleteUser(String name, String user, String password) {

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		poststring.add(new BasicNameValuePair("name", name));
		poststring.add(new BasicNameValuePair("user", user));
		poststring.add(new BasicNameValuePair("password", password));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "deleteservice.php"); // no
																			// creado
																			// aun

	}*/

	public ArrayList<Service> queryServiceAll() {
		respond = 0;

		services = new ArrayList<Service>();

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		poststring.add(new BasicNameValuePair("attrib", null));
		poststring.add(new BasicNameValuePair("querty", "ALL"));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "queryservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;

			try {
				for (int i = 0; i < jdata.length(); i++) {
					json_data = jdata.getJSONObject(i);
					Service tmp = new Service();

					tmp.setName(json_data.getString("name"));
					tmp.setCod(json_data.getInt("cod"));
					tmp.setAddress(json_data.getString("address"));
					tmp.setDescription(json_data.getString("description"));
					tmp.setTelephone(json_data.getString("telephone"));
					tmp.setCategory(json_data.getString("category"));
					tmp.setCity(json_data.getString("city"));
					// tmp.setDate_start(json_data.getDate("date_start"));
					// tmp.setDate_end(date_end)
					tmp.setWebpage(json_data.getString("webpage"));
					tmp.setAvailability(json_data.getInt("availability"));
					tmp.setNum_rating(json_data.getInt("num_rating"));
					tmp.setRating_acum(json_data.getInt("rating_acum"));
					tmp.setAdmin_state(json_data.getString("admin_state"));
					tmp.setEmail(json_data.getString("email"));
					tmp.setUsername(json_data.getString("username"));
					
					tmp.setCalatencion(json_data.getInt("calatencion"));
					tmp.setCalcalidad(json_data.getInt("calcalidad"));
					tmp.setCalcosto(json_data.getInt("calcosto"));
					tmp.setCalculminacion(json_data.getInt("calculminacion"));
					tmp.setCalpuntualidad(json_data.getInt("calpuntualidad"));
					


					services.add(tmp);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return services;

		} else {
			return null;
		}

	}

	public ArrayList<Service> queryServiceThis() {
		respond = 0;

		services = new ArrayList<Service>();

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		poststring.add(new BasicNameValuePair("attrib", UserGlobal.usersession
				.getUser()));
		poststring.add(new BasicNameValuePair("query", "BYUSER"));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "queryservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;

			try {
				for (int i = 0; i < jdata.length(); i++) {
					json_data = jdata.getJSONObject(i);
					Service tmp = new Service();

					tmp.setName(json_data.getString("name"));
					tmp.setCod(json_data.getInt("cod"));
					tmp.setAddress(json_data.getString("address"));
					tmp.setDescription(json_data.getString("description"));
					tmp.setTelephone(json_data.getString("telephone"));
					tmp.setCategory(json_data.getString("category"));
					tmp.setCity(json_data.getString("city"));
					// tmp.setDate_start(json_data.getDate("date_start"));
					// tmp.setDate_end(date_end)
					tmp.setWebpage(json_data.getString("webpage"));
					tmp.setAvailability(json_data.getInt("availability"));
					tmp.setNum_rating(json_data.getInt("num_rating"));
					tmp.setRating_acum(json_data.getInt("rating_acum"));
					tmp.setAdmin_state(json_data.getString("admin_state"));
					tmp.setEmail(json_data.getString("email"));
					tmp.setUsername(json_data.getString("username"));
					
					tmp.setCalatencion(json_data.getInt("calatencion"));
					tmp.setCalcalidad(json_data.getInt("calcalidad"));
					tmp.setCalcosto(json_data.getInt("calcosto"));
					tmp.setCalculminacion(json_data.getInt("calculminacion"));
					tmp.setCalpuntualidad(json_data.getInt("calpuntualidad"));

					services.add(tmp);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return services;

		} else {
			return null;
		}

	}

	public ArrayList<Service> queryServiceByUser(String username) {
		respond = 0;

		services = new ArrayList<Service>();

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		poststring.add(new BasicNameValuePair("attrib", username));
		poststring.add(new BasicNameValuePair("query", "BYUSER"));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "queryservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;

			try {
				for (int i = 0; i < jdata.length(); i++) {
					json_data = jdata.getJSONObject(i);
					Service tmp = new Service();

					tmp.setName(json_data.getString("name"));
					tmp.setCod(json_data.getInt("cod"));
					tmp.setAddress(json_data.getString("address"));
					tmp.setDescription(json_data.getString("description"));
					tmp.setTelephone(json_data.getString("telephone"));
					tmp.setCategory(json_data.getString("category"));
					tmp.setCity(json_data.getString("city"));
					// tmp.setDate_start(json_data.getDate("date_start"));
					// tmp.setDate_end(date_end)
					tmp.setWebpage(json_data.getString("webpage"));
					tmp.setAvailability(json_data.getInt("availability"));
					tmp.setNum_rating(json_data.getInt("num_rating"));
					tmp.setRating_acum(json_data.getInt("rating_acum"));
					tmp.setAdmin_state(json_data.getString("admin_state"));
					tmp.setEmail(json_data.getString("email"));
					tmp.setUsername(json_data.getString("username"));
					
					tmp.setCalatencion(json_data.getInt("calatencion"));
					tmp.setCalcalidad(json_data.getInt("calcalidad"));
					tmp.setCalcosto(json_data.getInt("calcosto"));
					tmp.setCalculminacion(json_data.getInt("calculminacion"));
					tmp.setCalpuntualidad(json_data.getInt("calpuntualidad"));

					services.add(tmp);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return services;

		} else {
			return null;
		}

	}

	public ArrayList<Service> queryServiceAllByQualify(String text, String cit,
			String categ, boolean available) {
		respond = 0;

		services = new ArrayList<Service>();

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		if (available) {
			poststring.add(new BasicNameValuePair("availability", "1"));
		} else {
			poststring.add(new BasicNameValuePair("availability", "0"));
		}

		if (categ.equals("ALL")) {
			poststring.add(new BasicNameValuePair("query", "SALLBYQUALIFY"));
		} else {
			poststring.add(new BasicNameValuePair("query",
					"SALLBYQUALIFYCATEGORY"));
		}

		poststring.add(new BasicNameValuePair("attrib", text));
		poststring.add(new BasicNameValuePair("city", cit));
		poststring.add(new BasicNameValuePair("category", categ));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "queryservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;

			try {
				for (int i = 0; i < jdata.length(); i++) {
					json_data = jdata.getJSONObject(i);
					Service tmp = new Service();

					tmp.setName(json_data.getString("name"));
					tmp.setCod(json_data.getInt("cod"));
					tmp.setAddress(json_data.getString("address"));
					tmp.setDescription(json_data.getString("description"));
					tmp.setTelephone(json_data.getString("telephone"));
					tmp.setCategory(json_data.getString("category"));
					tmp.setCity(json_data.getString("city"));
					// tmp.setDate_start(json_data.getDate("date_start"));
					// tmp.setDate_end(date_end)
					tmp.setWebpage(json_data.getString("webpage"));
					tmp.setAvailability(json_data.getInt("availability"));
					tmp.setNum_rating(json_data.getInt("num_rating"));
					tmp.setRating_acum(json_data.getInt("rating_acum"));
					tmp.setAdmin_state(json_data.getString("admin_state"));
					tmp.setEmail(json_data.getString("email"));
					tmp.setUsername(json_data.getString("username"));
					
					tmp.setCalatencion(json_data.getInt("calatencion"));
					tmp.setCalcalidad(json_data.getInt("calcalidad"));
					tmp.setCalcosto(json_data.getInt("calcosto"));
					tmp.setCalculminacion(json_data.getInt("calculminacion"));
					tmp.setCalpuntualidad(json_data.getInt("calpuntualidad"));

					services.add(tmp);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return services;

		} else {
			return null;
		}

	}

	public ArrayList<Service> queryServiceAllByGets(String text, String cit,
			String categ, boolean available) {
		respond = 0;

		services = new ArrayList<Service>();

		ArrayList<NameValuePair> poststring = new ArrayList<NameValuePair>();
		post = new HttpPostAux();

		if (available) {
			poststring.add(new BasicNameValuePair("availability", "1"));
		} else {
			poststring.add(new BasicNameValuePair("availability", "0"));
		}

		if (categ.equals("ALL")) {
			poststring.add(new BasicNameValuePair("query", "SALLBYGETS"));
		} else {
			poststring
					.add(new BasicNameValuePair("query", "SALLBYGETSCATEGORY"));
		}

		poststring.add(new BasicNameValuePair("attrib", text));
		poststring.add(new BasicNameValuePair("city", cit));
		poststring.add(new BasicNameValuePair("category", categ));

		JSONArray jdata = post.getServerData(poststring,
				RemoteConexion.CONNECT_REMOTE_URL + "queryservice.php");

		if (jdata != null && jdata.length() > 0) {
			JSONObject json_data;

			try {
				for (int i = 0; i < jdata.length(); i++) {
					json_data = jdata.getJSONObject(i);
					Service tmp = new Service();

					tmp.setName(json_data.getString("name"));
					tmp.setCod(json_data.getInt("cod"));
					tmp.setAddress(json_data.getString("address"));
					tmp.setDescription(json_data.getString("description"));
					tmp.setTelephone(json_data.getString("telephone"));
					tmp.setCategory(json_data.getString("category"));
					tmp.setCity(json_data.getString("city"));
					// tmp.setDate_start(json_data.getDate("date_start"));
					// tmp.setDate_end(date_end)
					tmp.setWebpage(json_data.getString("webpage"));
					tmp.setAvailability(json_data.getInt("availability"));
					tmp.setNum_rating(json_data.getInt("num_rating"));
					tmp.setRating_acum(json_data.getInt("rating_acum"));
					tmp.setAdmin_state(json_data.getString("admin_state"));
					tmp.setEmail(json_data.getString("email"));
					tmp.setUsername(json_data.getString("username"));
					
					tmp.setCalatencion(json_data.getInt("calatencion"));
					tmp.setCalcalidad(json_data.getInt("calcalidad"));
					tmp.setCalcosto(json_data.getInt("calcosto"));
					tmp.setCalculminacion(json_data.getInt("calculminacion"));
					tmp.setCalpuntualidad(json_data.getInt("calpuntualidad"));

					services.add(tmp);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return services;

		} else {
			return null;
		}

	}

}
