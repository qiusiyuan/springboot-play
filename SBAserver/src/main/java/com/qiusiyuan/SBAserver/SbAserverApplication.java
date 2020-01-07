package com.qiusiyuan.SBAserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SbAserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbAserverApplication.class, args);
	}

}
