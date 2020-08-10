package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;

public interface TobePickedShippingListService {
	List<TobePickedShippingInfo> getList(TobePickedShippingInfoRequestDto dto);
}
