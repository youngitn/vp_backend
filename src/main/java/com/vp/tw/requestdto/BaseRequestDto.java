package com.vp.tw.requestdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseRequestDto {
	@ApiModelProperty(value = "企業編號 ", example = "100")
	private String ent;

	@ApiModelProperty(value = "語系", example = "zh_TW")
	private String dlang;

	@ApiModelProperty(value = "營運據點", example = "TWVP")
	private String site;
}
