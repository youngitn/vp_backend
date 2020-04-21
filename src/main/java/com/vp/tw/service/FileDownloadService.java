package com.vp.tw.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileDownloadService {

	public ResponseEntity<Resource> getFileResponseEntity(String fileName, HttpServletRequest request);
}
