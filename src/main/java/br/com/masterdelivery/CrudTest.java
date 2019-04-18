package br.com.masterdelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.service.UsuarioService;

@SpringBootApplication
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
			//saveData();
			saveDataDTO();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Transactional
	private void saveData() {
		Usuario user1 = new Usuario();

		user1.setEmail("vitorlour@hotmail.com");
		user1.setSenha("123456");
		user1.setTokenUsuario("8bca56e1-3f3b-4751-9093-5fca816ecbaa");
		
		//service.salvar(user1);
		
		//service.alterarSenha(user1, "GG");
		
		//service.recuperarSenha("vitorlour@hotmail.com");
		
		//service.excluirCadastro(user1);
	}
	
	@Transactional
	private void saveDataDTO() {
		UsuarioDTO dto = new UsuarioDTO();

		dto.setEmail("vitorlour@hotmail.com");
		dto.setSenha("123456");
		
		service.realizarCadastro(dto);
		
		service.alterarSenha(dto, "Test");
		
		//service.recuperarSenha("vitorlour@hotmail.com");
	}

}
