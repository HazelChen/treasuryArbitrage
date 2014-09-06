package bizLogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
/**
 * 
 * @author luck-mac
 * 使用方法
 * 构造函数 参数1:方法名  参数2:所带参数
 * 调用JSONObjectByGet获取JSON进行处理
 *
 */
public class NetHelper {
	String urlString ;
	public NetHelper (String method, HashMap<String, String> params){
		this.urlString = "http://njuhq.sinaapp.com/"+method;
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
	
	/*
	 * 设置参数
	 */
	public void setPara(HashMap<String, String> param){
		urlString = urlString+"?";
		for (String key: param.keySet()) {
			urlString += key+"="+param.get(key)+"&";
		}
		System.out.println(urlString);
	}

	
	// 得到JSONObject(Get方式)
	public JSONObject getJSONObjectByGet() {
		JSONObject resultJsonObject = null;
		if ("".equals(urlString) || urlString == null) {
			return null;
		}		
        CloseableHttpClient httpclient = HttpClients.createDefault();  
		StringBuilder urlStringBuilder = new StringBuilder(urlString);
		StringBuilder entityStringBuilder = new StringBuilder();
		// 利用URL生成一个HttpGet请求
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
			// 得到httpResponse的实体数据		
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				try {
					bufferedReader = new BufferedReader(new InputStreamReader(
							httpEntity.getContent(), "UTF-8"), 8 * 1024);
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						entityStringBuilder.append(line + "/n");
						System.out.println(line);
					}
					// 利用从HttpEntity中得到的String生成JsonObject
					resultJsonObject = new JSONObject(
							entityStringBuilder.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultJsonObject;
	}
	
	

}
