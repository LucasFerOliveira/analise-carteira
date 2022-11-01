package com.desafiointer.DesafioInter.resource;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiointer.DesafioInter.models.empresas.Empresa;
import com.desafiointer.DesafioInter.models.investimento.Investimento;
import com.desafiointer.DesafioInter.models.investimento.InvestimentoForm;
import com.desafiointer.DesafioInter.models.ordem.Ordem;
import com.desafiointer.DesafioInter.models.ordem.OrdemDto;
import com.desafiointer.DesafioInter.repository.EmpresaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/investimento")
@Api(value = "API REST Investimento")
@CrossOrigin(origins = "*")
public class InvestimentoResource {
    @Autowired
    EmpresaRepository empresaRepository;

    @PostMapping()
    @ApiOperation(value = "Realiza os investimentos nas acões ativas aleatorias, usuario escolhe o valor e a quantide maxima de ações")
    public ResponseEntity<Investimento> realizarInvestimento(@RequestBody @Valid InvestimentoForm investimentoForm) throws Exception {
        Investimento resultado = new Investimento();

        List<Empresa> empresas = empresaRepository.findAllByStatus(true);

        if (empresas == null || empresas.isEmpty()) {
            resultado.setMensagemRetorno("Nenhuma empresa ativa localizada no banco de dados");
            return new ResponseEntity<Investimento>(resultado, HttpStatus.PRECONDITION_FAILED);
        }

        Collections.shuffle(empresas);

        empresas = empresas.stream().limit(investimentoForm.getQuantidadeEmpresas()).collect(Collectors.toList());

        List<Ordem> listOrdens = new Ordem().GetListaOrdemByListEmpresas(empresas);

        int loopSemValores = 0;
        double valorTotalInvestido = 0;

        while (investimentoForm.getValor() >= valorTotalInvestido && (loopSemValores < (empresas.size()))) {
            listOrdens = ordenaValores(listOrdens);

            int indexAtual = loopSemValores;
            Ordem ordem = listOrdens.get(indexAtual);
            double valorUnitarioAtual = listOrdens.get(indexAtual).getEmpresa().getPreco();
            double novoValorTotalInvestido = valorTotalInvestido + valorUnitarioAtual;


            if (novoValorTotalInvestido <= investimentoForm.getValor()) {
                int novaQuantidade = ordem.getQuantidade() + 1;
                listOrdens.get(indexAtual).setQuantidade(novaQuantidade);
                listOrdens.get(indexAtual).setValorOrdem(valorUnitarioAtual * novaQuantidade);
                valorTotalInvestido = novoValorTotalInvestido;

                loopSemValores = 0;
            } else {
                loopSemValores++;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        resultado = new Investimento(new OrdemDto().ListResponseOrdem(listOrdens), df.format(investimentoForm.getValor()), df.format(valorTotalInvestido), df.format(investimentoForm.getValor() - valorTotalInvestido), investimentoForm.getCpf(), "Investimento diversificado com sucesso!");

        return new ResponseEntity<Investimento>(resultado, HttpStatus.OK);
    }

    private List<Ordem> ordenaValores(List<Ordem> listOrdens) {
        Collections.sort(listOrdens, new Comparator<Ordem>() {
            @Override
            public int compare(Ordem c1, Ordem c2) {
                double valorTotalC1 = c1.getEmpresa().getPreco() * c1.getQuantidade();
                double valorTotalC2 = c2.getEmpresa().getPreco() * c2.getQuantidade();
                return Double.compare(valorTotalC1, valorTotalC2);
            }
        });
        return listOrdens;
    }
}
