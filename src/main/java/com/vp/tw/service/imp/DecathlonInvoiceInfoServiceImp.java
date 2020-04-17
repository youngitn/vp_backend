package com.vp.tw.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.poi.util.IOUtils;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;
import com.vp.tw.entity.t100.DecathlonInvoiceInfoSheet;
import com.vp.tw.exception.NoDataException;
import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;
import com.vp.tw.repository.t100.DecathlonInvoiceInfoDao;
import com.vp.tw.service.DecathlonInvoiceInfoService;
import com.vp.tw.util.ZipDirectoryUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DecathlonInvoiceInfoServiceImp implements DecathlonInvoiceInfoService {
	@Autowired
	DecathlonInvoiceInfoDao decathlonInvoiceInfoDao;
	@Autowired
	ZipDirectoryUtil zipUtil;
	@Value("${file.excel.dir}")
	private String excelDir;
	@Value("${decathlon.invoice.export.excel.templet}")
	private String decathlonInvoiceExportExcelTemplet;
	@Value("${decathlon.invoice.export.excel.templet.nosheet}")
	private String decathlonInvoiceExportExcelTempletNosheet;
	@Value("${decathlon.invoice.export.excel.templetpng}")
	private String decathlonInvoiceExportExcelTempletPng;
	@Value("${file.excel.temp.dir}")
	private String tempExcelPath;
	@Value("${file.pdf.dir}")
	private String decathlonInvoiceExportPdfPath;
	@Value("${file.pdf.zip.dir}")
	private String zipPath;

	/**
	 * 輸入日期區間查詢迪卡農發票明細資料
	 * 
	 * @param start 開始日期
	 * @param end   結束日期
	 */
	@Override
	public List<DecathlonInvoiceInfo> getDecathlonInvoiceInfoByDateRange(String start, String end) {

		return decathlonInvoiceInfoDao.getInvoiceInfoByDateRange(start, end);
	}

	/**
	 * 資料寫入樣板產生xlsx檔後再利用該檔產生PDF. 回傳PDF檔名組建出下載網址.
	 * 
	 * @param start 開始日期
	 * @param end   結束日期
	 */
	@Override
	public String excelToPdf(String start, String end) throws IOException, ParseException {
		deleteDirectory(tempExcelPath);
		deleteDirectory(decathlonInvoiceExportPdfPath);
		deleteDirectory(zipPath);
		checkDirectories(tempExcelPath);
		checkDirectories(zipPath);
		checkDirectories(decathlonInvoiceExportPdfPath);
		// 檔名處理
		String[] dt = start.split("/");
		String fileName = dt[0].substring(2) + String.format("%02d", Integer.parseInt(dt[1]));
		List<DecathlonInvoiceInfoSheet> sheets = createDecathlonInvoiceInfo(start, end);

		/**
		 * 產生單一sheet EXCEL檔後轉PDF
		 */
		/* read一次樣板 */

		for (DecathlonInvoiceInfoSheet sheet : sheets) {
			
			String name = sheet.getSheetName();
			// 暫存資料夾
			String filePath = tempExcelPath + name + ".xlsx";
			log.info(filePath);
			File initialFile = new File(decathlonInvoiceExportExcelTempletNosheet);
			try (InputStream is = new FileInputStream(initialFile)) {

				try (OutputStream os = new FileOutputStream(filePath)) {

					InputStream imageInputStream = new FileInputStream(decathlonInvoiceExportExcelTempletPng);
					byte[] imageBytes = IOUtils.toByteArray(imageInputStream);
					Context context = PoiTransformer.createInitialContext();

					List<DecathlonInvoiceInfoSheet> sheetList = new ArrayList<>();
					sheetList.add(sheet);
					context.putVar("sheets", sheetList);

					// System.out.println("------>" + dateString + " " + String.format("%02d",
					// number));
					// context.putVar("invNum", dateString + " " + String.format("%02d", number));

					context.putVar("details", sheet.getDetails());
					context.putVar("image", imageBytes);

					JxlsHelper.getInstance().setEvaluateFormulas(true).processTemplate(is, os, context);
				}
				Workbook wb = new Workbook();

				wb.loadFromFile(filePath);

				wb.saveToFile(decathlonInvoiceExportPdfPath + name + ".pdf", FileFormat.PDF);
			}

		}

		// 壓縮目標目錄
		String sourceFile = decathlonInvoiceExportPdfPath;
		// create 一個ZIP Stream
		FileOutputStream fos = new FileOutputStream(zipPath + fileName + ".zip");
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		// 取得目錄檔案
		File fileToZip = new File(sourceFile);

		zipUtil.zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();

		return fileName + ".zip";

	}

	private void checkDirectories(String dir) {
		Path path = Paths.get(dir);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
	}

	/**
	 * 資料寫入excel樣板並匯出至D:excel 路徑 最後回傳excel檔名組建出下載網址.
	 * 
	 * @param start 開始日期
	 * @param end   結束日期
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Override
	public String importDataToExcelTemplet(String start, String end) throws FileNotFoundException, IOException {

		checkDirectories(excelDir);
		// 檔名處理
		String[] dt = start.split("/");
		String fileName = dt[0].substring(2) + String.format("%02d", Integer.parseInt(dt[1]));
		String excelFileName = fileName + ".xlsx";
		String excelFilePath = excelDir + excelFileName;

		List<DecathlonInvoiceInfoSheet> sheets = createDecathlonInvoiceInfo(start, end);
		Collections.sort(sheets);
		List<String> sheetNameList = new ArrayList<>();
		for (DecathlonInvoiceInfoSheet sheet : sheets) {
			sheetNameList.add(sheet.getSheetName());
		}

		File initialFile = new File(decathlonInvoiceExportExcelTemplet);
		try (InputStream is = new FileInputStream(initialFile)) {
			try (OutputStream os = new FileOutputStream(excelFilePath)) {
				Context context = PoiTransformer.createInitialContext();
				context.putVar("sheets", sheets);
				InputStream imageInputStream = new FileInputStream(decathlonInvoiceExportExcelTempletPng);
				byte[] imageBytes = IOUtils.toByteArray(imageInputStream);
				context.putVar("image", imageBytes);
				context.putVar("sheetNames", sheetNameList);
				JxlsHelper.getInstance().setEvaluateFormulas(true).processTemplate(is, os, context);
			}
		}

		return excelFileName;
	}

	public List<DecathlonInvoiceInfoSheet> createDecathlonInvoiceInfo(String start, String end) throws NoDataException {

		List<DecathlonInvoiceInfo> all = getDecathlonInvoiceInfoByDateRange(start, end);
		if (all.size() == 0) {
			throw new NoDataException("find no data by date range between " + start + " and " + end);

		}
		/* 分組By ReceivableNum */
		Map<String, List<DecathlonInvoiceInfo>> skuIdMap = new HashMap<>();

		for (DecathlonInvoiceInfo detail : all) {
			List<DecathlonInvoiceInfo> tempList = skuIdMap.get(detail.getReceivableNum());
			/* 如果取不到資料,那麼直接new一個空的ArrayList **/
			if (tempList == null) {
				tempList = new ArrayList<>();
				tempList.add(detail);
				skuIdMap.put(detail.getReceivableNum(), tempList);

			} else {
				/* 某個tempList之前已經存放過了,則直接追加資料到原來的List裡 **/
				tempList.add(detail);
			}
		}

		/* 將分組好的detail資料放進sheet裡,再將sheet放進sheets */
		List<DecathlonInvoiceInfoSheet> sheets = new ArrayList<DecathlonInvoiceInfoSheet>();
		for (String receivableNum : skuIdMap.keySet()) {
			List<DecathlonInvoiceInfo> detail = skuIdMap.get(receivableNum);
			Date invDate = detail.get(0).getInvoiceDate();

			sheets.add(new DecathlonInvoiceInfoSheet(receivableNum, invDate, detail, ""));
		}

		/* 將sheets根據日期排序 */
		Collections.sort(sheets);

		/* 根據日期gen出 inNum=日期+流水號 */
		List<String> sheetNameList = new ArrayList<String>();
		int number = 1;
		String tempDate = "";
		for (DecathlonInvoiceInfoSheet sheet : sheets) {
			sheetNameList.add(sheet.getSheetName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
			String dateString = formatter.format(sheet.getInvDate());
			if (dateString.equals(tempDate)) {
				number++;
			} else {
				number = 1;
			}
			sheet.setInvNum(dateString + " " + String.format("%02d", number));
			// sheets.add(sheet);
			tempDate = dateString;
		}

		return sheets;
	}

	/**
	 * 刪除目錄（資料夾）以及目錄下的檔案
	 * 
	 * @param dir 被刪除目錄的檔案路徑
	 * @return 目錄刪除成功返回true,否則返回false
	 */
	public static boolean deleteDirectory(String directorydir) {
		// 如果dir不以檔案分隔符結尾，自動新增檔案分隔符
		if (!directorydir.endsWith(File.separator)) {
			directorydir = directorydir + File.separator;
		}
		File dirFile = new File(directorydir);
		// 如果dir對應的檔案不存在，或者不是一個目錄，則退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			// "刪除目錄失敗"+name+"目錄不存在！"
			return false;
		}
		boolean flag = true;
		// 刪除資料夾下的所有檔案(包括子目錄)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 刪除子檔案
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 刪除子目錄
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			// System.out.println("刪除目錄失敗");
			return false;
		}

		// 刪除當前目錄
		if (dirFile.delete()) {
			// System.out.println("刪除目錄"+directorydir+"成功！");
			return true;
		} else {
			// System.out.println("刪除目錄"+directorydir+"失敗！");
			return false;
		}
	}

	/**
	 * 刪除單個檔案
	 * 
	 * @param fileName 被刪除檔案的檔名
	 * @return 單個檔案刪除成功返回true,否則返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();// "刪除單個檔案"+name+"成功！"
			return true;
		} // "刪除單個檔案"+name+"失敗！"
		return false;
	}

}
