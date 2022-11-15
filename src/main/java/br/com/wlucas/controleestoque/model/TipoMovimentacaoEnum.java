package br.com.wlucas.controleestoque.model;

/**
 * Enum que traduz os tipos de movimentação de um produto.
 * 
 * @author Weverton Trindade, 14/11/2022
 *
 */
public enum TipoMovimentacaoEnum {
	ENTRADA(1, "Entrada"), SAIDA(2, "Saída");

	private Integer id;
	private String descricao;

	private TipoMovimentacaoEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static TipoMovimentacaoEnum getById(Integer id) {
		for (TipoMovimentacaoEnum value : values()) {
			if (value.id == id) {
				return value;
			}
		}

		return null;
	}
	
	public static TipoMovimentacaoEnum getByDescricao(String descricao) {
		for (TipoMovimentacaoEnum value : values()) {
			if (value.descricao.equals(descricao)) {
				return value;
			}
		}

		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
