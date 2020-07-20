package com.vp.tw.controller;

import java.util.List;

import javax.sound.midi.Track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.model.vo.t100.MateriaPrepareInfo;
import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.requestdto.MateriaPrepareInfoRequestDto;
import com.vp.tw.requestdto.PendingStorageInfoRequestDto;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.requestdto.WorkOrderProductionScheduleInfoRequestDto;
import com.vp.tw.responsedto.MateriaPrepareInfoResponseDto;
import com.vp.tw.responsedto.PendingStorageInfoResponseDto;
import com.vp.tw.responsedto.TobePickedShippingInfoResponseDto;
import com.vp.tw.responsedto.WorkOrderProductionScheduleInfoResponseDto;
import com.vp.tw.service.MateriaPrepareService;
import com.vp.tw.service.PendingStorageService;
import com.vp.tw.service.TobePickedShippingListService;
import com.vp.tw.service.WorkOrderProductionScheduleService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/kanbanApi")
public class KanbanController {

	// 注入介面依賴
	@Autowired
	private TobePickedShippingListService tobePickedShippingListservice;
	@Autowired
	private PendingStorageService pendingStorageService;

	@Autowired
	private MateriaPrepareService materiaService;
	@Autowired
	private WorkOrderProductionScheduleService workOrderProductionScheduleService;

	private void checkListIsEmpty(List<?> list) throws NotFoundException {
		if (list.isEmpty()) {
			throw new NotFoundException("因data.size() == 0 ,卻依舊對data作取值操作導致錯誤.");
		}
	}

	/**
	 * 根據預計出貨日區間取得待檢貨出貨清單 前端會循環呼叫 cxmr999
	 * 
	 * @param expShipDate 預計出貨日
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/getTobePickedShippingInfoList")
	public ResponseEntity<TobePickedShippingInfoResponseDto> getTobePickedShippingInfoList(
			@ModelAttribute TobePickedShippingInfoRequestDto dto) throws NotFoundException {
		
		List<TobePickedShippingInfo> data = tobePickedShippingListservice.queryByExpShipDate(dto);
		
		
		checkListIsEmpty(data);

		return ResponseEntity.ok(new TobePickedShippingInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 待入庫清單 一般 apmr931 pmds000 = '1' 委外 apmr932 pmds000 = '8'
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/getPendingStorageList")
	public ResponseEntity<PendingStorageInfoResponseDto> getPendingStorageList(
			@ModelAttribute PendingStorageInfoRequestDto dto) throws NotFoundException {

		List<PendingStorageInfo> data = pendingStorageService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(new PendingStorageInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 備料清單
	 * 
	 *  
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/getMateriaPrepareList")
	public ResponseEntity<MateriaPrepareInfoResponseDto> getMateriaPrepareList(
			@ModelAttribute MateriaPrepareInfoRequestDto dto) throws NotFoundException {

		List<MateriaPrepareInfo> data = materiaService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(new MateriaPrepareInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 工單生產進度表
	 * 
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/getWorkOrderProductionScheduleList")
	public ResponseEntity<WorkOrderProductionScheduleInfoResponseDto> getWorkOrderProductionScheduleList(
			@ModelAttribute WorkOrderProductionScheduleInfoRequestDto dto) throws NotFoundException {

		List<WorkOrderProductionScheduleInfo> data = workOrderProductionScheduleService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity
				.ok(new WorkOrderProductionScheduleInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

}
