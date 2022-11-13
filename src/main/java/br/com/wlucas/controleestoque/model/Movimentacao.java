package br.com.wlucas.controleestoque.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que mapeia a tabela movimentacao.
 * @author Weverton Trindade, 10/11/2022
 *
 */
@Entity
@Table(name = "produto")
public class Movimentacao implements Serializable {

	private static final long serialVersionUID = 279642962127298568L;

	@Id
	private Long id;
	
	private Integer tipo;
	
	private Date data;
	
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
