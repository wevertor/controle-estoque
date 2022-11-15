package br.com.wlucas.controleestoque.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wlucas.controleestoque.exception.InvalidFieldException;
import br.com.wlucas.controleestoque.exception.RequiredObjectIsNullException;
import br.com.wlucas.controleestoque.exception.ResourceNotFoundException;
import br.com.wlucas.controleestoque.mapper.DozerMapper;
import br.com.wlucas.controleestoque.model.Produto;
import br.com.wlucas.controleestoque.model.dto.ProdutoComparacaoDTO;
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
		validate(produto);

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

		logger.info("Buscando um produto pelo id.");
		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		ProdutoDTO dto = DozerMapper.getInstance().parseObject(entity, ProdutoDTO.class);

		return dto;
	}

	@Override
	public ProdutoComparacaoDTO compareEntradasAndSaidasDia(Long id, Date dia) {
		logger.info("Calculando todas as entradas e saídas em um dia.");
		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		ProdutoComparacaoDTO comparacaoDTO = DozerMapper.getInstance().parseObject(entity, ProdutoComparacaoDTO.class);

		comparacaoDTO.setQuantidadeEntradaTotal(entity.getQuantidadeEntradaTotal(dia, dia));
		comparacaoDTO.setQuantidadeSaidaTotal(entity.getQuantidadeSaidaTotal(dia, dia));
		comparacaoDTO.setDataInicio(dia);
		comparacaoDTO.setDataFim(dia);

		return comparacaoDTO;
	}

	@Override
	public ProdutoComparacaoDTO compareEntradasAndSaidasMes(Long id, Integer mes, Integer ano) {
		logger.info("Calculando todas as entradas e saídas em um dia.");

		if (mes < 1 || mes >= 12) {
			throw new InvalidFieldException("O mês informado deve ser um inteiro entre 1 e 12.");
		}
		
		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		ProdutoComparacaoDTO comparacaoDTO = DozerMapper.getInstance().parseObject(entity, ProdutoComparacaoDTO.class);

		Date dataInicio = new Date(ano - 1900, mes - 1, 1);
		Date dataFim = new Date(ano - 1900, mes - 1, getLastDayOfMonth(mes - 1, ano));

		comparacaoDTO.setQuantidadeEntradaTotal(entity.getQuantidadeEntradaTotal(dataInicio, dataFim));
		comparacaoDTO.setQuantidadeSaidaTotal(entity.getQuantidadeSaidaTotal(dataInicio, dataFim));
		comparacaoDTO.setDataInicio(dataInicio);
		comparacaoDTO.setDataFim(dataFim);

		return comparacaoDTO;
	}

	@Override
	public ProdutoComparacaoDTO compareEntradasAndSaidasAno(Long id, Integer ano) {
		logger.info("Calculando todas as entradas e saídas em um dia.");
		Produto entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro foi encontrado para esse id."));
		ProdutoComparacaoDTO comparacaoDTO = DozerMapper.getInstance().parseObject(entity, ProdutoComparacaoDTO.class);

		Date dataInicio = new Date(ano - 1900, Calendar.JANUARY, 1);
		Date dataFim = new Date(ano - 1900, Calendar.DECEMBER, 1);

		comparacaoDTO.setQuantidadeEntradaTotal(entity.getQuantidadeEntradaTotal(dataInicio, dataFim));
		comparacaoDTO.setQuantidadeSaidaTotal(entity.getQuantidadeSaidaTotal(dataInicio, dataFim));
		comparacaoDTO.setDataInicio(dataInicio);
		comparacaoDTO.setDataFim(dataFim);

		return comparacaoDTO;
	}

	@Override
	public ProdutoDTO findByDescricao(String descricao) {
		logger.info("Buscando um produto pela descrição.");
		Produto entity = repository.findOneByDescricao(descricao)
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
		validate(produto);
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

	@Override
	public void validate(ProdutoDTO produto) {
		if (produto == null) {
			throw new RequiredObjectIsNullException();
		}

		if (StringUtils.isBlank(produto.getDescricao())) {
			throw new InvalidFieldException("A descrição do produto não pode ser vazia.");
		}

		if (produto.getValor() == null || produto.getValor() <= 0d) {
			throw new InvalidFieldException("O valor do produto deve ser maior que zero.");
		}
	}

	private Integer getLastDayOfMonth(Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(year, month, 1));

		Integer max = calendar.getActualMaximum(Calendar.DATE);
		return max;

	}
}
