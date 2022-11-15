package br.com.wlucas.controleestoque.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.wlucas.controleestoque.mapper.mocks.MockMovimentacao;
import br.com.wlucas.controleestoque.mapper.mocks.MockProduto;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;

public class DozerConverterTest {

	private MockProduto mockProduto;
	private MockMovimentacao mockMovimentacao;

	/**
	 * Insere uma nova instância do mock a cada teste.
	 */
	@BeforeEach
	public void setUp() {
		mockProduto = new MockProduto();
		mockMovimentacao = new MockMovimentacao();
	}

	@Test
	public void deveConverterProduto() {
		ProdutoDTO output = DozerMapper.getInstance().parseObject(mockProduto.mockEntity(), ProdutoDTO.class);

		assertEquals(Long.valueOf(0), output.getId());
		assertEquals("AAAAA", output.getDescricao());
		assertEquals(0d, output.getValor());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deveConverterMovimentacao() {
		MovimentacaoDTO output = DozerMapper.getInstance().parseObject(mockMovimentacao.mockEntity(), MovimentacaoDTO.class);

		assertEquals(Long.valueOf(0), output.getId());
		assertEquals(Long.valueOf(0), output.getIdProduto());
		assertEquals(new Date(2022, 1, 1), output.getData());
		assertEquals(0, output.getQuantidade());
	}

}
