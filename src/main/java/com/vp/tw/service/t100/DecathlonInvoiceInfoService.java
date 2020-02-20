package com.vp.tw.service.t100;

import java.util.List;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;

public interface DecathlonInvoiceInfoService {

	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(String start, String end);
}
