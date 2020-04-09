package com.vp.tw.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.property.FileStorageProperties;
import com.vp.tw.service.DecathlonInvoiceInfoService;
import com.vp.tw.util.EnvUtil;
import com.vp.tw.util.FileStorageUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class DecathlonInvoiceInfoController {

	// 注入介面依賴
	@Autowired
	private DecathlonInvoiceInfoService service;
	@Autowired
	private FileStorageUtil fileStorageUtil;
	@Autowired
	private EnvUtil envUtil;
	@Autowired
	FileStorageProperties fileStorageProperties;

	@GetMapping("/DecathlonInvoiceInfoByDate")
	public List<DecathlonInvoiceInfo> get(@RequestParam String start, @RequestParam String end) {

		return service.getDecathlonInvoiceInfoByDateRange(start, end);

	}

	/**
	 * 資料寫入excel樣板並存進指定資料夾 同時回傳server端位址給前端產生下載網址
	 * 
	 * @param start 開始日期
	 * @param end   結束日期
	 * @return List 提供給前端解析為json字串以取得下載網址
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping("/DataToExcel")
	public List<String> exportToExcel(@RequestParam String start, @RequestParam String end) throws Exception {
		String fileName = "";
		// 用LIST包裝在前端方能用JSON解析
		List<String> ret = new ArrayList<>();
		try {
			fileName = service.importDataToExcelTemplet(start, end);

			ret.add("http://" + envUtil.getHostname() + ":" + envUtil.getPort() + "/api/files/" + fileName);
		} catch (Exception e) {
			ret.add("NoData");
		}
		return ret;

	}

	/**
	 * 資料寫入excel樣板並存進指定資料夾 並轉為PDF 同時回傳server端位址給前端產生下載網址
	 * 
	 * @param start 開始日期
	 * @param end   結束日期
	 * @return List 提供給前端解析為json字串以取得下載網址
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping("/DataToPdf")
	public List<String> exportPdf(@RequestParam String start, @RequestParam String end)
			throws IOException, ParseException {

		String fileName = "";
		// 用LIST包裝在前端方能用JSON解析
		List<String> ret = new ArrayList<>();
		try {
			fileName = service.excelToPdf(start, end);
			ret.add("http://" + envUtil.getHostname() + ":" + envUtil.getPort() + "/api/files/" + fileName);
		} catch (Exception e) {
			ret.add("NoData");
			log.info(e.toString());
		}

		return ret;

	}

	/**
	 * 檔案下載
	 * 
	 * @param fileName 檔名
	 * @param request  http request物件
	 * @return
	 */
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		String path = fileStorageProperties.getExcelDir();

		// 判斷檔案類型 區分來源路徑
		if (fileName.contains("pdf")) {
			path = fileStorageProperties.getPdfDir();
		} else if (fileName.contains("zip")) {
			path = fileStorageProperties.getPdfZipDir();
		}
		Resource resource = fileStorageUtil.loadFileAsResource(path + fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
