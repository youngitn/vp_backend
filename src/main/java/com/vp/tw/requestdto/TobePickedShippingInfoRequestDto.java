package com.vp.tw.requestdto;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TobePickedShippingInfoRequestDto {

	int page;
	int per_page;

	String expShipEndDate;
	String expShipStartDate;

}
