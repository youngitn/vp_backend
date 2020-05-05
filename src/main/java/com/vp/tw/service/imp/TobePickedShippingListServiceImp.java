package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.repository.t100.TobePickedShippingListDao;
import com.vp.tw.service.TobePickedShippingListService;

@Service
public class TobePickedShippingListServiceImp implements TobePickedShippingListService {
	@Autowired
	private TobePickedShippingListDao dao;

	@Override
	public List<TobePickedShippingInfo> queryByExpShipDate(String expShipDate) {

		return dao.getTobePickedShippingList(expShipDate);
	}

}
