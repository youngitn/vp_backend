package com.vp.tw.requestdto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkOrderInfoRequestDto {
	private int sfaaent;
	private String sfaadocno;
	private List<String> sfaadocnos;
	private String inaa004;
}
