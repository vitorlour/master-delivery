package br.com.masterdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.masterdelivery.entity" })
@EnableJpaRepositories(basePackages = { "br.com.masterdelivery.repository" })
@ComponentScan(basePackages = {"br.com.masterdelivery"})
public class MasterDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterDeliveryApplication.class, args);
	}

}
