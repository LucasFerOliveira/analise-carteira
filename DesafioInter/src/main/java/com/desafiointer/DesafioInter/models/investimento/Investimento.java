package com.desafiointer.DesafioInter.models.investimento;

import java.util.List;

import com.desafiointer.DesafioInter.models.ordem.OrdemDto;

public class Investimento {

    public Investimento(List<OrdemDto> ordem, String valorInvestido, String valorReal, String troco, String cpf, String mensagemRetorno) {
        this.ordem = ordem;
        this.valorInvestido = valorInvestido;
        this.valorReal = valorReal;
        this.troco = troco;
        this.cpf = cpf;
        MensagemRetorno = mensagemRetorno;
    }

    private List<OrdemDto> ordem;
    private String valorInvestido;
    private String valorReal;
    private String troco;
    private String cpf;
    private String MensagemRetorno;

    public String getMensagemRetorno() {
        return MensagemRetorno;
    }


    public void setMensagemRetorno(String mensagemRetorno) {
        MensagemRetorno = mensagemRetorno;
    }


    public Investimento() { }

    public List<OrdemDto> getOrdem() {
        return ordem;
    }


    public void setOrdem(List<OrdemDto> ordem) {
        this.ordem = ordem;
    }


    public String getValorInvestido() {
        return valorInvestido;
    }


    public void setValorInvestido(String valorInvestido) {
        this.valorInvestido = valorInvestido;
    }


    public String getValorReal() {
        return valorReal;
    }


    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }


    public String getTroco() {
        return troco;
    }


    public void setTroco(String troco) {
        this.troco = troco;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
