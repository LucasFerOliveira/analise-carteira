package com.desafiointer.DesafioInter.models.empresas;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class EmpresaDto {

    @NotNull
    @NotEmpty
    private String acao;

    @NotNull
    @NotEmpty
    private String ticker;

    @NotNull
    @Min(1)
    private String preco;

    @NotNull
    private boolean Status;

    public EmpresaDto(String acao, String ticker, String preco) {
        this.acao = acao;
        this.ticker = ticker;
        this.preco = preco;
    }

    public EmpresaDto() {
    }

    public EmpresaDto(Empresa empresa) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        this.acao = empresa.getAcao();
        this.ticker = empresa.getTicker();
        this.preco = df.format(empresa.getPreco());
    }

    public List<EmpresaDto> converterList(List<Empresa> empresa) {
    // verificar Stream
        List<EmpresaDto> listEmpresaDto = new ArrayList<EmpresaDto>();
        for (Empresa item : empresa) {
            listEmpresaDto.add(new EmpresaDto(item));
        }
        return listEmpresaDto;
    }


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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public Empresa converter() {
        return new Empresa(this.getAcao(), this.getTicker(), this.getPreco(), this.getStatus());
    }

}
