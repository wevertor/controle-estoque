package br.com.wlucas.controleestoque.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

	private static final long serialVersionUID = -6712676120145805396L;

	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	public ProdutoDTO(Long id, String descricao, Date dataCadastro, Double valor, Integer entrada) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	
	public ProdutoDTO() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
