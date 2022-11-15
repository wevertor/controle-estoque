package br.com.wlucas.controleestoque.service;

import java.util.Date;
import java.util.List;

import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;

/**
 * Interface do service de Movimentacao.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
public interface MovimentacaoService {

	public List<MovimentacaoDTO> findAll();

	public MovimentacaoDTO findById(Long id);

	public List<MovimentacaoDTO> findByProduto(Long produtoId);

	public List<MovimentacaoDTO> findByDataBetween(Date inicio, Date fim);

	public MovimentacaoDTO create(MovimentacaoDTO movimentacao);

	public MovimentacaoDTO update(MovimentacaoDTO movimentacao);

	public void delete(Long id);
	
	public void validate(MovimentacaoDTO movimentacao);

	public List<MovimentacaoDTO> findByFilters(Long idProduto, Integer tipo, Date dataInicio, 
			Date dataFim);
	
	
}
