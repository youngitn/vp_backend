package com.vp.tw.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MateriaPrepareInfoRequestDto {

	private String site;
	private String stus;
	private String sfaa019; //預計開工日
	private int page;
	private int per_page;
}
