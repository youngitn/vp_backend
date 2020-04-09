package com.vp.tw.model.vo.t100;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public interface DecathlonInvoiceInfo {

	
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date getInvoiceDate();

	String getOrderNum();

	String getProductName();

	String getCusProductNum();

	String getQty();

	String getUnitPrice();

	String getInvoiceNum();

	String getReceivableNum();// 帳款單號(應收單號)

	
}
