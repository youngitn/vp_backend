package com.vp.tw.requestdto;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingStorageInfoRequestDto {

	private int ent;
	private String dlang;
	private String site;
	private int pmds000;
	private int page;
	private int per_page;
}
