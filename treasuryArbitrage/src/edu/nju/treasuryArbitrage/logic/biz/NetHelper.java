package edu.nju.treasuryArbitrage.logic.biz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author luck-mac
 * 使用方法
 * 构�?函数 参数1:方法�? 参数2:�?��参数
 * 调用JSONObjectByGet获取JSON进行处理
 *
 */
public class NetHelper {
	String urlString ;
	public NetHelper (String method, HashMap<String, String> params){
		this.urlString = "http://njuhq.sinaapp.com/"+method;
//		this.urlString = "192.168.53.56/"+method;
		this.setPara(params);
	}
	
//	public static void main(String[] args) {
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("username", "a");
//		params.put("password", "b");
//		NetHelper helper = new NetHelper("register",params);
//		JSONObject ret = helper.getJSONObjectByGet();
//		UserVO vo = new UserVO(ret);
//		System.out.println(vo);
//	}
	
//	public static void main(String[] args){
//		String arr = "[{\"name\":\"jack\"},{\"name\":\"lucy\"}]";
//		JSONArray temp = new JSONArray(arr);
//		for(int i=0;i<temp.length();i++){
//			JSONObject ob = temp.getJSONObject(i);
//			String aa = ob.getString("name");
//			System.out.println(aa);
//		}
//	}
	
	/*
	 * 设置参数
	 */
	public void setPara(HashMap<String, String> params){
		urlString = urlString+"?";
		for (String key: params.keySet()) {
			urlString += key+"="+params.get(key)+"&";
		}
		System.out.println(urlString);
	}

	public String getStringByGet(){
		String resultString = null;
		if ("".equals(urlString) || urlString == null) {
			return null;
		}		
        CloseableHttpClient httpclient = HttpClients.createDefault();  
		StringBuilder urlStringBuilder = new StringBuilder(urlString);
		StringBuilder entityStringBuilder = new StringBuilder();
		// 利用URL生成�?��HttpGet请求
		HttpGet httpGet = new HttpGet(urlStringBuilder.toString());
		BufferedReader bufferedReader = null;
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 得到httpResponse的状态响应码
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			// 得到httpResponse的实体数�?	
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				try {
					bufferedReader = new BufferedReader(new InputStreamReader(
							httpEntity.getContent(), "UTF-8"), 8 * 1024);
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						entityStringBuilder.append(line + "/n");
//						System.out.println(line);
					}
					// 利用从HttpEntity中得到的String生成JsonObject
					resultString = entityStringBuilder.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultString;
	}
	
//	// 得到JSONObject(Get方式)
//	public JSONObject getJSONObjectByGet() {
//		JSONObject resultJsonObject = null;
//		if ("".equals(urlString) || urlString == null) {
//			return null;
//		}		
//        CloseableHttpClient httpclient = HttpClients.createDefault();  
//		StringBuilder urlStringBuilder = new StringBuilder(urlString);
//		StringBuilder entityStringBuilder = new StringBuilder();
//		// 利用URL生成�?��HttpGet请求
//		HttpGet httpGet = new HttpGet(urlStringBuilder.toString());
//		BufferedReader bufferedReader = null;
//		HttpResponse httpResponse = null;
//		try {
//			httpResponse = httpclient.execute(httpGet);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 得到httpResponse的状态响应码
//		int statusCode = httpResponse.getStatusLine().getStatusCode();
//		if (statusCode == HttpStatus.SC_OK) {
//			// 得到httpResponse的实体数�?	
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if (httpEntity != null) {
//				try {
//					bufferedReader = new BufferedReader(new InputStreamReader(
//							httpEntity.getContent(), "UTF-8"), 8 * 1024);
//					String line = null;
//					while ((line = bufferedReader.readLine()) != null) {
//						entityStringBuilder.append(line + "/n");
//						System.out.println(line);
//					}
//					// 利用从HttpEntity中得到的String生成JsonObject
//					resultJsonObject = new JSONObject(
//							entityStringBuilder.toString());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return resultJsonObject;
//	}
	
	// 得到JSONObject(Get方式)
	public JSONObject getJSONObjectByGet() {
		JSONObject result = new JSONObject(getStringByGet());
		return result;
	}
	
	
	// 得到JSONObject(Get方式)
	public JSONArray getJSONArrayByGet() {
		JSONArray result = new JSONArray(getStringByGet());
		return result;
	}

}
