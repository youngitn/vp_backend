package com.vp.tw.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.requestdto.DecathlonInvoiceInfoByDateRangeRequestDto;

public interface DecathlonInvoiceInfoService {
	
	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(DecathlonInvoiceInfoByDateRangeRequestDto requestDto);

	public List<String> importDataToExcelTemplet(DecathlonInvoiceInfoByDateRangeRequestDto requestDto)
			throws FileNotFoundException, IOException, ParseException;

	public List<String> excelToPdf(DecathlonInvoiceInfoByDateRangeRequestDto requestDto) throws IOException, ParseException;

}
