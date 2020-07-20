package com.vp.tw.responsedto;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TobePickedShippingInfoResponseDto {
	
	List<TobePickedShippingInfo> data;
	int page = 1;
	int per_page = 1;

}
