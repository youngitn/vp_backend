package com.vp.tw.service.imp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vp.tw.property.FileStorageProperties;
import com.vp.tw.service.FileDownloadService;
import com.vp.tw.util.FileStorageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileDownloadServiceImp implements FileDownloadService {

	@Autowired
	private FileStorageUtil fileStorageUtil;
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	public ResponseEntity<Resource> getFileResponseEntity(String fileName, HttpServletRequest request) {
		String path = fileStorageProperties.getExcelDir();

		// 判斷檔案類型 區分來源路徑
		if (fileName.contains("pdf")) {
			path = fileStorageProperties.getPdfDir();
		} else if (fileName.contains("zip")) {
			path = fileStorageProperties.getPdfZipDir();
		}
		Resource resource = fileStorageUtil.loadFileAsResource(path + fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
