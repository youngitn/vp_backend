package com.vp.tw.service.imp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vp.tw.entity.mdb.MeasureData;
import com.vp.tw.repository.mdb.MeasureDataDao;
import com.vp.tw.service.GetListService;

@Service("MdbService")
public class MdbServiceImp implements GetListService {

	@Autowired
	MeasureDataDao mdd;
	
	@Override
	public List getList(Object dto) {
		List<MeasureData> ml = mdd.findAll(Example.of((MeasureData)dto));
		//Collections.sort(ml, new MeasureDataSort()); 
		return ml;
	}
	
	class MeasureDataSort implements Comparator<MeasureData> {
		
		@Override
		public int compare(MeasureData a, MeasureData b) {
			
			String aaa = (a.getCreateDateTime()).replaceAll("[-:. ]*", "");
			String bbb = (b.getCreateDateTime()).replaceAll("[-:. ]*", "");
			Long aa = Long.valueOf(aaa);
			
			Long bb = Long.valueOf(bbb);
			
			
			return aa.compareTo(bb);
		}

		
	}

}
