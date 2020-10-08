package com.vp.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.entity.mdb.MeasureData;
import com.vp.tw.service.GetListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@Api(tags = "T100 看板&相關API", description = "API資料來源為T100")
@RestController
@RequestMapping("/kanbanApi")
@Log
public class MdbController {
	
	@Qualifier("MdbService")
	@Autowired
	GetListService service;
	
	@ApiOperation(value="取得待檢貨出貨清單",notes="根據**預計出貨日區間**取得待檢貨出貨清單 參考cxmr999 ")
	@GetMapping("/getMeasureDataList")
	public ResponseEntity<List<MeasureData>> getMeasureDataList(@ModelAttribute MeasureData dto) {
		log.info("進入getMeasureDataList");
		log.info(dto.getEmp_code());
		return ResponseEntity.ok(service.getList(dto));
		
	}
}
