package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.repository.t100.PendingStorageListDao;
import com.vp.tw.requestdto.PendingStorageInfoRequestDto;
import com.vp.tw.service.PendingStorageService;

@Service
public class PendingStorageServiceImp implements PendingStorageService {

	@Autowired
	private PendingStorageListDao dao;

	@Override
	public List<PendingStorageInfo> getList(PendingStorageInfoRequestDto dto) {

		
		int a = dto.getPer_page() * (dto.getPage() - 1);
//		return dao.getPendingStorageList(dto.getDlang(), dto.getEnt(), dto.getSite(), dto.getPmds000(), dto.getPer_page(), a);
		return dao.getPendingStorageList(dto.getDlang(), dto.getEnt(), dto.getSite(), dto.getPmds000());
	}

}
