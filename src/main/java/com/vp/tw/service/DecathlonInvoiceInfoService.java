package com.vp.tw.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;

public interface DecathlonInvoiceInfoService {

	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(String start, String end);


	public String importDataToExcelTemplet(String start, String end)
			throws FileNotFoundException, IOException, ParseException;
	
	public String excelToPdf(String start, String end) throws IOException, ParseException;
}
