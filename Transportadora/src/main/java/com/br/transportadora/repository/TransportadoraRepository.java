package com.br.transportadora.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.transportadora.model.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

	List<Transportadora> findAll(Specification<Transportadora> completeQuery);
	
}
