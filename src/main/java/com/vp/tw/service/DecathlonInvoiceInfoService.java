package com.vp.tw.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.vp.tw.dto.DecathlonInvoiceInfoByDateRangeDto;
import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;

public interface DecathlonInvoiceInfoService {
	
	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(DecathlonInvoiceInfoByDateRangeDto requestDto);

	public List<String> importDataToExcelTemplet(DecathlonInvoiceInfoByDateRangeDto requestDto)
			throws FileNotFoundException, IOException, ParseException;

	public List<String> excelToPdf(DecathlonInvoiceInfoByDateRangeDto requestDto) throws IOException, ParseException;

}
