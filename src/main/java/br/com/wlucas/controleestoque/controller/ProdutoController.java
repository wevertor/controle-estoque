package br.com.wlucas.controleestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value = "/{id}")
	public ProdutoDTO findById(@PathVariable(value = "id") Long id) {
		return produtoService.findById(id);
	}
	
	@PostMapping
	public ProdutoDTO create(@RequestBody ProdutoDTO produto) {
		return produtoService.create(produto);
	}

	@PutMapping
	public ProdutoDTO update(@RequestBody ProdutoDTO produto) {
		return produtoService.update(produto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		produtoService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
