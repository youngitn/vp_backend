package com.vp.tw.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.requestdto.DecathlonInvoiceInfoByDateRangeRequestDto;
import com.vp.tw.service.DecathlonInvoiceInfoService;
import com.vp.tw.service.FileDownloadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* @ClassName: DecathlonInvoiceInfoController 
* @Description: 迪卡農發票匯出
* @author ytc
* @date 2020年7月29日 上午9:35:55 
*
 */
@Api(tags = "迪卡農發票處理API", description = "處理迪卡農的發票紀錄查詢&匯出")
@RestController
@RequestMapping("/api")
public class DecathlonInvoiceInfoController {

	// 注入介面依賴
	@Autowired
	private DecathlonInvoiceInfoService dclInvService;

	@Autowired
	private FileDownloadService fileDownloadService;

	/**
	 * get all DecathlonInvoiceInfo.
	 * 
	 * @param DecathlonInvoiceInfoByDateRangeRequestDto requestDto
	 * @return
	 */
	@ApiOperation("根據日期查詢發票資料")
	@GetMapping("/DecathlonInvoiceInfoByDate")
	public ResponseEntity<List<DecathlonInvoiceInfo>> getDecathlonInvoiceInfoByDate(
			@ModelAttribute DecathlonInvoiceInfoByDateRangeRequestDto requestDto) {

		return ResponseEntity.ok(dclInvService.getDecathlonInvoiceInfoByDateRange(requestDto));

	}

	/**
	 * 資料寫入excel樣板並存進指定資料夾 同時回傳server端位址給前端產生下載網址
	 * 
	 * @param DecathlonInvoiceInfoByDateRangeRequestDto requestDto
	 * @return List 提供給前端解析為json字串以取得下載網址
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@ApiOperation("資料寫入excel樣板並存進指定資料夾 同時回傳server端位址給前端產生下載網址")
	@GetMapping("/DataToExcel")
	public ResponseEntity<List<String>> getExcelDownloadUul(
			@ModelAttribute DecathlonInvoiceInfoByDateRangeRequestDto requestDto) throws IOException, ParseException {

		return ResponseEntity.ok(dclInvService.importDataToExcelTemplet(requestDto));

	}

	/**
	 * 資料寫入excel樣板並存進指定資料夾 並轉為PDF 同時回傳server端位址給前端產生下載網址
	 * 
	 * @param DecathlonInvoiceInfoByDateRangeRequestDto requestDto
	 * @return List 提供給前端解析為json字串以取得下載網址
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@ApiOperation("資料寫入excel樣板並存進指定資料夾 並轉為PDF 同時回傳server端位址給前端產生下載網址")
	@GetMapping("/DataToPdf")
	public ResponseEntity<List<String>> exportPdf(@ModelAttribute DecathlonInvoiceInfoByDateRangeRequestDto requestDto)
			throws IOException, ParseException {

		return ResponseEntity.ok(dclInvService.excelToPdf(requestDto));

	}

	/**
	 * 檔案下載
	 * 
	 * @param fileName 檔名
	 * @param request  http request物件
	 * @return
	 */
	@ApiOperation("檔案下載")
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

		return fileDownloadService.getFileResponseEntity(fileName, request);
	}

}
