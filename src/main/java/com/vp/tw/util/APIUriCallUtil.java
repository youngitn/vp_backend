package com.vp.tw.util;

import java.nio.charset.Charset;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class APIUriCallUtil {

	

	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	// 宣告rep
	private CloseableHttpResponse response = null;
	
	/**
	 * 
	* @Title: sendReq 
	* @Description: 以POST方式送出req
	* @param @param uri 資源位置
	* @param @param contentType 
	* @param @param charset
	* @param @param reqJSONString    設定檔案 
	* @param @param reqJSONString req內容
	* @return void    返回型別 
	* @throws
	 */
	public void sendReq(String uri, String contentType, String charset, String reqJSONString) {
		log.info("send req to " + uri);
		HttpPost httppost = new HttpPost(uri);
		httppost.addHeader("Content-Type", contentType);

		StringEntity se = new StringEntity(reqJSONString, Charset.forName(charset));

		httppost.setEntity(se);

		String urlist = null;

		try {
			// send req
			response = httpclient.execute(httppost);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
