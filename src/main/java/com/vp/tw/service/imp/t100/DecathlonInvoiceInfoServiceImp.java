package com.vp.tw.service.imp.t100;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.repository.t100.DecathlonInvoiceInfoDao;
import com.vp.tw.service.t100.DecathlonInvoiceInfoService;

@Service
public class DecathlonInvoiceInfoServiceImp implements DecathlonInvoiceInfoService {
	@Autowired
	DecathlonInvoiceInfoDao testDao;

	@Override
	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(String start, String end) {
		// TODO Auto-generated method stub
		return testDao.getInvoiceInfoByDateRange(start, end);
	}

}
