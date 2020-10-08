package com.vp.tw;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import com.vp.tw.entity.mdb.MeasureData;
import com.vp.tw.repository.mdb.MeasureDataDao;

@SpringBootTest
public class MeasureDataTest {

	@Autowired
	MeasureDataDao dao;

	class MeasureDataSort implements Comparator<MeasureData> {
		// 以book的ID升序排列
		@Override
		public int compare(MeasureData a, MeasureData b) {
			
			
			int aa = Integer.parseInt((a.getCreateDateTime()).replaceAll("[-:. ]*", ""));
			
			int bb = Integer.parseInt((b.getCreateDateTime()).replaceAll("[-:. ]*",""));
			
			
			return aa - bb;
		}

		
	}

	@Test
	public void t1() {
		MeasureData md = new MeasureData();
		md.setTemplate("#1 VP-001-all");
		// Sort sort = new Sort(Direction.ASC, null);
		List<MeasureData> m = dao.findAll(Example.of(md));
		;
		System.out.println(m.size());
		Collections.sort(m, new MeasureDataSort()); 
		for (MeasureData measureData : m) {
			System.out.println(measureData.getCreateDateTime());
		}
	}
}
