package com.vp.tw.entity.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;

/**
 * 寫資料進EXCEL樣板 SAMPLE
 * @author thecodeexamples
 */
public class JxlsOutput {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {

		List cars = generateTestCarData();
		File initialFile = new File("sample.xlsx");
		try (InputStream is = new FileInputStream(initialFile)) {
			try (OutputStream os = new FileOutputStream("sample123.xlsx")) {
				Context context = new Context();
				context.putVar("cars", cars);
				JxlsHelper.getInstance().processTemplate(is, os, context);
			}
		}
		Workbook wb = new Workbook();
		wb.loadFromFile("sample123.xlsx");

		wb.saveToFile("ToPDF.pdf", FileFormat.PDF);

	}

	public static List<Car> generateTestCarData() {
		
		return Arrays.asList(new Car("BMW", new BigDecimal("10000"),"jack"), new Car("Subaru", new BigDecimal("12000"),"mike"));
	}
}