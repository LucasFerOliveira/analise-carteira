package com.desafiointer.DesafioInter.models.investimento;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.desafiointer.DesafioInter.models.empresas.EmpresaDto;
import com.desafiointer.DesafioInter.models.ordem.OrdemDto;

class InvestimentoTest {

	@Test
	void testeConstrutor() {
		
		EmpresaDto empresa = new EmpresaDto("INTER","BIDI4","60.50");

		OrdemDto ordemDto = new OrdemDto();
		ordemDto.setEmpresa(empresa);
		ordemDto.setQuantidade(10);
		ordemDto.setValorOrdem("100");
		
		List<OrdemDto> listOrdem = new ArrayList();
		listOrdem.add(ordemDto);
		

		Investimento investimento = new Investimento(listOrdem,"1000","950","50","123456","sucesso");
	    
		Assertions.assertEquals(investimento.getCpf(),"123456");
		Assertions.assertEquals(investimento.getMensagemRetorno(),"sucesso");
		Assertions.assertEquals(investimento.getTroco(),"50");	
		Assertions.assertEquals(investimento.getValorInvestido(),"1000");	
		Assertions.assertEquals(investimento.getValorReal(),"950");	


		Assertions.assertEquals(investimento.getOrdem().get(0).getQuantidade(),10);	
		Assertions.assertEquals(investimento.getOrdem().get(0).getValorOrdem(),"100");
		
		
		Assertions.assertEquals(investimento.getOrdem().get(0).getEmpresa().getAcao(),"INTER");	
		Assertions.assertEquals(investimento.getOrdem().get(0).getEmpresa().getPreco(),"60.50");	
		Assertions.assertEquals(investimento.getOrdem().get(0).getEmpresa().getTicker(),"BIDI4");	

	}
	

}
