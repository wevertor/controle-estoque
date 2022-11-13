package br.com.wlucas.controleestoque.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe DTO de movimentacao.
 * @author Weverton Trindade, 10/11/2022
 *
 */
public class MovimentacaoDTO implements Serializable {

	private static final long serialVersionUID = 279642962127298568L;

	private Long id;
	
	private Integer tipo;
	
	private Date data;
	
	private Integer quantidade;
	
	private Long idProduto;

	public MovimentacaoDTO(Long id, Integer tipo, Date data, Integer quantidade, Long idProduto) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.data = data;
		this.quantidade = quantidade;
		this.idProduto = idProduto;
	}

	public MovimentacaoDTO() {
		super();
	}

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

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	

}
