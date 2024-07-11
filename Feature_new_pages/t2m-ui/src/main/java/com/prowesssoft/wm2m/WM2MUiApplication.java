package com.prowesssoft.wm2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.prowesssoft.wm2m.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WM2MUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WM2MUiApplication.class, args);
	}

}
