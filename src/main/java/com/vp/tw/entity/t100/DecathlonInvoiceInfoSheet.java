package com.vp.tw.entity.t100;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@JavaBean
@Data
@AllArgsConstructor
public class DecathlonInvoiceInfoSheet {
	private String sheetName;
	private Date invDate;
	private List<DecathlonInvoiceInfo> details = new ArrayList<DecathlonInvoiceInfo>();
}
