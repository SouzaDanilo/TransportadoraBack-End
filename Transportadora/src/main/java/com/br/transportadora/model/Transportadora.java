package com.br.transportadora.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.ResponseEntity;


@Entity
@Table(name = "transportadoras")
public class Transportadora implements Serializable {
	
	  private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "id_transportadora")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "empresa")
	private String empresa;
	
	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "modal")
	private String modal;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "uf")
	private String uf;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Transportadora() {
	}
	
	public Transportadora(Long id,String nome,String empresa, String cnpj, String email,
			 String telefone,String modal,String rua,String bairro,
			 String numero,String cidade,String uf) {
		this.id = id;
		this.nome = nome;
		this.empresa = empresa;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.modal = modal;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	@Override
	public String toString() {
		return "Transportadora [id=" + id + ", nome=" + nome + ", empresa=" + empresa + ", cnpj=" + cnpj + ", email="
				+ email + ", telefone=" + telefone + ", modal=" + modal + ", rua=" + rua + ", bairro=" + bairro
				+ ", numero=" + numero + ", cidade=" + cidade + ", uf=" + uf + "]";
	}

}
