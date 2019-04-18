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
	
	protected void configure(MapperFactory factory) {
		factory.classMap(Usuario.class, UsuarioDTO.class)
			.field("email", "email")
			.field("senha", "senha")
			.byDefault()
			.register();
	}
}
