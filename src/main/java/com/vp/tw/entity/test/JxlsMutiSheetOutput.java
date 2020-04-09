package com.vp.tw.entity.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 * 寫資料進樣板病產生多SHEET EXCEL檔
 * @author thecodeexamples
 */
public class JxlsMutiSheetOutput {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {

//		List cars = generateTestCarData();
//		File initialFile = new File("sample.xlsx");
//		try (InputStream is = new FileInputStream(initialFile)) {
//			try (OutputStream os = new FileOutputStream("sample123.xlsx")) {
//				Context context = new Context();
//				context.putVar("cars", cars);
//				JxlsHelper.getInstance().processTemplate(is, os, context);
//			}
//		}
//		test();
	}
/*
	public static void test() throws FileNotFoundException, IOException {
		List<Department> departments = createDepartments();
		File initialFile = new File("multisheet_markup_template.xlsx");
		try (InputStream is = new FileInputStream(initialFile)) {
			try (OutputStream os = new FileOutputStream("multisheet_markup.xlsx")) {
				Context context = PoiTransformer.createInitialContext();
				context.putVar("departments", departments);
				context.putVar("sheetNames", Arrays.asList(departments.get(0).getName(), departments.get(1).getName(),
						departments.get(2).getName()));
				JxlsHelper.getInstance().processTemplate(is, os, context);
			}
		}
	}

	public static List<Department> createDepartments() {
		List<Employee> staff = new ArrayList<Employee>();

		Employee e = new Employee("jack", 18, 56666.0, 5555.0, new Date(),
				new Employee("mike", 18, 56666.0, 5555.0, new Date(), null));
		staff.add(e);
		staff.add(e);

		List<Department> ds = new ArrayList<Department>();
		ds.add(new Department("會計", e, staff, "link"));
		ds.add(new Department("資訊", e, staff, "link"));
		ds.add(new Department("XX", e, staff, "link"));
		return ds;
	}

	public static List<Car> generateTestCarData() {

		return Arrays.asList(new Car("BMW", new BigDecimal("10000"), "jack"),
				new Car("Subaru", new BigDecimal("12000"), "mike"));
	}
	*/
}