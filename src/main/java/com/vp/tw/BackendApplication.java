package com.vp.tw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.vp.tw.property.FileStorageProperties;
//這邊要注意 所有class最好給個@service @controller之類的定義
//例如util相關就給個@Component 以免spring抓不到
@SpringBootApplication(scanBasePackages = {"com.vp.tw"})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
