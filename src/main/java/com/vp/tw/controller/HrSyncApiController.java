package com.vp.tw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.iteratordemo.IteratorPt;
import com.vp.tw.obsdemo.SomeObserver;
import com.vp.tw.obsdemo.Subject;
import com.vp.tw.obsdemo.SubjectX;
import com.vp.tw.util.T100WebServiceTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@CrossOrigin
@Api(tags = "資料同步API", description = "透過觸發並執行API使各系統所需資料一致")
@RestController
@RequestMapping("/hrdatasync")
@Log
public class HrSyncApiController {

	@ApiOperation(value = "員工基本資料同步", notes = "當HR進行人事異動時將呼叫本API執行各系統的人事基本資料的upsert")
	@PostMapping("/upsertEmployees")
	public ResponseEntity<JSONObject> upsertEmployees(@RequestBody List<Map<String, Object>> mapList) {
		
		/****Observe 模式
		Subject subj = new SubjectX();

		subj.addObserves(new SomeObserver());

		subj.notifyObserves("Yo4籃球");
		subj.notifyObserves("木要四");
		****/
		/****Iterator模式
		IteratorPt ipt = new IteratorPt<Map<String, Object>>(mapList);

		while (ipt.hasNext()) {
			Map<String, Object> n = (Map<String, Object>) ipt.next();
			System.out.println(n.get("name"));

		}
		**/
		//get JSON from hr sys
		
		//json to LIST<OBJ>
		
		//build其他平台使用的input
		
		//人臉辨識 input(JSON)
		
		//BPM??需要DB table & 欄位 相關資料
		
		
		//T100input(專屬req)
		T100WebServiceTool t100WsTool = null;
		
		try {
			t100WsTool = new T100WebServiceTool("userdata");
			t100WsTool.setListValueByKey("userdate", mapList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		JSONObject jo = null;
		try {
			jo = (JSONObject) p.parse(t100WsTool.sendReqToT100());
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok(jo);

	}

}
