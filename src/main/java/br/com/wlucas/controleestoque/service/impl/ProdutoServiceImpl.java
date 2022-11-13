package br.com.wlucas.controleestoque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;
import br.com.wlucas.controleestoque.model.repository.ProdutoRepository;
import br.com.wlucas.controleestoque.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Override
	public ProdutoDTO create(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Produto> findAll() {
		List<Produto> listEntities = repository.findAll();
		
		return null;
	}
	
	@Override
	public ProdutoDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProdutoDTO update(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
