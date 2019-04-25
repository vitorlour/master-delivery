package br.com.masterdelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.service.UsuarioService;

@EntityScan(basePackages = { "br.com.masterdelivery.entity" })
@EnableJpaRepositories(basePackages = { "br.com.masterdelivery.repository" })
@ComponentScan(basePackages = { "br.com.masterdelivery" })
public class CrudTest implements CommandLineRunner {

	@Autowired
	private UsuarioService service;
	
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
		UsuarioDTO dto = new UsuarioDTO();

		dto.setEmail("vitorlour@hotmail.com");
		dto.setSenha("123456");
		
		service.realizarCadastro(dto);
	}

}
