package com.desafiointer.DesafioInter.resource;

import com.desafiointer.DesafioInter.models.empresas.EmpresaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.desafiointer.DesafioInter.models.empresas.Empresa;
import com.desafiointer.DesafioInter.models.empresas.EmpresaForm;
import com.desafiointer.DesafioInter.repository.EmpresaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CONFLICT;


@RestController
@RequestMapping
@Api(value = "API REST Empresas")
@CrossOrigin(origins = "*")
public class EmpresaResource {

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("/empresa")
    @ApiOperation(value = "Retorna todos as empresas(ativas e inativas). Por padrão retorna a primeira pagina de 10 registros")
    @Cacheable(value = "listaEmpresas")
    public ResponseEntity<Page<Empresa>> listaEmpresas(@PageableDefault(sort = "acao", direction = ASC, page = 0, size = 10) Pageable paginacao) {

        Page<Empresa> pageEmpresas = empresaRepository.findAll(paginacao);

        return new ResponseEntity<Page<Empresa>>(pageEmpresas, HttpStatus.OK);
    }

    @GetMapping("/empresaByStatus/{status}")
    @ApiOperation(value = "Retorna uma lista de todas as empresas ativas ou inativas")
    @Cacheable(value = "listaTodasEmpresas")
    public ResponseEntity<List<Empresa>> listaTodasEmpresas(@PathVariable(value = "status") boolean status) {
        List<Empresa> empresas = empresaRepository.findAllByStatus(status);

        return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
    }

    @GetMapping("/empresa/{id}")
    @ApiOperation(value = "Retorna a empresa buscando pelo id")
    @Cacheable(value = "EmpresaById")
    public ResponseEntity<Empresa> empresaById(@PathVariable(value = "id") Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent())
            return new ResponseEntity<Empresa>(empresa.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Transactional
    @PostMapping("/empresa")
    @ApiOperation(value = "Cadastra uma empresa")
    @CacheEvict(value = {"EmpresaById", "listaEmpresas", "listaTodasEmpresas"}, allEntries = true)
    public ResponseEntity<Empresa> salvaEmpresa(@RequestBody @Valid EmpresaDto empresa) {

        Optional<Empresa> empresaTeste = empresaService.salvar(empresa);
        if(empresaTeste.isPresent())
            return new ResponseEntity<Empresa>(empresaSalva, HttpStatus.CREATED);

        return new ResponseEntity<>(CONFLICT);

        /*vai pra dentro
        Optional<Empresa> empresaByticker = empresaRepository.findByTicker(empresa.getTicker());
        if (empresaByticker.isPresent()) {
            return new ResponseEntity<>(CONFLICT);

        }
        Empresa empresaSalva = empresaRepository.save(empresa.converter());
        return new ResponseEntity<Empresa>(empresaSalva, HttpStatus.CREATED);*/
    }

    @Transactional
    @PutMapping("/empresaAlterarStatus/{id}/{status}")
    @ApiOperation(value = "Atualiza o status da empresa")
    @CacheEvict(value = {"EmpresaById", "listaEmpresas", "listaTodasEmpresas"}, allEntries = true)
    public ResponseEntity<Empresa> atualizarStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") boolean status) {
        Optional<Empresa> empresaOld = empresaRepository.findById(id);
        if (empresaOld.isPresent()) {
            empresaOld.get().setStatus(status);
            empresaRepository.save(empresaOld.get());
            return new ResponseEntity<Empresa>(empresaOld.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PutMapping("/empresa/{id}/{preco}")
    @ApiOperation(value = "Atualiza valor de uma empresa")
    @CacheEvict(value = {"EmpresaById", "listaEmpresas", "listaTodasEmpresas"}, allEntries = true)
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") Long id, @PathVariable("preco") double preco) {
        Optional<Empresa> empresaOld = empresaRepository.findById(id);
        if (empresaOld.isPresent()) {
            empresaOld.get().setPreco(preco);
            empresaRepository.save(empresaOld.get());
            return new ResponseEntity<>(empresaOld.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @DeleteMapping("/empresa/{id}")
    @ApiOperation(value = "Deletar uma empresa")
    @CacheEvict(value = {"EmpresaById", "listaEmpresas", "listaTodasEmpresas"}, allEntries = true)
    public ResponseEntity<String> deleteEmpresa(@PathVariable("id") Long id) {
        Optional<Empresa> empresaOld = empresaRepository.findById(id);
        if (empresaOld.isPresent()) {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>("Sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
	