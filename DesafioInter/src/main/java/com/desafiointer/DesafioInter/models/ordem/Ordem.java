package com.desafiointer.DesafioInter.models.ordem;




import java.util.ArrayList;
import java.util.List;

import com.desafiointer.DesafioInter.models.empresas.Empresa;


public class Ordem {		
	private Empresa empresa;
	private int quantidade;
	private double valorOrdem;

	public Ordem() {}

	public Ordem(Empresa empresa, int quantidade, double valorOrdem) {
		super();
		this.empresa = empresa;
		this.quantidade = quantidade;
		this.valorOrdem = valorOrdem;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorOrdem() {
		return valorOrdem;
	}
	public void setValorOrdem(double valorOrdem) {
		this.valorOrdem = valorOrdem;
	}
	
	public List<Ordem> GetListaOrdemByListEmpresas(List<Empresa> empresas) {
		List<Ordem> listOrdens = new ArrayList<Ordem>();
		for (Empresa item : empresas) {
			Ordem newOrdem = new Ordem(item,0,item.getPreco());	
			listOrdens.add(newOrdem);
		}
		return listOrdens;
	}
}
