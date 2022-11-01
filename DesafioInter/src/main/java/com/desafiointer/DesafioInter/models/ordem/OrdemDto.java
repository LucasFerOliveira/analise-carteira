package com.desafiointer.DesafioInter.models.ordem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.desafiointer.DesafioInter.models.empresas.EmpresaDto;

public class OrdemDto {

	private EmpresaDto empresa;		
	private int quantidade;
	private String valorOrdem;

	public EmpresaDto getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDto empresa) {
		this.empresa = empresa;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getValorOrdem() {
		return valorOrdem;
	}
	public void setValorOrdem(String valorOrdem) {
		this.valorOrdem = valorOrdem;
	}
	
	public List<OrdemDto> ListResponseOrdem(List<Ordem> listOrdens) {
		return listOrdens.stream().map(OrdemDto::new).collect(Collectors.toList());
	}

	public OrdemDto() {
	}

	private OrdemDto(Ordem ordem) {
		DecimalFormat df = new DecimalFormat("#,###.00");	
		
		this.empresa = new EmpresaDto(ordem.getEmpresa());	
		this.quantidade = ordem.getQuantidade();
		this.valorOrdem = df.format(ordem.getValorOrdem());
	}
}
