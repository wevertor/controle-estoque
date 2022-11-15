package br.com.wlucas.controleestoque.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wlucas.controleestoque.exception.InvalidFieldException;
import br.com.wlucas.controleestoque.exception.RequiredObjectIsNullException;
import br.com.wlucas.controleestoque.exception.ResourceNotFoundException;
import br.com.wlucas.controleestoque.mapper.DozerMapper;
import br.com.wlucas.controleestoque.model.Movimentacao;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.TipoMovimentacaoEnum;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;
import br.com.wlucas.controleestoque.model.repository.MovimentacaoRepository;
import br.com.wlucas.controleestoque.model.repository.ProdutoRepository;
import br.com.wlucas.controleestoque.service.MovimentacaoService;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;

	@Autowired
	private ProdutoRepository produtoRepository;

	private Logger logger = Logger.getLogger(MovimentacaoService.class.getName());

	/**
	 * Método usado para cadastrar uma nova movimentação.
	 * 
	 * @param movimentação DTO contendo dados da nova movimentação.
	 * @return DTO contendo dados da movimentação cadastrada.
	 */
	@Override
	public MovimentacaoDTO create(MovimentacaoDTO movimentacao) {
		validate(movimentacao);
		
		/* valores de saída devem ser negativos e de entrada positivos */
		if (movimentacao.getQuantidade() > 0 && movimentacao.getTipo() == TipoMovimentacaoEnum.SAIDA.getId()) {
			movimentacao.setQuantidade(0 - movimentacao.getQuantidade());
		}
		else if (movimentacao.getQuantidade() < 0 && movimentacao.getTipo() == TipoMovimentacaoEnum.ENTRADA.getId()) {
			movimentacao.setQuantidade(Math.abs(movimentacao.getQuantidade()));
		}

		Produto produto = produtoRepository.findById(movimentacao.getIdProduto())
				.orElseThrow(() -> new ResourceNotFoundException("Produto informado não encontrado."));

		logger.info("Cadastrando uma nova movimentação do tipo: "
				+ TipoMovimentacaoEnum.getById(movimentacao.getTipo()).getDescricao() + ".");

		Movimentacao entity = DozerMapper.getInstance().parseObject(movimentacao, Movimentacao.class);
		entity.setProduto(produto);
		MovimentacaoDTO dto = DozerMapper.getInstance().parseObject(repository.save(entity), MovimentacaoDTO.class);

		return dto;
	}

	/**
	 * Método usado para buscar todas as movimentacões cadastradas.
	 */
	@Override
	public List<MovimentacaoDTO> findAll() {

		logger.info("Buscando todas as movimentações cadastradas.");
		List<Movimentacao> listEntities = repository.findAll();
		List<MovimentacaoDTO> listDtos = DozerMapper.getInstance().parseListObjects(listEntities,
				MovimentacaoDTO.class);

		return listDtos;
	}

	/**
	 * Método usado para buscar uma movimentação específica a partir do id.
	 * 
	 * @param id Id do produto
	 * @return DTO do produto encontrado
	 */
	@Override
	public MovimentacaoDTO findById(Long id) {

		logger.info("Buscando uma movimentacao.");
		Movimentacao entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		MovimentacaoDTO dto = DozerMapper.getInstance().parseObject(entity, MovimentacaoDTO.class);

		return dto;
	}

	@Override
	public List<MovimentacaoDTO> findByDataBetween(Date inicio, Date fim) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		logger.info("Buscando todas as movimentações cadastradas entre " + formatter.format(inicio) + " e "
				+ formatter.format(fim) + ".");

		List<Movimentacao> listEntities = repository.findByDataBetween(inicio, fim);
		List<MovimentacaoDTO> listDtos = DozerMapper.getInstance().parseListObjects(listEntities,
				MovimentacaoDTO.class);

		return listDtos;
	}

	@Override
	public List<MovimentacaoDTO> findByFilters(Long idProduto, Integer tipo, Date dataInicio, Date dataFim) {
		List<Movimentacao> listEntities = repository.findByFilters(idProduto, tipo, dataInicio,
				dataFim);
		List<MovimentacaoDTO> listDtos = DozerMapper.getInstance().parseListObjects(listEntities,
				MovimentacaoDTO.class);

		return listDtos;
	}

	@Override
	public List<MovimentacaoDTO> findByProduto(Long produtoId) {
		List<Movimentacao> listEntities = repository.findByProduto(produtoId);
		List<MovimentacaoDTO> listDtos = DozerMapper.getInstance().parseListObjects(listEntities,
				MovimentacaoDTO.class);

		return listDtos;
	}

	/**
	 * Método usado para alterar dados de uma movimentação.
	 * 
	 * @param DTO contendo dados da movimentação a ser alterada.
	 * @return DTO contendo dados da movimentação salva.
	 */
	@Override
	public MovimentacaoDTO update(MovimentacaoDTO movimentacao) {
		validate(movimentacao);
		
		/* valores de saída devem ser negativos e de entrada positivos */
		if (movimentacao.getQuantidade() > 0 && movimentacao.getTipo() == TipoMovimentacaoEnum.SAIDA.getId()) {
			movimentacao.setQuantidade(0 - movimentacao.getQuantidade());
		}
		else if (movimentacao.getQuantidade() < 0 && movimentacao.getTipo() == TipoMovimentacaoEnum.ENTRADA.getId()) {
			movimentacao.setQuantidade(Math.abs(movimentacao.getQuantidade()));
		}
		Produto produto = produtoRepository.findById(movimentacao.getIdProduto())
				.orElseThrow(() -> new ResourceNotFoundException("Produto informado não encontrado."));

		logger.info("Alterando um produto.");
		Movimentacao entity = repository.findById(movimentacao.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));

		entity.setData(movimentacao.getData());
		entity.setQuantidade(movimentacao.getQuantidade());
		entity.setTipo(movimentacao.getTipo());
		entity.setProduto(produto);

		MovimentacaoDTO dto = DozerMapper.getInstance().parseObject(repository.save(entity), MovimentacaoDTO.class);

		return dto;
	}

	/**
	 * Método usado para apagar uma movimentação.
	 * 
	 * @param id Id do produto.
	 */
	@Override
	public void delete(Long id) {

		Movimentacao entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));

		repository.delete(entity);
	}
	
	@Override
	public void validate(MovimentacaoDTO movimentacao) {
		if (movimentacao == null) {
			throw new RequiredObjectIsNullException();
		}
		
		if (movimentacao.getData() == null) {
			throw new InvalidFieldException("Data da movimentação não informada.");
		}
		if (movimentacao.getQuantidade() == null) {
			throw new InvalidFieldException("Quantidade não informada.");
		}
		if (movimentacao.getTipo() == null) {
			throw new InvalidFieldException("Tipo não informado.");
		}
	}
}
