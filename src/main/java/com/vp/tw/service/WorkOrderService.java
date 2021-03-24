package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.WorkOrderInfo;
import com.vp.tw.model.vo.t100.WorkOrderInfoWithLocation;
import com.vp.tw.requestdto.WorkOrderInfoRequestDto;


public interface WorkOrderService {
	

	List<WorkOrderInfo> getWorkOrderListWithDetail(WorkOrderInfoRequestDto dto);
	List<WorkOrderInfoWithLocation> getWorkOrderListWithDetailByLocation(WorkOrderInfoRequestDto dto);

	
}
