package org.example.apigatewayprod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayProdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayProdApplication.class, args);
	}

}
