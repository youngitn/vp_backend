package com.vp.tw.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.repository.t100.TobePickedShippingListDao;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.service.TobePickedShippingListService;
/**
 * 
* @ClassName: TobePickedShippingListByXmdgdocnoServiceImp 
* @Description: 待檢貨出貨清單byXmdgdocno(出貨通知單單號)
* @author ytc
* @date 2020年7月29日 上午9:39:53 
*
 */
@Service("tobePickedShippingListByXmdgdocnoService")
public class TobePickedShippingListByXmdgdocnoServiceImp implements TobePickedShippingListService {
	
	@Autowired
	private TobePickedShippingListDao dao;

	/**
	 * 
	 * 
	 */
	@Override
	public List<TobePickedShippingInfo> getList(TobePickedShippingInfoRequestDto dto) {
		
		
		return dao.getTobePickedShippingListByXmdgdocno(dto.getXmdgdocno());
	}

}
