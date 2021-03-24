package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.WorkOrderInfo;
import com.vp.tw.model.vo.t100.WorkOrderInfoWithLocation;
import com.vp.tw.repository.t100.WorkOrderDao;
import com.vp.tw.requestdto.WorkOrderInfoRequestDto;
import com.vp.tw.service.WorkOrderService;

@Service
public class WorkOrderServiceImp implements WorkOrderService {

		
	@Autowired
	WorkOrderDao dao;
	
	@Override
	public List<WorkOrderInfo> getWorkOrderListWithDetail(WorkOrderInfoRequestDto dto) {
		
		return dao.getWorkOrderListWithDetail(dto.getSfaaent(),dto.getSfaadocno());
	}

	@Override
	public List<WorkOrderInfoWithLocation> getWorkOrderListWithDetailByLocation(WorkOrderInfoRequestDto dto) {
		
		
		return dao.getWorkOrderListWithDetailByLocation(dto.getSfaaent(),dto.getSfaadocnos(),dto.getInaa004());
	}

}
