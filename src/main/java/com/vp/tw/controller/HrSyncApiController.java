package com.vp.tw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.vp.tw.dto.FaceEmpInfoDto;
import com.vp.tw.util.APIUriCallUtil;
import com.vp.tw.util.T100WebServiceTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@CrossOrigin
@Api(tags = "資料同步API", description = "透過觸發並執行API使各系統所需資料一致")
@RestController
@RequestMapping("/hrdatasync")
@Log4j2
public class HrSyncApiController {

	@ApiOperation(value = "員工基本資料同步", notes = "當HR進行人事異動時將呼叫本API執行各系統的人事基本資料的upsert")
	@PostMapping("/upsertEmployees")
	public ResponseEntity<JSONObject> upsertEmployees(@RequestBody List<Map<String, Object>> mapList) {

		/****
		 * Observe 模式 Subject subj = new SubjectX();
		 * 
		 * subj.addObserves(new SomeObserver());
		 * 
		 * subj.notifyObserves("Yo4籃球"); subj.notifyObserves("木要四");
		 ****/
		/****
		 * Iterator模式 IteratorPt ipt = new IteratorPt<Map<String, Object>>(mapList);
		 * 
		 * while (ipt.hasNext()) { Map<String, Object> n = (Map<String, Object>)
		 * ipt.next(); System.out.println(n.get("name"));
		 * 
		 * }
		 **/
		// get JSON from hr sys

		// json to LIST<OBJ>

		// build其他平台使用的input

		// 人臉辨識 input(JSON) 請修改f.get("屬性名稱")
		// URI改API URI
		List<FaceEmpInfoDto> faceEmpInfoList = mapList.stream()
				.map(f -> new FaceEmpInfoDto(f.get("ooag003").toString(), f.get("ooag003").toString(),
						f.get("ooag003").toString(), f.get("ooag003").toString(), f.get("ooag003").toString()))
				.collect(Collectors.toList());
		String json = new Gson().toJson(faceEmpInfoList);
		System.out.println(json);
		APIUriCallUtil apiUriCall = new APIUriCallUtil();
		apiUriCall.sendReq("URI", "application/json; charset=utf-8", "UTF-8", json);

		/**
		 * call api
		 * 
		 */
		// BPM??需要DB table & 欄位 相關資料

		// T100input(專屬req)

		T100WebServiceTool t100WsTool = null;

		try {
			t100WsTool = new T100WebServiceTool("userdata");
			t100WsTool.setListValueByKey("userdate", mapList);

		} catch (IOException e) {

			log.error(e.getStackTrace().toString());
			e.printStackTrace();

		}

		JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		JSONObject jo = null;
		try {
			jo = (JSONObject) p.parse(t100WsTool.sendReqToT100());
		} catch (ParseException e) {
			log.error(e.getStackTrace().toString());
			e.printStackTrace();

		}

		log.info(jo);

		// 目前回傳的是T100執行後訊息
		return ResponseEntity.ok(jo);

	}

}
