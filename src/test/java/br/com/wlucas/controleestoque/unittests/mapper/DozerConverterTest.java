package br.com.wlucas.controleestoque.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.wlucas.controleestoque.mapper.DozerMapper;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;
import br.com.wlucas.controleestoque.unittests.mapper.mocks.MockMovimentacao;
import br.com.wlucas.controleestoque.unittests.mapper.mocks.MockProduto;

public class DozerConverterTest {

	private MockProduto mockProduto;
	private MockMovimentacao mockMovimentacao;
	private DozerMapper dozerMapper;

	/**
	 * Insere uma nova instância do mock a cada teste.
	 */
	@BeforeEach
	public void setUp() {
		mockProduto = new MockProduto();
		mockMovimentacao = new MockMovimentacao();
		dozerMapper = new DozerMapper();
	}

	@Test
	public void produtoParseTest() {
		ProdutoDTO output = dozerMapper.parseObject(mockProduto.mockEntity(), ProdutoDTO.class);

		assertEquals(Long.valueOf(0), output.getId());
		assertEquals("AAAAA", output.getDescricao());
		assertEquals(0d, output.getValor());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void movimentacaoParseTest() {
		MovimentacaoDTO output = dozerMapper.parseObject(mockMovimentacao.mockEntity(), MovimentacaoDTO.class);

		assertEquals(Long.valueOf(0), output.getId());
		assertEquals(Long.valueOf(0), output.getIdProduto());
		assertEquals(new Date(2022, 1, 1), output.getData());
		assertEquals(0, output.getQuantidade());
	}

}
