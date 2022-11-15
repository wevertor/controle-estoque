package br.com.wlucas.controleestoque.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wlucas.controleestoque.model.dto.ProdutoComparacaoDTO;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;
import br.com.wlucas.controleestoque.service.ProdutoService;

/**
 * Classe controller de Produto.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<ProdutoDTO> findAll() {
		return produtoService.findAll();
	}

	/**
	 * Busca todos os produtos cadastrados.
	 * @param descricao
	 * @return
	 */
	@GetMapping(value = "/busca")
	public ProdutoDTO findByDescricao(@RequestParam(value = "descricao") String descricao) {
		return produtoService.findByDescricao(descricao);
	}

	/**
	 * Busca um produto específico.
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ProdutoDTO findById(@PathVariable(value = "id") Long id) {
		return produtoService.findById(id);
	}

	/**
	 * Retorna um comparativo entre as entradas e saídas de um produto durante um dia.
	 * @param id
	 * @param data
	 * @return
	 */
	@GetMapping(value = "/{id}/relatorio/dia")
	public ProdutoComparacaoDTO compareEntradasAndSaidasDia(@PathVariable(value = "id") Long id,
			@RequestParam(value = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data) {
		return produtoService.compareEntradasAndSaidasDia(id, data);
	}

	/**
	 * Retorna um comparativo entre as entradas e saídas de um produto durante um mês.
	 * @param id
	 * @param mes
	 * @param ano
	 * @return
	 */
	@GetMapping(value = "/{id}/relatorio/mes/{mes}/{ano}")
	public ProdutoComparacaoDTO compareEntradasAndSaidasMes(@PathVariable(value = "id") Long id,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano) {
		return produtoService.compareEntradasAndSaidasMes(id, mes, ano);
	}

	/**
	 * Retorna um comparativo entre as entradas e saídas de um produto durante um ano.
	 * @param id
	 * @param ano
	 * @return
	 */
	@GetMapping(value = "/{id}/relatorio/ano/{ano}")
	public ProdutoComparacaoDTO compareEntradasAndSaidasAno(@PathVariable(value = "id") Long id,
			@PathVariable(value = "ano") Integer ano) {
		return produtoService.compareEntradasAndSaidasAno(id, ano);
	}

	/**
	 * Cadastra um novo produto.
	 * @param produto
	 * @return
	 */
	@PostMapping
	public ProdutoDTO create(@RequestBody ProdutoDTO produto) {
		return produtoService.create(produto);
	}

	/**
	 * Altera o registro de um produto.
	 * @param produto
	 * @return
	 */
	@PutMapping
	public ProdutoDTO update(@RequestBody ProdutoDTO produto) {
		return produtoService.update(produto);
	}

	/**
	 * Remove um produto a partir do id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		produtoService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
