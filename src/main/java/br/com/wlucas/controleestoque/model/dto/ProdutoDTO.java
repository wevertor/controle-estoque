package br.com.wlucas.controleestoque.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = -6712676120145805396L;

	private Long id;

	private String descricao;

	private Double valor;

	/* quantidade total do produto no estoque. Não é informado no cadastro. */
	private Integer quantidadeTotal;

	public ProdutoDTO(Long id, String descricao, Date dataCadastro, Double valor, Integer total) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidadeTotal = total;
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

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
}
