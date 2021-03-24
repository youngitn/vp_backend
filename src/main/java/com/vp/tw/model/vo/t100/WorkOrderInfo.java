package com.vp.tw.model.vo.t100;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "工單資訊")
public interface WorkOrderInfo {

	@ApiModelProperty("項次")
	String getSfbaseq();

	@ApiModelProperty("料號")
	String getSfba005();

	@ApiModelProperty("料名")
	String getImaal003();

	@ApiModelProperty("數量")
	String getSfba013();
	
	@ApiModelProperty("已發套數")
	String getSfba016();

	@ApiModelProperty("單位")
	String getSfba014();
	
	

}
