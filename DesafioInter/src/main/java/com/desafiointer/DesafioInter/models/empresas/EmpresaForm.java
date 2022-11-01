package com.desafiointer.DesafioInter.models.empresas;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmpresaForm {

    @NotNull
    @NotEmpty
    private String acao;

    @NotNull
    @NotEmpty
    private String ticker;

    @NotNull
    @Min(1)
    private double preco;

    @NotNull
    private boolean Status;


    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Empresa toEntity() {
        return new Empresa(this.getAcao(), this.getTicker(), this.getPreco(), this.getStatus());
    }
}
