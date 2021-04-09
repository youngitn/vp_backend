package com.vp.tw.requestdto;

import lombok.Data;

@Data
public class NoShippingNoticeInfoListRequestDto {
	private int ent;
	private String site;
	private String xmdd011Start; //預計開工日
	private String xmdd011End; //預計開工日
	
}
