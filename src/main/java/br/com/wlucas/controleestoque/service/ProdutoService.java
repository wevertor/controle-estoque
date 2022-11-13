package br.com.wlucas.controleestoque.service;

import java.util.List;

import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;

public interface ProdutoService {
	
	public List<Produto> findAll();
	
	public ProdutoDTO findById(Long id);
	
	public ProdutoDTO create(ProdutoDTO produto);
	
	public ProdutoDTO update(ProdutoDTO produto);
	
	public void delete(Long id);
}
