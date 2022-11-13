package br.com.wlucas.controleestoque.unittests.mapper.mocks;

import java.util.Date;

import br.com.wlucas.controleestoque.model.Movimentacao;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;

/**
 * Classe usada para gerar mocks da entidade e DTO de Movimentacao.
 *  
 * @author Weverton Trindade, 11/11/2022
 */
public class MockMovimentacao {
	
	public Movimentacao mockEntity() {
		return mockEntity(0);
	}
	
	public MovimentacaoDTO mockDTO() {
		return mockDTO(0);
	}

	@SuppressWarnings("deprecation")
	public Movimentacao mockEntity(Integer number) {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setId(number.longValue());
		movimentacao.setProduto(new MockProduto().mockEntity());
		movimentacao.setData(new Date(2022, 1, 1));
		movimentacao.setQuantidade(number);
		
		return movimentacao;
	}
	
	@SuppressWarnings("deprecation")
	public MovimentacaoDTO mockDTO(Integer number) {
		MovimentacaoDTO dto = new MovimentacaoDTO();
		dto.setId(number.longValue());
		dto.setIdProduto(number.longValue());
		dto.setData(new Date(2022, 1, 1));
		dto.setQuantidade(number);
		
		return dto;
	}
	
}
