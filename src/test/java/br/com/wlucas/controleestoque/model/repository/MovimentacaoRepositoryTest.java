package br.com.wlucas.controleestoque.model.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.wlucas.controleestoque.mapper.mocks.MockMovimentacao;
import br.com.wlucas.controleestoque.mapper.mocks.MockProduto;
import br.com.wlucas.controleestoque.model.Movimentacao;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.TipoMovimentacaoEnum;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MovimentacaoRepositoryTest {
	@Autowired
	private MovimentacaoRepository repository;
	@Autowired
	private ProdutoRepository produtoRepository;

	private MockMovimentacao mockMovimentacao;
	private MockProduto mockProduto;

	/**
	 * Insere uma nova instância do mock a cada teste.
	 */
	@BeforeEach
	public void setUp() {
		mockMovimentacao = new MockMovimentacao();
		mockProduto = new MockProduto();
	}

	@Test
	public void devePersistirUmaMovimentacaoNaBaseDeDados() {
		Movimentacao movimentacao = mockMovimentacao.mockEntity();
		Produto produtoSalvo = produtoRepository.save(mockProduto.mockEntity());

		movimentacao.setProduto(produtoSalvo);

		Movimentacao movimentacaoSalva = repository.save(movimentacao);

		assertNotNull(movimentacaoSalva.getId());

		repository.delete(movimentacaoSalva);
	}

	@Test
	public void deveBuscarUmaMovimentacaoPorData() {
		Movimentacao movimentacaoA = mockMovimentacao.mockEntity(2);
		Movimentacao movimentacaoB = mockMovimentacao.mockEntity(3);
		Produto produtoSalvo = produtoRepository.save(mockProduto.mockEntity());

		movimentacaoA.setProduto(produtoSalvo);
		movimentacaoB.setProduto(produtoSalvo);

		repository.save(movimentacaoA);
		repository.save(movimentacaoB);

		List<Movimentacao> listaA = repository.findByFilters(null, null, new Date(2022, 1, 1), new Date(2022, 1, 2));
		List<Movimentacao> listaB = repository.findByFilters(null, null, new Date(2022, 1, 3), new Date(2022, 1, 5));

		assertTrue(listaA.size() == 1);
		assertEquals(movimentacaoA.getQuantidade(), listaA.get(0).getQuantidade());
		assertEquals(movimentacaoA.getTipo(), listaA.get(0).getTipo());

		assertTrue(listaB.size() == 1);
		assertEquals(movimentacaoB.getQuantidade(), listaB.get(0).getQuantidade());
		assertEquals(movimentacaoB.getTipo(), listaB.get(0).getTipo());

		repository.delete(listaA.get(0));
		repository.delete(listaB.get(0));
	}

	@Test
	public void deveBuscarUmaMovimentacaoPorTipoEProduto() {
		Movimentacao movimentacaoA = mockMovimentacao.mockEntity(2);
		Movimentacao movimentacaoB = mockMovimentacao.mockEntity(3);
		Produto produtoSalvo = produtoRepository.save(mockProduto.mockEntity());

		movimentacaoA.setProduto(produtoSalvo);
		movimentacaoB.setProduto(produtoSalvo);

		repository.save(movimentacaoA);
		repository.save(movimentacaoB);

		List<Movimentacao> listaA = repository.findByFilters(produtoSalvo.getId(), TipoMovimentacaoEnum.ENTRADA.getId(),
				null, null);
		List<Movimentacao> listaB = repository.findByFilters(produtoSalvo.getId(), TipoMovimentacaoEnum.SAIDA.getId(),
				null, null);

		assertTrue(listaA.size() == 1);
		assertEquals(movimentacaoA.getQuantidade(), listaA.get(0).getQuantidade());
		assertEquals(movimentacaoA.getTipo(), listaA.get(0).getTipo());

		assertTrue(listaB.size() == 1);
		assertEquals(movimentacaoB.getQuantidade(), listaB.get(0).getQuantidade());
		assertEquals(movimentacaoB.getTipo(), listaB.get(0).getTipo());

		repository.delete(listaA.get(0));
		repository.delete(listaB.get(0));
	}

	@Test
	public void deveRemoverUmaMovimentacao() {
		Movimentacao movimentacao = mockMovimentacao.mockEntity(2);
		Produto produtoSalvo = produtoRepository.save(mockProduto.mockEntity());

		movimentacao.setProduto(produtoSalvo);

		repository.save(movimentacao);

		List<Movimentacao> movimentacaoParaRemover = repository.findByFilters(produtoSalvo.getId(),
				TipoMovimentacaoEnum.ENTRADA.getId(), null, null);

		repository.delete(movimentacaoParaRemover.get(0));

		List<Movimentacao> movimentacaoEncontrado = repository.findByFilters(produtoSalvo.getId(), TipoMovimentacaoEnum.ENTRADA.getId(),
				null, null);

		assertTrue(movimentacaoEncontrado.isEmpty());
	}
}
