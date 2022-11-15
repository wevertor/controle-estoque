package br.com.wlucas.controleestoque.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.wlucas.controleestoque.ControleEstoqueApplication;
import br.com.wlucas.controleestoque.exception.InvalidFieldException;
import br.com.wlucas.controleestoque.mapper.mocks.MockProduto;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;
import br.com.wlucas.controleestoque.model.repository.ProdutoRepository;
import br.com.wlucas.controleestoque.service.impl.ProdutoServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=ControleEstoqueApplication.class)
public class ProdutoServiceTest {

	@InjectMocks
	ProdutoServiceImpl service;

	@Mock
	ProdutoRepository repository;
	
	private MockProduto mockProduto;
	
	/**
	 * Insere uma nova instância do mock a cada teste.
	 */
	@BeforeEach
	public void setUp() {
		mockProduto = new MockProduto();
	}
	
	@Test
	public void deveLancarErroAoSalvarUmProdutoComValorZero() {
		Produto produto = mockProduto.mockEntity();
		
		Mockito.when(repository.save(Mockito.any(Produto.class))).thenReturn(produto);
		
		Throwable exception = catchThrowable(() -> service.create(mockProduto.mockDTO()));
		
		assertThat(exception)
			.isInstanceOf(InvalidFieldException.class)
			.hasMessage("O valor do produto deve ser maior que zero.");
	}
	
	@Test
	public void deveSalvarUmProduto() {
		Produto produto = mockProduto.mockEntity(23);
		
		Mockito.when(repository.save(Mockito.any(Produto.class))).thenReturn(produto);
		
		ProdutoDTO produto2 = service.create(mockProduto.mockDTO(23));
		
		assertNotNull(produto2);
		assertEquals(produto2.getDescricao(), produto.getDescricao());
		assertEquals(produto2.getValor(), produto.getValor());
	}
}
