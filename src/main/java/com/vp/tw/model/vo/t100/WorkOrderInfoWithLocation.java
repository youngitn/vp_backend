package com.vp.tw.model.vo.t100;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "工單資訊 含備料清單庫位資訊")
public interface WorkOrderInfoWithLocation extends WorkOrderInfo{
	
	@ApiModelProperty("工單號碼")
	String getSfaadocno();
	
	@ApiModelProperty("儲位")
	String getInag005();
	
	@ApiModelProperty("庫位")
	String getInag004();
	
	@ApiModelProperty("儲位控管")
	String getInaa007();
	
	@ApiModelProperty("庫存數量")
	String getInag008();
}
