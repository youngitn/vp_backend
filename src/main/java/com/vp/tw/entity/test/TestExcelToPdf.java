package com.vp.tw.entity.test;

import com.spire.xls.*;
/**
 * 免費版 有警告標語和sheet限制
 * @author USER
 *
 */
public class TestExcelToPdf {
	public static void main(String[] args) {

		Workbook wb = new Workbook();
		wb.loadFromFile("multisheet_markup.xlsx");

		wb.saveToFile("ToPDF.pdf", FileFormat.PDF);
	}
}