package com.desafiointer.DesafioInter.models.empresas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmpresaTest {

	@Test
	void testeValidarGetAndSet() {
		Double preco = 60.5;
	    String acao = "INTER";
	    String ticker = "BIDI4";
	    Long id = (long) 10;
	    Boolean status = true;
	    
	    Empresa empresa = new Empresa();
	    empresa.setAcao(acao);
	    empresa.setPreco(preco);
	    empresa.setTicker(ticker);
	    empresa.setId(id);
	    empresa.setStatus(status);
	    
		Assertions.assertEquals(preco,empresa.getPreco());
		Assertions.assertEquals(acao,empresa.getAcao());
		Assertions.assertEquals(ticker,empresa.getTicker());	
		Assertions.assertEquals(id,empresa.getId());		
		Assertions.assertEquals(status,empresa.getStatus());		

		
	}
	


}
