package com.vp.tw.requestdto;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TobePickedShippingInfoRequestDto {

	int page = 1;
	int per_page = 1;
	String xmdgdocno;
	String expShipEndDate;
	String expShipStartDate;

}
