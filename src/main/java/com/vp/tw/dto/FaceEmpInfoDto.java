package com.vp.tw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaceEmpInfoDto {

	String status;
	String empid;
	String name;
	String group;
	String card_number;
}