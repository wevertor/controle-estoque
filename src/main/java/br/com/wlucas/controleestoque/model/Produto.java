package br.com.wlucas.controleestoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que mapeia a tabela produto.
 * 
 * @author Weverton Trindade, 10/11/2022
 *
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 3915543617440949751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private Double valor;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
	private List<Movimentacao> movimentacoes;

	/**
	 * Guarda a informação do total em estoque do produto, calculado a partir da
	 * lista de movimentações.
	 */
	@Transient
	private Integer quantidadeTotal;

	/**
	 * Calcula a quantidade total de saídas do produto de acordo em um período de
	 * tempo.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public Integer getQuantidadeSaidaTotal(Date dataInicio, Date dataFim) {
		return movimentacoes
				.stream().filter((m) -> m.getTipo() == TipoMovimentacaoEnum.SAIDA.getId()
						&& m.getData().after(dataInicio) && m.getData().before(dataFim))
				.mapToInt((m) -> m.getQuantidade()).sum();
	}

	/**
	 * Calcula a quantidade total de saídas do produto de acordo em um período de
	 * tempo.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public Integer getQuantidadeEntradaTotal(Date dataInicio, Date dataFim) {
		Integer total = 0;
		if (dataInicio.compareTo(dataFim) == 0) {
			total = movimentacoes.stream()
					.filter((m) -> m.getTipo() == TipoMovimentacaoEnum.ENTRADA.getId()
							&& m.getData().compareTo(dataInicio) == 0 )
					.mapToInt((m) -> m.getQuantidade()).sum();
		} else {
			total = movimentacoes.stream()
					.filter((m) -> m.getTipo() == TipoMovimentacaoEnum.ENTRADA.getId()
							&& m.getData().compareTo(dataInicio) > 0 
							&& m.getData().compareTo(dataFim) < 0)
					.mapToInt((m) -> m.getQuantidade()).sum();
		}
		
		
		return total;
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

	public List<Movimentacao> getMovimentacoes() {
		if (movimentacoes == null) {
			movimentacoes = new ArrayList<Movimentacao>();
		}
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		
		this.movimentacoes = movimentacoes;
	}

	public Integer getQuantidadeTotal() {
		if (quantidadeTotal == null) {
			quantidadeTotal = getMovimentacoes().stream().mapToInt((m) -> m.getQuantidade()).sum();
		}

		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
}
