package com.vp.tw.responsedto;

import java.util.List;

import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkOrderProductionScheduleInfoResponseDto {
	List<WorkOrderProductionScheduleInfo> data;
	int page = 1;
	int per_page = 1;
}
