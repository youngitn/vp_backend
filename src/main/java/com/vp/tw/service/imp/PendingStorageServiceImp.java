package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.repository.t100.PendingStorageListDao;
import com.vp.tw.requestdto.PendingStorageInfoRequestDto;
import com.vp.tw.service.GetListService;

@Service("PendingStorageService")
public class PendingStorageServiceImp implements GetListService {

	@Autowired
	private PendingStorageListDao dao;

	@Override
	public List<PendingStorageInfo> getList(Object dto) {

		PendingStorageInfoRequestDto ps = (PendingStorageInfoRequestDto) dto;
		//int a = ps.getPer_page() * (ps.getPage() - 1);
//		return dao.getPendingStorageList(dto.getDlang(), dto.getEnt(), dto.getSite(), dto.getPmds000(), dto.getPer_page(), a);
		return dao.getPendingStorageList(ps.getDlang(), ps.getEnt(), ps.getSite(), ps.getPmds000());
	}

}
