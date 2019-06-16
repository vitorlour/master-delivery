package br.com.masterdelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.repository.EntregaRepository;
import br.com.masterdelivery.service.UsuarioService;

@EntityScan(basePackages = { "br.com.masterdelivery.entity" })
@EnableJpaRepositories(basePackages = { "br.com.masterdelivery.repository" })
@ComponentScan(basePackages = { "br.com.masterdelivery" })
public class CrudTest implements CommandLineRunner {

	@Autowired
	private EntregaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudTest.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		try {
			saveDataDTO();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private void saveDataDTO() {
		
		
	}

}
