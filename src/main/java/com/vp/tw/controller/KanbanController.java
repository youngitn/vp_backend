package com.vp.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.service.TobePickedShippingListService;

@RestController
@RequestMapping("/kanbanApi")
public class KanbanController {

	// 注入介面依賴
	@Autowired
	private TobePickedShippingListService service;

	/**
	 * 預計出貨日根據取得待檢貨出貨清單
	 * 
	 * @param expShipDate 預計出貨日
	 * @return
	 */
	@GetMapping("/getTobePickedShippingInfoList")
	public ResponseEntity<List<TobePickedShippingInfo>> getTobePickedShippingInfoList(
			@RequestParam String expShipDate) {

		return ResponseEntity.ok(service.queryByExpShipDate(expShipDate));

	}

}
