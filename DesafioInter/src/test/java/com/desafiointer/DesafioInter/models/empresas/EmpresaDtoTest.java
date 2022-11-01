package com.desafiointer.DesafioInter.models.empresas;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmpresaDtoTest {

	@Test
	void testeValidarGetAndSet() {
		DecimalFormat df = new DecimalFormat("#.##");
		String preco = df.format(60.5);
	    String acao = "INTER";
	    String ticker = "BIDI4";
		

		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setAcao(acao);
		empresaDto.setPreco(preco);
		empresaDto.setTicker(ticker);
		
		
		Assertions.assertEquals(preco,empresaDto.getPreco());
		Assertions.assertEquals(acao,empresaDto.getAcao());
		Assertions.assertEquals(ticker,empresaDto.getTicker());		
	}
	
	@Test
	void testeValidarContrutor() {
		DecimalFormat df = new DecimalFormat("#.##");
		String preco = df.format(60.5);
	    String acao = "INTER";
	    String ticker = "BIDI4";
	    
	    EmpresaDto empresaDto = new EmpresaDto(acao,ticker,preco);
		
		Assertions.assertEquals(preco,empresaDto.getPreco());
		Assertions.assertEquals(acao,empresaDto.getAcao());
		Assertions.assertEquals(ticker,empresaDto.getTicker());	
	}
	
	@Test
	void testeValidacaoConvercaoEmpresaToEmpresaDto() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);

		Empresa empresa = new Empresa("INTER","BIDI4",60.50,true);
		EmpresaDto empresaDto = new EmpresaDto(empresa);
		
		Assertions.assertEquals(df.format(empresa.getPreco()),empresaDto.getPreco());
		Assertions.assertEquals(empresa.getAcao(),empresaDto.getAcao());
		Assertions.assertEquals(empresa.getTicker(),empresaDto.getTicker());		
	}
	
	@Test
	void testeValidacaoConvercaoListEmpresaToListEmpresaDto() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		Empresa empresa = new Empresa("INTER","BIDI4",60.50,true);
		Empresa empresa2 = new Empresa("INTER","BIDI11",48.2,true);
		Empresa empresa3 = new Empresa("OI","OIBR3",1,true);
		Empresa empresa4 = new Empresa("OI","OIBR4",2.5,false);
		List<Empresa> listEmpresa = new ArrayList<>();	
		listEmpresa.add(empresa);
		listEmpresa.add(empresa2);
		listEmpresa.add(empresa3);
		listEmpresa.add(empresa4);

		List<EmpresaDto> listEmpresaDto = new EmpresaDto().converterList(listEmpresa);
		
		Assertions.assertEquals(listEmpresa.size(),listEmpresaDto.size());
		
		for (int i = 0; i < listEmpresaDto.size(); i++) {
		
			Assertions.assertEquals(listEmpresaDto.get(i).getPreco(),df.format(listEmpresa.get(i).getPreco()));			
			Assertions.assertEquals(listEmpresa.get(i).getAcao(),listEmpresaDto.get(i).getAcao());
			Assertions.assertEquals(listEmpresa.get(i).getTicker(),listEmpresaDto.get(i).getTicker());	
		}
	
	}


}
