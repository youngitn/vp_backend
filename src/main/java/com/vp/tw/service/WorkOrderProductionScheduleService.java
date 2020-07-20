package com.vp.tw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.requestdto.WorkOrderProductionScheduleInfoRequestDto;

public interface WorkOrderProductionScheduleService {

	public List<WorkOrderProductionScheduleInfo> getList(WorkOrderProductionScheduleInfoRequestDto dto);

}
