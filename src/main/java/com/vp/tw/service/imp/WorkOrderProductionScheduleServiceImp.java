package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.repository.t100.WorkOrderProductionScheduleListDao;
import com.vp.tw.requestdto.WorkOrderProductionScheduleInfoRequestDto;
import com.vp.tw.service.GetListService;

@Service("workOrderProductionScheduleService")
public class WorkOrderProductionScheduleServiceImp implements GetListService {
	@Autowired
	private WorkOrderProductionScheduleListDao dao;

	/**
	 * 
	 */
	@Override
	public List<WorkOrderProductionScheduleInfo> getList(Object dto) {
		WorkOrderProductionScheduleInfoRequestDto wopsir = (WorkOrderProductionScheduleInfoRequestDto) dto;
		int a = wopsir.getPer_page() * (wopsir.getPage() - 1);

		if ("area".equals(wopsir.getType())) {
			return dao.getWorkOrderProductionScheduleListByArea(
					wopsir.getEnt(), 
					wopsir.getSite(),
					wopsir.getSfaa020Start(), // 預計完工日 開始
					wopsir.getSfaa020End(), // 預計完工日 結束
					wopsir.getPer_page(), a,wopsir.getArea(),wopsir.getSfaa057());
		}else if("total".equals(wopsir.getType())) {
			return dao.getWorkOrderProductionScheduleListByAreaTotalNumber(
					wopsir.getEnt(), 
					wopsir.getSite(),
					wopsir.getSfaa020Start(), // 預計完工日 開始
					wopsir.getSfaa020End(), // 預計完工日 結束
					wopsir.getArea(),
					wopsir.getSfaa057());
		}
		return dao.getWorkOrderProductionScheduleList(wopsir.getEnt(), wopsir.getSite(), wopsir.getSfaa020Start(), // 預計完工日
																													// 開始
				wopsir.getSfaa020End(), // 預計完工日 結束
				wopsir.getPer_page(), a);
	}

}
