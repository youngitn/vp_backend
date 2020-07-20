package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.MateriaPrepareInfo;
import com.vp.tw.repository.t100.MateriaPrepareListDao;
import com.vp.tw.requestdto.MateriaPrepareInfoRequestDto;
import com.vp.tw.service.MateriaPrepareService;

@Service
public class MateriaPrepareServiceImp implements MateriaPrepareService {
	@Autowired
	private MateriaPrepareListDao dao;

	@Override
	public List<MateriaPrepareInfo> getList(MateriaPrepareInfoRequestDto dto) {

		int a = dto.getPer_page() * (dto.getPage() - 1);
//		return dao.getMateriaList(dto.getStus(), dto.getSite(), dto.getSfaa019(),dto.getPer_page(), a);
		return dao.getMateriaList(dto.getStus(), dto.getSite(), dto.getSfaa019());
	}

}
