package br.com.wlucas.controleestoque.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
import br.com.wlucas.controleestoque.mapper.mocks.MockMovimentacao;
import br.com.wlucas.controleestoque.mapper.mocks.MockProduto;
import br.com.wlucas.controleestoque.model.Movimentacao;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;
import br.com.wlucas.controleestoque.model.repository.MovimentacaoRepository;
import br.com.wlucas.controleestoque.model.repository.ProdutoRepository;
import br.com.wlucas.controleestoque.service.impl.MovimentacaoServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=ControleEstoqueApplication.class)
public class MovimentacaoServiceTest {

	@InjectMocks
	MovimentacaoServiceImpl service;

	@Mock
	MovimentacaoRepository repository;
	
	@Mock
	ProdutoRepository produtoRepository;
	
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
	public void deveLancarErroAoSalvarUmaMovimentacaoComDataInvalida() {
		Movimentacao movimentacao = mockMovimentacao.mockEntity();
		MovimentacaoDTO dto = mockMovimentacao.mockDTO();
		dto.setData(null);
		
		Mockito.when(repository.save(Mockito.any(Movimentacao.class))).thenReturn(movimentacao);
		
		Throwable exception = catchThrowable(() -> service.create(dto));
		
		assertThat(exception)
			.isInstanceOf(InvalidFieldException.class)
			.hasMessage("Data da movimentação não informada.");
	}
	
	@Test
	public void deveSalvarUmaMovimentacao() {
		Movimentacao movimentacao = mockMovimentacao.mockEntity(23);
		Optional<Produto> produto = Optional.of(mockProduto.mockEntity(23));
		
		Mockito.when(repository.save(Mockito.any(Movimentacao.class))).thenReturn(movimentacao);
		Mockito.when(produtoRepository.findById(Mockito.any(Long.class))).thenReturn(produto);
		
		MovimentacaoDTO movimentacao2 = service.create(mockMovimentacao.mockDTO(23));
		movimentacao2.setId(produto.get().getId());
		
		assertNotNull(movimentacao2);
		assertEquals(movimentacao2.getData(), movimentacao.getData());
		assertEquals(movimentacao2.getQuantidade(), movimentacao.getQuantidade());
		assertEquals(movimentacao2.getTipo(), movimentacao.getTipo());
	}
}
