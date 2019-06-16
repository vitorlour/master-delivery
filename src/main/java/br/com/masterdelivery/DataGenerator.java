package br.com.masterdelivery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.entity.Corrida;
import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.entity.Plataforma;
import br.com.masterdelivery.enu.StatusCorridaEnum;
import br.com.masterdelivery.repository.CorridaRepository;
import br.com.masterdelivery.repository.EntregaRepository;
import br.com.masterdelivery.repository.PlataformaRepository;

@Component
public class DataGenerator {
		
	@Autowired
	private EntregaRepository repo;
	
	@Autowired
	private PlataformaRepository repopla;
	
	@Autowired
	private CorridaRepository corridaRepo;
	
	
	
	public void geraMassaCorrida() {
		
		for (int i = 0; i < 20; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.PRONTO_PARA_COLETA.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		for (int i = 0; i < 8; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.COLETADO_A_CAMINHO_DA_ENTREGA.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		for (int i = 0; i < 4; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.ENTREGUE.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		for (int i = 0; i < 2; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.PROBLEMA_COLETA.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		for (int i = 0; i < 1; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.PROBLEMA_COM_O_USUARIO.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		
		for (int i = 0; i < 6; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.PROBLEMA_NA_ENTREGA.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
		
		for (int i = 0; i < 1; i++) {
			Corrida corrida = new Corrida();
			corrida.setStatusCorrida(StatusCorridaEnum.PROBLEMA_A_CAMINHO_DA_ENTREGA.status());
			corridaRepo.saveAndFlush(corrida);
		}
		
	}
	
	@Transactional
	public void gerarMassaEntrega() throws ParseException {
		String janeiro = "01/01/2019";
		String fevereiro = "01/02/2019";
		String marco = "01/03/2019";
		String abril = "01/04/2019";
		String maio = "01/05/2019";
		String junho = "01/06/2019";
		String julho = "01/07/2019";
		String agosto = "01/08/2019";
		String setembro = "01/09/2019";
		String outubro = "01/10/2019";
		String novembro = "01/11/2019";
		String dezembro = "01/12/2019";
		
		Optional<Plataforma> ifood = repopla.findById(1L);
		
		Optional<Plataforma> rappi = repopla.findById(2L);
		
		Optional<Plataforma> uber = repopla.findById(3L);


		
		for (int i = 1; i < 42; i++) {
			Entrega entrega = new Entrega();
			entrega.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(janeiro));
			entrega.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega);
			
			Entrega entrega2 = new Entrega();
			entrega2.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(fevereiro));
			entrega2.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega2);
			
			Entrega entrega3 = new Entrega();
			entrega3.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(marco));
			entrega3.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega3);
			
			
			Entrega entrega4 = new Entrega();
			entrega4.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(abril));
			entrega4.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega4);
			
			
			Entrega entrega5 = new Entrega();
			entrega5.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(maio));
			entrega5.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega5);
			
			Entrega entrega6 = new Entrega();
			entrega6.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(junho));
			entrega6.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega6);
			
			Entrega entrega11 = new Entrega();
			entrega11.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(novembro));
			entrega11.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega11);
			
			Entrega entrega12 = new Entrega();
			entrega12.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(dezembro));
			entrega12.addPlataforma(ifood.get());
			repo.saveAndFlush(entrega12);
		}
		
		

		for (int i = 1; i < 34; i++) {
			Entrega entrega = new Entrega();
			entrega.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(janeiro));
			entrega.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega);
			
			Entrega entrega2 = new Entrega();
			entrega2.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(fevereiro));
			entrega2.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega2);
			
			Entrega entrega3 = new Entrega();
			entrega3.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(abril));
			entrega3.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega3);
			
			
			Entrega entrega4 = new Entrega();
			entrega4.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(marco));
			entrega4.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega4);
			
			
			Entrega entrega5 = new Entrega();
			entrega5.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(maio));
			entrega5.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega5);
			
			
			Entrega entrega8 = new Entrega();
			entrega8.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(agosto));
			entrega8.addPlataforma(rappi.get());
			repo.saveAndFlush(entrega8);
		}
		
		for (int i = 1; i < 23; i++) {
			Entrega entrega = new Entrega();
			entrega.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(janeiro));
			entrega.addPlataforma(uber.get());
			repo.saveAndFlush(entrega);
			
			
			Entrega entregaa = new Entrega();
			entregaa.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(janeiro));
			entregaa.addPlataforma(uber.get());
			repo.saveAndFlush(entregaa);
			
			
			Entrega entrega2 = new Entrega();
			entrega2.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(fevereiro));
			entrega2.addPlataforma(uber.get());
			repo.saveAndFlush(entrega2);
			
			Entrega entrega3 = new Entrega();
			entrega3.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(abril));
			entrega3.addPlataforma(uber.get());
			repo.saveAndFlush(entrega3);
			
			Entrega entrega33 = new Entrega();
			entrega33.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(abril));
			entrega33.addPlataforma(uber.get());
			repo.saveAndFlush(entrega33);
			
			
			Entrega entrega4 = new Entrega();
			entrega4.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(marco));
			entrega4.addPlataforma(uber.get());
			repo.saveAndFlush(entrega4);
			
			
			Entrega entrega5 = new Entrega();
			entrega5.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(maio));
			entrega5.addPlataforma(uber.get());
			repo.saveAndFlush(entrega5);
			
			Entrega entrega7 = new Entrega();
			entrega7.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(julho));
			entrega7.addPlataforma(uber.get());
			repo.saveAndFlush(entrega7);
			
			
			Entrega entrega8 = new Entrega();
			entrega8.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(agosto));
			entrega8.addPlataforma(uber.get());
			repo.saveAndFlush(entrega8);
			
			
			Entrega entrega10 = new Entrega();
			entrega10.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(outubro));
			entrega10.addPlataforma(uber.get());
			repo.saveAndFlush(entrega10);
			
			
			Entrega entrega100 = new Entrega();
			entrega100.setDataEntrega(new SimpleDateFormat("dd/MM/yyyy").parse(outubro));
			entrega100.addPlataforma(uber.get());
			repo.saveAndFlush(entrega100);
		}
	}
}
