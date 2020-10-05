package com.vp.tw.requestdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingStorageInfoRequestDto {

	@ApiModelProperty(value = "企業編號 ", example = "100")
	private int ent;

	@ApiModelProperty(value = "語系", example = "zh_TW")
	private String dlang;

	@ApiModelProperty(value = "營運據點", example = "TWVP")
	private String site;

	@ApiModelProperty(value = "類型  一般=1; 委外=8", allowableValues = "1,8" ,example = "1")
	private int pmds000;

	@ApiModelProperty(value = "初期開發時用,目前在前端fetch URL中 value預設給1 實際上已無作用 ", example = "1")
	private int page;

	@ApiModelProperty(value = "初期開發時用,目前在前端fetch URL中 value預設給5 實際上已無作用 ", example = "5")
	private int per_page;
}
