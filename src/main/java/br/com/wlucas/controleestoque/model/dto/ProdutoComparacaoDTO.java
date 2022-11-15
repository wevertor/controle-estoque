package br.com.wlucas.controleestoque.model.dto;

import java.io.Serializable;
import java.util.Date;


public class ProdutoComparacaoDTO implements Serializable {

	private static final long serialVersionUID = -6712676120145805396L;

	private Long id;

	private String descricao;

	private Double valor;

	private Integer quantidadeEntradaTotal;
	
	private Integer quantidadeSaidaTotal;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	public ProdutoComparacaoDTO() {}

	public ProdutoComparacaoDTO(Long id, String descricao, Double valor, Integer quantidadeEntradaTotal,
			Integer quantidadeSaidaTotal) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidadeEntradaTotal = quantidadeEntradaTotal;
		this.quantidadeSaidaTotal = quantidadeSaidaTotal;
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

	public Integer getQuantidadeEntradaTotal() {
		return quantidadeEntradaTotal;
	}

	public void setQuantidadeEntradaTotal(Integer quantidadeEntradaTotal) {
		this.quantidadeEntradaTotal = quantidadeEntradaTotal;
	}

	public Integer getQuantidadeSaidaTotal() {
		return quantidadeSaidaTotal;
	}

	public void setQuantidadeSaidaTotal(Integer quantidadeSaidaTotal) {
		this.quantidadeSaidaTotal = quantidadeSaidaTotal;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	
}
