package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.NoShippingNoticeInfo;
import com.vp.tw.repository.t100.NoShippingNoticeListDao;
import com.vp.tw.requestdto.NoShippingNoticeInfoListRequestDto;
import com.vp.tw.service.NoShippingNoticeService;

@Service
public class NoShippingNoticeServiceImp implements NoShippingNoticeService {

	@Autowired
	NoShippingNoticeListDao dao;
	
	@Override
	public List<NoShippingNoticeInfo> getNoShippingNoticeInfoList(NoShippingNoticeInfoListRequestDto dto) {
		
		return dao.getNoShippingNoticeInfoList(dto.getEnt(), dto.getSite(), dto.getXmdd011Start(), dto.getXmdd011End());
	}

}
