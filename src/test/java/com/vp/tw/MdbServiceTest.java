package com.vp.tw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.tw.entity.mdb.MeasureData;
import com.vp.tw.service.imp.MdbServiceImp;

@SpringBootTest
public class MdbServiceTest {

	@Autowired
	MdbServiceImp service;
	@Test
	public void getListTest() {
		MeasureData m = new MeasureData();
		m.setTemplate("#1 VP-001-all");
		List ll =  service.getList(m);
		System.out.println(ll.size());
		assertEquals(ll.size(), 1350);
		
	}
}
