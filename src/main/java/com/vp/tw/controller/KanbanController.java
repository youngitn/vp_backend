package com.vp.tw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.entity.t100.Inag;
import com.vp.tw.model.vo.t100.MateriaPrepareInfo;
import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.repository.t100.InagDao;
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
import com.vp.tw.service.StockService;
import com.vp.tw.service.TobePickedShippingListService;
import com.vp.tw.service.WorkOrderProductionScheduleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.extern.java.Log;
/**
 * 
* @ClassName: KanbanController 
* @Description: T100 看板&相關API
* @author ytc
* @date 2020年7月29日 上午9:35:05 
*
 */
@Api(tags = "T100 看板&相關API", description = "API資料來源為T100")
@RestController
@RequestMapping("/kanbanApi")
@Log
public class KanbanController {

	// 注入介面依賴
	@Qualifier("tobePickedShippingListService")
	@Autowired
	private TobePickedShippingListService tobePickedShippingListservice;

	@Qualifier("tobePickedShippingListByXmdgdocnoService")
	@Autowired
	private TobePickedShippingListService tobePickedShippingListByXmdgdocnoService;

	@Autowired
	private PendingStorageService pendingStorageService;

	@Autowired
	private MateriaPrepareService materiaService;
	@Autowired
	private WorkOrderProductionScheduleService workOrderProductionScheduleService;

	@Autowired
	private InagDao inagDao;

	@Autowired
	private StockService stockService;

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
	@ApiOperation(value="取得待檢貨出貨清單",notes="根據**預計出貨日區間**取得待檢貨出貨清單 參考cxmr999 ")
	@GetMapping("/getTobePickedShippingInfoList")
	public ResponseEntity<TobePickedShippingInfoResponseDto> getTobePickedShippingInfoList(
			@ModelAttribute TobePickedShippingInfoRequestDto dto) throws NotFoundException {

		List<TobePickedShippingInfo> data = tobePickedShippingListservice.getList(dto);

		checkListIsEmpty(data);
		log.info("起日:"+dto.getExpShipStartDate()+" 迄日:"+dto.getExpShipEndDate());
		return ResponseEntity.ok(new TobePickedShippingInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 待入庫清單 一般 apmr931 pmds000 = '1' 委外 apmr932 pmds000 = '8'
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value="待入庫清單",notes="待入庫清單 一般 apmr931 pmds000 = '1' 委外 apmr932 pmds000 = '8'")
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
	@ApiOperation(value="備料清單",notes="取得備料清單")
	@GetMapping("/getMateriaPrepareList")
	public ResponseEntity<MateriaPrepareInfoResponseDto> getMateriaPrepareList(
			@ModelAttribute MateriaPrepareInfoRequestDto dto) throws NotFoundException {

		List<MateriaPrepareInfo> data = materiaService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(new MateriaPrepareInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 工單生產進度表1
	 * 
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value="工單生產進度表")
	@GetMapping("/getWorkOrderProductionScheduleList")
	public ResponseEntity<WorkOrderProductionScheduleInfoResponseDto> getWorkOrderProductionScheduleList(
			@ModelAttribute WorkOrderProductionScheduleInfoRequestDto dto) throws NotFoundException {

		List<WorkOrderProductionScheduleInfo> data = workOrderProductionScheduleService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity
				.ok(new WorkOrderProductionScheduleInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	

}
