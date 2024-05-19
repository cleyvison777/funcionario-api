package com.funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class FuncionarioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioApiApplication.class, args);
	}

}
