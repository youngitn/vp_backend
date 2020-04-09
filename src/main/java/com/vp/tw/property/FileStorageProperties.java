package com.vp.tw.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String excelDir;
	private String pdfDir;
	private String pdfZipDir;

}