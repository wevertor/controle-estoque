package br.com.wlucas.controleestoque.model.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.wlucas.controleestoque.mapper.mocks.MockProduto;
import br.com.wlucas.controleestoque.model.Produto;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProdutoRepositoryTest {
	@Autowired
	private ProdutoRepository repository;
	
	private MockProduto mockProduto;

	/**
	 * Insere uma nova instância do mock a cada teste.
	 */
	@BeforeEach
	public void setUp() {
		mockProduto = new MockProduto();
	}
	
	@Test
	public void devePersistirUmProdutoNaBaseDeDados() {
		Produto produto = mockProduto.mockEntity();
		
		Produto produtoSalvo = repository.save(produto);
		
		assertNotNull(produtoSalvo.getId());
		
		repository.delete(produtoSalvo);
	}
	
	@Test
	public void deveBuscarUmProdutoPorDescricao() {
		Produto produtoA = mockProduto.mockEntity(2);
		Produto produtoB = mockProduto.mockEntity(3);
		
		repository.save(produtoA);
		repository.save(produtoB);
		
		Optional<Produto> produtoAEncontrado = repository.findOneByDescricao("AAAAA");
		Optional<Produto> produtoBEncontrado = repository.findOneByDescricao("BBBBB");
		
		assertTrue(produtoAEncontrado.isPresent());
		assertEquals(produtoA.getDescricao(), produtoAEncontrado.get().getDescricao());
		assertEquals(produtoA.getValor(), produtoAEncontrado.get().getValor());
		
		assertTrue(produtoBEncontrado.isPresent());
		assertEquals(produtoB.getDescricao(), produtoBEncontrado.get().getDescricao());
		assertEquals(produtoB.getValor(), produtoBEncontrado.get().getValor());
		
		repository.delete(produtoA);
		repository.delete(produtoB);
	}
	
	@Test
	public void deveRemoverUmProduto() {
		Produto produtoA = mockProduto.mockEntity(2);
		
		repository.save(produtoA);
		
		Optional<Produto> produtoAParaRemover = repository.findOneByDescricao("AAAAA");
		
		repository.delete(produtoAParaRemover.get());
		
		Optional<Produto> produtoAEncontrado = repository.findOneByDescricao("AAAAA");
		
		assertTrue(produtoAEncontrado.isEmpty());
	}
}
