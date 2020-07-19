package com.vp.tw.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DecathlonInvoiceInfoByDateRangeDto {
	String start;
	String end;
	String cusNo;
}
