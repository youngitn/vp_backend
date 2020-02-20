package com.vp.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.service.t100.DecathlonInvoiceInfoService;

@RestController
@RequestMapping("/api")
public class DecathlonInvoiceInfoController {

	//注入介面依賴
	@Autowired
	DecathlonInvoiceInfoService service;


	@GetMapping("/DecathlonInvoiceInfoByDate")
	public List<DecathlonInvoiceInfo> get(@RequestParam String start, @RequestParam String end) {

		return service.getDecathlonInvoiceInfoByDateRange(start, end);

	}

}
