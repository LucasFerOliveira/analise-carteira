package com.desafiointer.DesafioInter.models.empresas;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmpresaFormTest {

	@Test
	void testeValidarGetAndSet() {
		Double preco = 60.5;
	    String acao = "INTER";
	    String ticker = "BIDI4";
		

	    EmpresaForm empresaForm = new EmpresaForm();
	    empresaForm.setAcao(acao);
	    empresaForm.setPreco(preco);
	    empresaForm.setTicker(ticker);
		
		
		Assertions.assertEquals(preco,empresaForm.getPreco());
		Assertions.assertEquals(acao,empresaForm.getAcao());
		Assertions.assertEquals(ticker,empresaForm.getTicker());		
	}
	
	@Test
	void testeConverterEmpresaFormToEmpresa() {
		Double preco = 60.5;
	    String acao = "INTER";
	    String ticker = "BIDI4";
		

	    EmpresaForm empresaForm = new EmpresaForm();
	    empresaForm.setAcao(acao);
	    empresaForm.setPreco(preco);
	    empresaForm.setTicker(ticker);
	    
	    Empresa empresa = empresaForm.converter();
		
		
		Assertions.assertEquals(empresa.getPreco(),empresaForm.getPreco());
		Assertions.assertEquals(empresa.getAcao(),empresaForm.getAcao());
		Assertions.assertEquals(empresa.getTicker(),empresaForm.getTicker());		
	}

}
