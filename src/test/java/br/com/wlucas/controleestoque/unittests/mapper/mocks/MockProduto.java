package br.com.wlucas.controleestoque.unittests.mapper.mocks;

import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;

/**
 * Classe usada para gerar mocks da entidade e DTO de Produto.
 *  
 * @author Weverton Trindade, 11/11/2022
 */
public class MockProduto {
	
	public Produto mockEntity() {
		return mockEntity(0);
	}
	
	public ProdutoDTO mockDTO() {
		return mockDTO(0);
	}

	public Produto mockEntity(Integer number) {
		Produto produto = new Produto();
		produto.setId(number.longValue());
		produto.setDescricao(number % 2 == 0 ? "AAAAA" : "BBBBB");
		produto.setValor((double) number * 10);
		
		return produto;
	}
	
	public ProdutoDTO mockDTO(Integer number) {
		ProdutoDTO dto = new ProdutoDTO();
		dto.setId(number.longValue());
		dto.setDescricao(number % 2 == 0 ? "AAAAA" : "BBBBB");
		dto.setValor((double) number * 10);
		
		return dto;
	}
	
}
