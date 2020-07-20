package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.repository.t100.TobePickedShippingListDao;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.service.TobePickedShippingListService;

@Service
public class TobePickedShippingListServiceImp implements TobePickedShippingListService {
	@Autowired
	private TobePickedShippingListDao dao;

	@Override
	public List<TobePickedShippingInfo> queryByExpShipDate(TobePickedShippingInfoRequestDto dto) {
		int a = dto.getPer_page() * (dto.getPage() - 1);
		
//		return dao.getTobePickedShippingList(dto.getExpShipStartDate(),dto.getExpShipEndDate(), dto.getPer_page(),
//				a);
		
		return dao.getTobePickedShippingList(dto.getExpShipStartDate(),dto.getExpShipEndDate());
	}

}
