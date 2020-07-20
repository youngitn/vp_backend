package com.vp.tw.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkOrderProductionScheduleInfoRequestDto {

	private int ent;
	private String site;
//	private String stus;
	private String sfaa020Start; //預計開工日
	private String sfaa020End; //預計開工日
	private int page;
	private int per_page;
}
