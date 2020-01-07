package com.PTS3S36G3.LobbyService.Logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LobbyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobbyServiceApplication.class, args);
	}

}
