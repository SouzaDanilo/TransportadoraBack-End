package com.br.transportadora.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.transportadora.exceptions.ErroException;
import com.br.transportadora.model.Transportadora;
import com.br.transportadora.service.TransportadoraService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {
	
	@Autowired
    private TransportadoraService transportadoraService;

	
	/**
	 * CADASTRAR TRANSPORTADORAS
	 */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transportadora> cadastrarTransportadora(@RequestBody @Valid Transportadora body) {
    	
    	if(body.getId() != null) { 		
    		throw new ErroException("Requisição não consistente");
    		
    	}
    	    	
    	final Transportadora response = transportadoraService.salvar(body);
    	
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(body.getId()).toUri();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        
        return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);    
    }  

	/**
	 * ATUALIZAR TRANSPORTADORAS
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Transportadora> atualizarTransportadora(@RequestBody Transportadora body, @PathVariable Long id) {
    	
    	if(body.getId() == null || !id.equals(body.getId())) {  		
    		throw new ErroException("Requisição não consistente");
    		
    	}
    	
    	body.setId(id);
    	final Transportadora response = transportadoraService.salvar(body);
    	
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
	 * DELETAR TRANSPORTADORAS
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarTransportadora(@PathVariable Long id) {
    	
    	transportadoraService.deletar(id);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    /**
	 * BUSCAR PELO ID
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transportadora> recuperarTransporadora(@PathVariable Long id) {
    	
    	final Transportadora response = transportadoraService.findById(id);
    	
    	return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    
    /**
	 * BUSCAR TODAS TRANSPORTADORAS
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transportadora>> listarTransportadora(@RequestParam (value = "nome", required = false) String nome,
    		@RequestParam (value = "uf", required = false) String uf,
    		@RequestParam (value = "cidade", required = false) String cidade,
    		@RequestParam (value = "modal", required = false) String modal) {
    	
    	List<Transportadora> listResponse = null;
    	
    	if(StringUtils.hasText(nome) || StringUtils.hasText(uf) || StringUtils.hasText(cidade) || StringUtils.hasText(modal)) {
    		
    		listResponse = transportadoraService.findByFilter(nome, uf, cidade, modal);
    	} else {
    		
    		listResponse = transportadoraService.findAll();
    	}
    	
        return new ResponseEntity<>(listResponse, HttpStatus.OK); 
    }
    
    /**
	 * LISTAR TODAS AS CIDADES EM CANTO DA TELA
	 */
    @RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listarCidades() {
    	
        List<Transportadora> list = transportadoraService.findAll();
        
        List<String> citeis = list.stream().map(obj -> obj.getCidade()).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(citeis);
    }
    
    /**
	 * LISTAR TODAS AS UFS EM CANTO DA TELA
	 */
    @RequestMapping(value = "/ufs", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listarUfs() {
    	
        List<Transportadora> list = transportadoraService.findAll();
        
        List<String> ufs = list.stream().map(obj -> obj.getUf()).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(ufs);
    }
    
    /**
	 * LISTAR TODAS AS MODAIS EM CANTO DA TELA
	 */
    @RequestMapping(value = "/modais", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listarModais() {
    	
        List<Transportadora> list = transportadoraService.findAll();
        
        List<String> modals = list.stream().map(obj -> obj.getModal()).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(modals);
    }
    
}
