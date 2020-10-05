package com.vp.tw.model.vo.t100;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="待檢貨出貨資訊")
public interface TobePickedShippingInfo {

	@ApiModelProperty("營運據點")
	String getXmdhsite(); // 營運據點siteNo

	@ApiModelProperty("企業編號")
	String getXmdhent(); // 企業編號entNo

	
	@ApiModelProperty("單據編號")
	String getXmdhdocno(); // 單據編號docNo

	@ApiModelProperty("項次")
	String getXmdhseq(); // 項次seq

	@ApiModelProperty("訂單客戶")
	String getXmdg005(); // 訂單客戶cusNo

	@ApiModelProperty("預計出貨日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	Date getXmdg028(); // 預計出貨日期
	
	@ApiModelProperty("出通單據日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	Date getXmdgdocdt(); // 出通單據日期
	
	@ApiModelProperty("料件編號")
	String getXmdh006(); // 料件編號ProductNo

	@ApiModelProperty("訂單單號")
	String getXmdh001(); // 訂單單號
	
	@ApiModelProperty("業務人員")
	String getXmdg002(); // 業務人員

	@ApiModelProperty("品名")
	String getImaal003(); // 品名 ProductName

	@ApiModelProperty("規格")
	String getImaal004(); // 規格 spec

	@ApiModelProperty("產品特徵")
	String getXmdh007(); // 產品特徵SignatureNo

	@ApiModelProperty("申請出通數量")
	String getXmdh016(); // 申請出通數量ApplyShipNotifyQty
	
	@ApiModelProperty("已轉出貨量")
	String getXmdh030(); // 已轉出貨量ApplyShipNotifyQty
	
	@ApiModelProperty("實際出通數量")
	String getXmdh017(); // 實際出通數量
	
	@ApiModelProperty("出貨單位")
	String getXmdh015(); // 出貨單位ShippingUnit

	@ApiModelProperty("工單單號")
	String getSfec001(); // 工單單號WorkOrderNo

	@ApiModelProperty("已入庫合格量")
	String getSfaa050(); // 已入庫合格量QualifiedQty

	
	@ApiModelProperty("庫位")
	String getSfec012(); // 庫位locationNo

	@ApiModelProperty("儲位")
	String getSfec013(); // 儲位storageNo

	@ApiModelProperty("批號")
	String getSfec014(); // 批號batchNo

	@ApiModelProperty("帳面庫存數量")
	String getInag008(); // 帳面庫存數量bookStockQty

	@ApiModelProperty("客戶訂購單號")
	String getXmda033(); // 客戶訂購單號 cusOrderNo

	@ApiModelProperty("交易對象簡稱")
	String getPmaal004(); // 交易對象簡稱cusAbbreviation

}
