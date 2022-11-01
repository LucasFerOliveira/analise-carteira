package com.desafiointer.DesafioInter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiointer.DesafioInter.models.empresas.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Long> 
{
	Optional<Empresa> findByTicker(String ticker);
	List<Empresa> findAllByStatus(Boolean status);
}
