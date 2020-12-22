package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.entity.t100.Xmdk;
import com.vp.tw.repository.t100.ShipInfoDao;
import com.vp.tw.requestdto.BaseRequestDto;
import com.vp.tw.service.GetListService;
//@Service("ShipInfoService") 在controller的autowrite時會用到這個名稱 
@Service("ShipInfoService")
public class ShipInfoServiceImp implements GetListService{
	
	@Autowired
	ShipInfoDao dao;
	
	@Override
	public List<Xmdk> getList(Object dto) {
		BaseRequestDto baseDto = (BaseRequestDto) dto;
		
		return dao.getShipInfoList(baseDto.getEnt(),baseDto.getSite());
	}

	

}
