package com.vp.tw.util;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class DeleteFileAndDirUtil {
	/**
	 * 刪除目錄（資料夾）以及目錄下的檔案
	 * 
	 * @param dir 被刪除目錄的檔案路徑
	 * @return 目錄刪除成功返回true,否則返回false
	 */
	public boolean deleteDirectory(String directorydir) {

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
	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();// "刪除單個檔案"+name+"成功！"
			return true;
		} // "刪除單個檔案"+name+"失敗！"
		return false;
	}
}
