package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

public interface TobePickedShippingListService {
	List<TobePickedShippingInfo> queryByExpShipDate(String expShipDate);
}
