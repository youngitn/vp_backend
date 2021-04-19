package com.vp.tw.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.tw.responsedto.T100Rep;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;


/**
 * 
* @ClassName: T100WebServiceTool 
* @Description: 進行T100 web service的工具 
* @author ytc
* @date 2021年4月16日 上午10:26:41 
*
 */
@Data
@Log4j2
public class T100WebServiceTool {

	// T100 ws URL
	private String url = "http://192.168.0.5/wstopprd/ws/r/awsp920";
	private String reqJSONString;
	//送去給T100 WS的JSON檔名
	private String reqName;
	private String patch = "D:\\T100reqFile\\";
	private HttpPost httppost = new HttpPost(url);
	// 宣告rep
	private CloseableHttpResponse response = null;
	private String temp = null;

	private ObjectMapper objectMapper = new ObjectMapper();
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	private T100Rep t100Rep;

	private JSONObject jo;

	public T100WebServiceTool(String reqName) throws IOException {
		// req名稱
		this.reqName = reqName;
		// 從file抓json req,並賦值給reqJSONString
		this.buildReqJSON();
		// 設定Header 指定為req為JSON

	}

	public String sendReqToT100() {
		
		log.info("send req to T100");
		
		this.httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		
		StringEntity se = new StringEntity(reqJSONString, Charset.forName("UTF-8"));
		
		this.httppost.setEntity(se);
		
		String urlist = null;
		
		try {
			response = httpclient.execute(httppost);

			this.t100Rep = new T100Rep(response, reqName);
			//urlist = rep.getMaster().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t100Rep.getJobj().toString();
	}

//	SFRes setDataToSalesforceObject() {
//		return null;
//	}

	public void setListValueByKey(String key, List list) {
		JSONFileReader.replaceAll(jo, key, list);
		System.out.println("------------setListValueByKey req--------------");
		System.out.println(jo);
		this.reqJSONString = jo.toString();
	}

	public void setStringValueByKey(String key, String str) {
		JSONFileReader.replaceAll(jo, key, str);
		this.reqJSONString = jo.toString();
	}

	private void buildReqJSON() throws IOException {

		JSONFileReader jsonFileReader = new JSONFileReader(patch + "web." + reqName + ".json");
		this.jo = jsonFileReader.getJSONObject();
//		List a = new ArrayList();
//		a.add("A");
//		a.add("A");
//		a.add("A");
//		a.add("A");
//		
//		jo.put("test", a);
//		JSONFileReader.replaceAll(jo, "userdate", a);
		this.reqJSONString = jo.toString();
		System.out.println("------------init req--------------");
		System.out.println(jo);

	}

}
