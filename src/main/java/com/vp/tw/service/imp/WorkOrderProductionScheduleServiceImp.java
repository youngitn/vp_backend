package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.repository.t100.WorkOrderProductionScheduleListDao;
import com.vp.tw.requestdto.WorkOrderProductionScheduleInfoRequestDto;
import com.vp.tw.service.WorkOrderProductionScheduleService;
@Service
public class WorkOrderProductionScheduleServiceImp implements WorkOrderProductionScheduleService {
	@Autowired
	private WorkOrderProductionScheduleListDao dao;
	
	/**
	 * 
	 */
	@Override
	public List<WorkOrderProductionScheduleInfo> getList(WorkOrderProductionScheduleInfoRequestDto dto) {
		int a = dto.getPer_page() * (dto.getPage() - 1);
		
		return dao.getWorkOrderProductionScheduleList(
				dto.getEnt(), 
				dto.getSite(), 
				dto.getSfaa020Start(),  //預計完工日 開始
				dto.getSfaa020End(),    //預計完工日 結束
				dto.getPer_page(),
				a);
	}

}
