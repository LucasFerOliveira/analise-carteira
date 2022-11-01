package com.desafiointer.DesafioInter.models.investimento;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class InvestimentoForm {

    @NotNull
    @Min(1)
    private double valor;

    @NotNull
    @Min(1)
    private long quantidadeEmpresas;

    @NotNull
    @NotEmpty
    @CPF
    private String cpf;


    public InvestimentoForm(double valor, long quantidadeEmpresas, String cpf) {
        this.valor = valor;
        this.quantidadeEmpresas = quantidadeEmpresas;
        this.cpf = cpf;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public long getQuantidadeEmpresas() {
        return quantidadeEmpresas;
    }


    public void setQuantidadeEmpresas(long quantidadeEmpresas) {
        this.quantidadeEmpresas = quantidadeEmpresas;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
