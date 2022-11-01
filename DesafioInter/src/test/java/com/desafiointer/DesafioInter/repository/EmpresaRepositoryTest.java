package com.desafiointer.DesafioInter.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.desafiointer.DesafioInter.models.empresas.Empresa;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;	
	
	@Autowired
	private TestEntityManager em;
	

	
	@Test
	void deviaLocalizarAoBuscarPorTicker() {
		
		String ticker = "BIDI41";
		
		Empresa empresaInter = new Empresa();
		empresaInter.setAcao("INTER");
		empresaInter.setPreco(52.2);
		empresaInter.setTicker(ticker);
		empresaInter.setStatus(true);
		em.persist(empresaInter);
		
		Optional<Empresa> empresa = empresaRepository.findByTicker(ticker);
		Assertions.assertNotNull(empresa,"BIDI11 NÃO LOCALIZADO");
		Empresa obj = empresa.get();	
		String _ticker = obj.getTicker();
		
		Assertions.assertEquals(_ticker,ticker);
	}
	
	@Test
	void deviaNAOLocalizarAoBuscarPorTicker() {
		Optional<Empresa> empresa = empresaRepository.findByTicker("BIDI4");
		Assertions.assertTrue(!empresa.isPresent(),"BIDI4 LOCALIZADO DE MANEIRA ERRADA");

	}

}
