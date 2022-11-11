package br.com.wlucas.controleestoque.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

	private static final long serialVersionUID = -6712676120145805396L;

	private Long id;
	
	private String descricao;
	
	private LocalDate dataCadastro;
	
	private Double valor;
	
	private Integer entrada;
	
	public ProdutoDTO(Long id, String descricao, LocalDate dataCadastro, Double valor, Integer entrada) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.valor = valor;
		this.entrada = entrada;
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
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getEntrada() {
		return entrada;
	}
	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}
}
