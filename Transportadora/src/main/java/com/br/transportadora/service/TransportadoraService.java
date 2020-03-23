package com.br.transportadora.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.transportadora.exceptions.ErroException;
import com.br.transportadora.model.Transportadora;
import com.br.transportadora.repository.TransportadoraRepository;

@Service
public class TransportadoraService {
	
	@Autowired
    private TransportadoraRepository transportadoraRepository;
	
	
	/**
	 * METODO RESPONSAVEL POR BUSCAR PELO ID
	 */
    public Transportadora findById(Long id) {
    	
        Optional<Transportadora> obj = transportadoraRepository.findById(id);
        
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id, "Tipo: " + Transportadora.class.getName()));
        
    }
    
    /**
	 * METODO RESPONSAVEL POR ATUALIZAR E CADASTRAR
	 */
    public Transportadora salvar(Transportadora body) {
    	
    	if(body.getId() != null && transportadoraRepository.findById(body.getId()) == null ) {

    		throw new ErroException("Tentativa de atualizar um registro não existente");
    	}
    	
    	validarCamposObrigatorios(body);

    	return transportadoraRepository.save(body);
    }
    

    /**
	 * METODO RESPONSAVEL POR DELETAR
	 */
    public void deletar(Long id) {
    	
        try {
        	transportadoraRepository.deleteById(id);
        }
        catch (final EmptyResultDataAccessException e) {
        	
            throw new ErroException("Tentativa de remover um registro não existente");
        }
        
    }

    /**
	 * METODO RESPONSAVEL POR BUSCAR TODAS TRANSPORTADORAS
	 */
    public List<Transportadora> findAll() {
    	
        return transportadoraRepository.findAll();
    }
    
    
    /**
	 * METODO RESPONSAVEL POR FAZER O FILTRO DOS PARAMETROS
	 */
    public List<Transportadora> findByFilter(String nome, String uf, String cidade, String modal){
    	
    	final List <Specification<Transportadora>> specifications = new ArrayList<>();

    	if(StringUtils.hasText(nome)) {

    		specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome));
    	}
    	
    	if(StringUtils.hasText(uf)) {
    		
    		specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("uf")), uf));
    	}
    	
    	if(StringUtils.hasText(cidade)) {

    		specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("cidade")), cidade));
    	}

    	if(StringUtils.hasText(modal)) {

    		specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("modal")), modal));
    	}
    	
    	Specification<Transportadora> completeQuery = null;
    	
    	for(final Specification<Transportadora> specification : specifications) {
    		
    		if(completeQuery == null) {
    			
    			completeQuery = specification;
    		} else {
    			
    			completeQuery = Specification.where(completeQuery).and(specification);
    		}
    	}
		return transportadoraRepository.findAll(completeQuery);
    	
    }
    
    
    
    /**
	 * METODO RESPONSAVEL POR VALIDAR CAMPOS OBRIGATORIOS
	 */
    public void validarCamposObrigatorios(Transportadora transportadora) {
    	  	
    	if(StringUtils.isEmpty(transportadora.getNome()) || transportadora.getNome().length() < 4) {
    		
    		throw new ErroException(" campo nome esta null ou é menor que 4 caracteres");
    		
    	}

    	if(StringUtils.isEmpty(transportadora.getEmpresa())) {
    		
    		throw new ErroException(" campo empresa esta null");

    	}
    	
    	if(StringUtils.isEmpty(transportadora.getEmail())) {

    		throw new ErroException("campo nome esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getTelefone())) {

    		throw new ErroException("campo telefone esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getModal())) {

    		throw new ErroException("campo modal esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getRua())) {

    		throw new ErroException("campo rua esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getBairro())) {

    		throw new ErroException("campo bairro esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getNumero())) {

    		throw new ErroException("campo numero esta null");
    	}

    	if(StringUtils.isEmpty(transportadora.getCidade())) {

    		throw new ErroException("campo cidade esta null");
    	}
    	
    	if(StringUtils.isEmpty(transportadora.getUf())) {

    		throw new ErroException("campo uf esta null");
    	}


    }

}
