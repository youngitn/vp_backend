package com.vp.tw.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vp.tw.entity.t100.Xmdk;
import com.vp.tw.model.vo.t100.Gzcbl;
import com.vp.tw.model.vo.t100.MateriaPrepareInfo;
import com.vp.tw.model.vo.t100.NoShippingNoticeInfo;
import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.model.vo.t100.WorkOrderInfo;
import com.vp.tw.model.vo.t100.WorkOrderInfoWithLocation;
import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;
import com.vp.tw.requestdto.BaseRequestDto;
import com.vp.tw.requestdto.GzcblRequestDto;
import com.vp.tw.requestdto.MateriaPrepareInfoRequestDto;
import com.vp.tw.requestdto.NoShippingNoticeInfoListRequestDto;
import com.vp.tw.requestdto.PendingStorageInfoRequestDto;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.requestdto.WorkOrderInfoRequestDto;
import com.vp.tw.requestdto.WorkOrderProductionScheduleInfoRequestDto;
import com.vp.tw.responsedto.GzcblResponseDto;
import com.vp.tw.responsedto.MateriaPrepareInfoResponseDto;
import com.vp.tw.responsedto.PendingStorageInfoResponseDto;
import com.vp.tw.responsedto.ShipInfoResponseDto;
import com.vp.tw.responsedto.TobePickedShippingInfoResponseDto;
import com.vp.tw.responsedto.WorkOrderProductionScheduleInfoResponseDto;
import com.vp.tw.service.GetListService;
import com.vp.tw.service.GzcblService;
import com.vp.tw.service.MateriaPrepareService;
import com.vp.tw.service.NoShippingNoticeService;
import com.vp.tw.service.TobePickedShippingListService;
import com.vp.tw.service.WorkOrderService;

import io.swagger.annotations.Api;
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
@CrossOrigin
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

	@Qualifier("PendingStorageService")
	@Autowired
	private GetListService pendingStorageService;

	@Qualifier("ShipInfoService")
	@Autowired
	private GetListService shipInfoService;

	@Autowired
	private MateriaPrepareService materiaService;

	@Qualifier("workOrderProductionScheduleService")
	@Autowired
	private GetListService workOrderProductionScheduleService;

	@Autowired
	private GzcblService gzcblService;

	@Autowired
	private WorkOrderService workOrderService;
	
	@Autowired
	private NoShippingNoticeService noShippingNoticeService;

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
	@ApiOperation(value = "取得待檢貨出貨清單", notes = "根據**預計出貨日區間**取得待檢貨出貨清單 參考cxmr999 ")
	@GetMapping("/getTobePickedShippingInfoList")
	public ResponseEntity<TobePickedShippingInfoResponseDto> getTobePickedShippingInfoList(
			@ModelAttribute TobePickedShippingInfoRequestDto dto) throws NotFoundException {

		List<TobePickedShippingInfo> data = tobePickedShippingListservice.getList(dto);

		checkListIsEmpty(data);
		log.info("起日:" + dto.getExpShipStartDate() + " 迄日:" + dto.getExpShipEndDate());
		return ResponseEntity.ok(new TobePickedShippingInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 待入庫清單 一般 apmr931 pmds000 = '1' 委外 apmr932 pmds000 = '8'
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "待入庫清單", notes = "待入庫清單 一般 apmr931 pmds000 = '1' 委外 apmr932 pmds000 = '8'")
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
	@ApiOperation(value = "備料清單", notes = "取得備料清單")
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
	@ApiOperation(value = "工單生產進度表By產線區域")
	@GetMapping("/getWorkOrderProductionScheduleListByArea")
	public ResponseEntity<WorkOrderProductionScheduleInfoResponseDto> getWorkOrderProductionScheduleListByArea(
			@ModelAttribute WorkOrderProductionScheduleInfoRequestDto dto) throws NotFoundException {
		dto.setType("area");
		List<WorkOrderProductionScheduleInfo> data = workOrderProductionScheduleService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity
				.ok(new WorkOrderProductionScheduleInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}
	
	@ApiOperation(value = "工單生產進度表By產線區域")
	@GetMapping("/getWorkOrderProductionScheduleListByAreaTotalNumber")
	public ResponseEntity<String> getWorkOrderProductionScheduleListByAreaTotalNumber(
			@ModelAttribute WorkOrderProductionScheduleInfoRequestDto dto) throws NotFoundException {
		dto.setType("total");
		List<WorkOrderProductionScheduleInfo> data = workOrderProductionScheduleService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity
				.ok(String.valueOf(data.size()));

	}
	

	/**
	 * 工單生產進度表1
	 * 
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "工單生產進度表")
	@GetMapping("/getWorkOrderProductionScheduleList")
	public ResponseEntity<WorkOrderProductionScheduleInfoResponseDto> getWorkOrderProductionScheduleList(
			@ModelAttribute WorkOrderProductionScheduleInfoRequestDto dto) throws NotFoundException {

		List<WorkOrderProductionScheduleInfo> data = workOrderProductionScheduleService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity
				.ok(new WorkOrderProductionScheduleInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	/**
	 * 當日出貨單資訊
	 * 
	 * 
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "當日出貨單資訊")
	@GetMapping("/getTodayShipInfoList")
	public ResponseEntity<ShipInfoResponseDto> getTodayShipInfoList(@ModelAttribute BaseRequestDto dto)
			throws NotFoundException {

		List<Xmdk> data = shipInfoService.getList(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(new ShipInfoResponseDto(data));

	}

	@ApiOperation(value = "取得主要產線別清單")
	@GetMapping("/getGzcblList")
	public ResponseEntity<GzcblResponseDto> getGzcblList(@ModelAttribute GzcblRequestDto dto) throws NotFoundException {

		List<Gzcbl> data = gzcblService.getListByGzcbl001(dto.getGzcbl001());
		checkListIsEmpty(data);

		return ResponseEntity.ok(new GzcblResponseDto(data));

	}

	@ApiOperation(value = "取得單筆工單其備料清單")
	@GetMapping("/getWorkOrderListWithDetail")
	public ResponseEntity<List<WorkOrderInfo>> getWorkOrderListWithDetail(@ModelAttribute WorkOrderInfoRequestDto dto)
			throws NotFoundException {

		List<WorkOrderInfo> data = workOrderService.getWorkOrderListWithDetail(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(data);

	}

	@ApiOperation(value = "取得多筆工單其備料清單 包含庫位資訊")
	@PostMapping("/getWorkOrderListWithDetailByLocation")
	public ResponseEntity<List<WorkOrderInfoWithLocation>> getWorkOrderListWithDetailByLocation(
			@ModelAttribute WorkOrderInfoRequestDto dto) throws NotFoundException, ParseException {

		if (dto.getSfaadocnos().toString().contains("[[")) {
			JSONParser parser = new JSONParser(dto.getSfaadocnos().toString().replace("[[", "[").replace("]]", "]"));
			List<String> strList = new ArrayList<String>();
			for (Object item : parser.list()) {
				strList.add(item.toString());
			}
			dto.setSfaadocnos(strList);
		}

		List<WorkOrderInfoWithLocation> data = workOrderService.getWorkOrderListWithDetailByLocation(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(data);

	}
	
	@ApiOperation(value = "取得已完工入庫、無出貨通知單清單")
	@GetMapping("/getNoShippingNoticeInfoList")
	public ResponseEntity<List<NoShippingNoticeInfo>> getNoShippingNoticeInfoList(
			@ModelAttribute NoShippingNoticeInfoListRequestDto dto) throws NotFoundException, ParseException {

		

		List<NoShippingNoticeInfo> data = noShippingNoticeService.getNoShippingNoticeInfoList(dto);
		checkListIsEmpty(data);

		return ResponseEntity.ok(data);

	}

}
