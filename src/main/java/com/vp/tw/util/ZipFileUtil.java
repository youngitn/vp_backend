package com.vp.tw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

@Component
public class ZipFileUtil {

//	public static void main(String[] args) throws IOException {
//		// 壓縮目標目錄
//		String sourceFile = "D:\\zipTest";
//		// create 一個ZIP Stream
//		FileOutputStream fos = new FileOutputStream("dirCompressed.zip");
//		ZipOutputStream zipOut = new ZipOutputStream(fos);
//		// 取得目錄檔案
//		File fileToZip = new File(sourceFile);
//
//		zipFile(fileToZip, fileToZip.getName(), zipOut);
//		zipOut.close();
//		fos.close();
//
//	}

	public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

}
