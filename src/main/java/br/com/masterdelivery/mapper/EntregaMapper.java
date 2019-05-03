/**
 * 
 */
package br.com.masterdelivery.mapper;

import org.springframework.stereotype.Component;

import br.com.masterdelivery.dto.EntregaDTO;
import br.com.masterdelivery.entity.Entrega;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @author vitorlour
 *
 */
@Component
public class EntregaMapper extends ConfigurableMapper {
	
	private static final String PLATAFORMA = "plataforma";
	private static final String DATA_ENTREGA = "dataEntrega";
	private static final String DURACAO_CORRIDA = "duracaoCorrida";
	private static final String VALOR_GORJETA = "valorGorjeta";
	private static final String VALOR_ENTREGA = "valorEntrega";

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Entrega.class, EntregaDTO.class)
			.field(VALOR_ENTREGA, VALOR_ENTREGA)
			.field(VALOR_GORJETA, VALOR_GORJETA)
			.field(DURACAO_CORRIDA, DURACAO_CORRIDA)
			.field(DATA_ENTREGA, DATA_ENTREGA)
			.field(PLATAFORMA, PLATAFORMA)
			.byDefault()
			.register();
	}

}
