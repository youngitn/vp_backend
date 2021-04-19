package com.vp.tw.responsedto;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;

/**
 * 
 * @ClassName: T100Rep
 * @Description: T100回傳資料物件
 * @author ytc
 * @date 2020年11月17日 下午4:36:59
 *
 */
@Data
public class T100Rep {

	private String sql_code;
	private String code;
	private String description;
	private JSONObject parameter;
	private JSONArray master;
	private JSONObject jobj;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param response  CloseableHttpResponse物件 由T100回傳
	 * @param sfObjName 要同步資料的salesforce物件名稱(小寫,如:account)
	 * @throws ParseException
	 * @throws JSONException
	 * @throws IOException
	 */
	public T100Rep(CloseableHttpResponse response, String sfObjName) throws ParseException, JSONException, IOException {
		HttpEntity entity1 = response.getEntity();
		this.jobj = new JSONObject(EntityUtils.toString(entity1));
		System.out.println("---------T100Rep-----------");
		System.out.println(this.jobj);
		// 拆分巢狀JSON
		JSONObject payload = this.jobj.getJSONObject("payload");
		JSONObject std_data = payload.getJSONObject("std_data");
		this.parameter = std_data.getJSONObject("parameter");

		// 執行結果訊息
		JSONObject execution = std_data.getJSONObject("execution");
		this.sql_code = execution.getString("sql_code");
		this.code = execution.getString("code");
		this.description = execution.getString("description");
		this.master = parameter.getJSONArray(sfObjName + "_master");

	}

}
