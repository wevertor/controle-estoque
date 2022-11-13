package br.com.wlucas.controleestoque.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que mapeia a tabela produto.
 * @author Weverton Trindade, 10/11/2022
 *
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 3915543617440949751L;

	@Id
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	@OneToMany(mappedBy = "produto")
	private List<Movimentacao> movimentacoes;

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

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
}
