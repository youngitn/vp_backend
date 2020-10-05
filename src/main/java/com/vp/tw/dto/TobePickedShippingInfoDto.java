package com.vp.tw.dto;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TobePickedShippingInfoDto {

	List<TobePickedShippingInfo> data;
	int page = 1;
	int per_page = 1;
	int total = 0;
	int total_pages = 1;
	String expShipDate;

}
