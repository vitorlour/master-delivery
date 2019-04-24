/**
 * 
 */
package br.com.masterdelivery.mapper;

import org.springframework.stereotype.Component;

import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @author vitorlour
 *
 */
@Component
public class UsuarioMapper extends ConfigurableMapper {
	
	private static final String SENHA = "senha";
	
	private static final String EMAIL = "email";

	protected void configure(MapperFactory factory) {
		factory.classMap(Usuario.class, UsuarioDTO.class)
			.field(EMAIL, EMAIL)
			.field(SENHA, SENHA)
			.byDefault()
			.register();
	}
}
