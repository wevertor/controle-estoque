package br.com.wlucas.controleestoque.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wlucas.controleestoque.exception.RequiredObjectIsNullException;
import br.com.wlucas.controleestoque.exception.ResourceNotFoundException;
import br.com.wlucas.controleestoque.mapper.DozerMapper;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;
import br.com.wlucas.controleestoque.model.repository.ProdutoRepository;
import br.com.wlucas.controleestoque.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	private Logger logger = Logger.getLogger(ProdutoService.class.getName());

	/**
	 * Método usado para cadastrar um novo produto.
	 * 
	 * @param produto DTO contendo dados do produto a ser cadastrado.
	 * @return DTO contendo dados do produto cadastrado.
	 */
	@Override
	public ProdutoDTO create(ProdutoDTO produto) {
		if (produto == null) {
			throw new RequiredObjectIsNullException();
		}

		logger.info("Cadastrando um novo produto.");

		Produto entity = DozerMapper.getInstance().parseObject(produto, Produto.class);
		ProdutoDTO dto = DozerMapper.getInstance().parseObject(repository.save(entity), ProdutoDTO.class);

		return dto;
	}

	/**
	 * Método usado para buscar todos os produtos cadastrados.
	 */
	@Override
	public List<ProdutoDTO> findAll() {

		logger.info("Buscando todos os produtos cadastrados.");
		List<Produto> listEntities = repository.findAll();
		List<ProdutoDTO> listDtos = DozerMapper.getInstance().parseListObjects(listEntities, ProdutoDTO.class);

		return listDtos;
	}

	/**
	 * Método usado para buscar um produto específico a partir do id.
	 * 
	 * @param id Id do produto
	 * @return DTO do produto encontrado
	 */
	@Override
	public ProdutoDTO findById(Long id) {

		logger.info("Buscando um produto.");
		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		ProdutoDTO dto = DozerMapper.getInstance().parseObject(entity, ProdutoDTO.class);

		return dto;
	}

	/**
	 * Método usado para alterar dados de um produto.
	 * 
	 * @param produto DTO contendo dados do produto a ser alterado.
	 * @return DTO contendo dados do produto alterado.
	 */
	@Override
	public ProdutoDTO update(ProdutoDTO produto) {
		if (produto == null) {
			throw new RequiredObjectIsNullException();
		}
		logger.info("Alterando um produto.");
		Produto entity = repository.findById(produto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));

		entity.setDescricao(produto.getDescricao());
		entity.setValor(produto.getValor());

		ProdutoDTO dto = DozerMapper.getInstance().parseObject(repository.save(entity), ProdutoDTO.class);

		return dto;
	}

	/**
	 * Método usado para apagar um produto.
	 * 
	 * @param id Id do produto.
	 */
	@Override
	public void delete(Long id) {

		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));

		repository.delete(entity);
	}
}
