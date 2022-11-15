package br.com.wlucas.controleestoque.service;

import java.util.Date;
import java.util.List;

import br.com.wlucas.controleestoque.model.dto.ProdutoComparacaoDTO;
import br.com.wlucas.controleestoque.model.dto.ProdutoDTO;

/**
 * Interface do service de Produto.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
public interface ProdutoService {

	public List<ProdutoDTO> findAll();

	public ProdutoDTO findById(Long id);

	public ProdutoDTO findByDescricao(String descricao);

	public ProdutoComparacaoDTO compareEntradasAndSaidasDia(Long id, Date dia);

	public ProdutoComparacaoDTO compareEntradasAndSaidasMes(Long id, Integer mes, Integer ano);

	public ProdutoComparacaoDTO compareEntradasAndSaidasAno(Long id, Integer ano);

	public ProdutoDTO create(ProdutoDTO produto);

	public ProdutoDTO update(ProdutoDTO produto);

	public void delete(Long id);
	
	public void validate(ProdutoDTO produto);
}
