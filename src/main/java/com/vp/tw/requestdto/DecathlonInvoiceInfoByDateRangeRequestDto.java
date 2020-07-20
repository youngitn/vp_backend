package com.vp.tw.requestdto;

import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * 作為接收cline端用的requestDto,
 * 如增加參數
 * @author USER
 *
 */
@Component
@Data
public class DecathlonInvoiceInfoByDateRangeRequestDto {
	String start;
	String end;
	String cusNo;
}
