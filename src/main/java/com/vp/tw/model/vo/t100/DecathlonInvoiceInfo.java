package com.vp.tw.model.vo.t100;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="迪卡農發票資訊")
public interface DecathlonInvoiceInfo {

	
	@ApiModelProperty("發票日期")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date getInvoiceDate();

	@ApiModelProperty("訂單號碼")
	String getOrderNum();

	@ApiModelProperty("品名")
	String getProductName();

	
	@ApiModelProperty("客戶產編")
	String getCusProductNum();

	@ApiModelProperty("數量")
	String getQty();

	@ApiModelProperty("單價")
	String getUnitPrice();

	@ApiModelProperty("發票號碼")
	String getInvoiceNum();

	@ApiModelProperty("帳款單號(應收單號)")
	String getReceivableNum();// 帳款單號(應收單號)

	
}
