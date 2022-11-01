package com.desafiointer.DesafioInter.models.investimento;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class InvestimentoFormTest {

	@Test
	void testeValidarGetAndConstrutor() {
		Double valor = 60.5;
	    Long quantidadeEmpresas = (long) 10;
	    String cpf = "000.000.000-00";
	    
	    InvestimentoForm investimentoForm = new InvestimentoForm(valor,quantidadeEmpresas,cpf);
	    
		Assertions.assertEquals(valor,investimentoForm.getValor());
		Assertions.assertEquals(quantidadeEmpresas,investimentoForm.getQuantidadeEmpresas());
		Assertions.assertEquals(cpf,investimentoForm.getCpf());	
		

		
	}

}
