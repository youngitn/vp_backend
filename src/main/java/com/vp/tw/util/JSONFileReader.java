package com.vp.tw.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;








public class JSONFileReader {
	
	String filePath;
	FileReader reader;
	JSONObject jsonObj;
	
	public JSONFileReader(String filePath){
		this.filePath = filePath;
		try {
			this.reader = getReader();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private FileReader getReader() throws FileNotFoundException {
		return  new FileReader(filePath);
		 	 	 
	}
	
	public String getJSONString() {
		
		
		return jsonObj.toString();
	}
	
	public JSONObject getJSONObject() throws IOException {
		JSONParser jsonParser = new JSONParser();
		
        try 
        {
        	
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            jsonObj = (JSONObject) obj;
             
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return jsonObj;
        
	}
	
	/**
	 * 
	* @Title: replaceAll 
	* @Description: 對JSONObject中,其指定的key給予list值 
	* @param @param json
	* @param @param key
	* @param @param newValue
	* @param @return
	* @param @throws JSONException    設定檔案 
	* @return JSONObject    返回型別 
	* @throws
	 */
	public static JSONObject replaceAll(JSONObject json, String key, List newValue) throws JSONException {
	    Iterator<?> keys =  json.keySet().iterator();
	    while (keys.hasNext()) {
	        String k = (String) keys.next();
	        if (key.equals(k)) {
	            json.put(key, newValue);
	        }
	        Object value = json.get(k);
	        if (value != null && value instanceof JSONObject) {
	            replaceAll((JSONObject) value, key, newValue);
	        }
	    }
	    return json;
	}
	
	/**
	 * 
	* @Title: replaceAll 
	* @Description: 對JSONObject中,其指定的key給予String值 
	* @param @param json
	* @param @param key
	* @param @param newValue
	* @param @return
	* @param @throws JSONException    設定檔案 
	* @return JSONObject    返回型別 
	* @throws
	 */
	public static JSONObject replaceAll(JSONObject json, String key, String newValue) throws JSONException {
	    Iterator<?> keys =  json.keySet().iterator();
	    while (keys.hasNext()) {
	        String k = (String) keys.next();
	        if (key.equals(k)) {
	            json.put(key, newValue);
	        }
	        Object value = json.get(k);
	        if (value != null && value instanceof JSONObject) {
	            replaceAll((JSONObject) value, key, newValue);
	        }
	    }
	    return json;
	}
	
    
    
}

