package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.Gzcbl;
import com.vp.tw.repository.t100.GzcblDao;
import com.vp.tw.service.GzcblService;

@Service
public class GzcblServiceImp implements GzcblService{

	@Autowired
	GzcblDao dao;
	
	
	@Override
	public List<Gzcbl> getListByGzcbl001(String gzcbl001) {
		
		return dao.findByGzcbl001(Integer.parseInt(gzcbl001));
	}

}
