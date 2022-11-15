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

import br.com.wlucas.controleestoque.model.TipoMovimentacaoEnum;
import br.com.wlucas.controleestoque.model.dto.MovimentacaoDTO;
import br.com.wlucas.controleestoque.service.MovimentacaoService;

/**
 * Classe controller de Movimentacao.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService movimentacaoService;

	/**
	 * Busca todas as movimentacões cadastradas.
	 * @return
	 */
	@GetMapping(produces="application/json")
	public List<MovimentacaoDTO> findAll() {
		return movimentacaoService.findAll();
	}
	/**
	 * Busca uma movimentação a partir do id.
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces="application/json")
	public MovimentacaoDTO findById(@PathVariable(value = "id") Long id) {
		return movimentacaoService.findById(id);
	}

	/**
	 * Busca todas as entradas cadastradas e é possível usar parâmetros como filtros.
	 * @param idProduto
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	@GetMapping(value = "/entrada", produces="application/json")
	public List<MovimentacaoDTO> findEntradasByFilters(
			@RequestParam(value = "idProduto", required = false) Long idProduto,
			@RequestParam(value = "dataInicio", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio, 
			@RequestParam(value = "dataFim", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
		return movimentacaoService.findByFilters(idProduto, TipoMovimentacaoEnum.ENTRADA.getId(), dataInicio, dataFim);
	}
	
	/**
	 * Busca todas as saídas cadastradas e é possível usar parâmetros como filtros.
	 * @param idProduto
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	@GetMapping(value = "/saida", produces="application/json")
	public List<MovimentacaoDTO> findSaidasByFilters(
			@RequestParam(value = "idProduto", required = false) Long idProduto,
			@RequestParam(value = "dataInicio", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio, 
			@RequestParam(value = "dataFim", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
		return movimentacaoService.findByFilters(idProduto, TipoMovimentacaoEnum.SAIDA.getId(), dataInicio, dataFim);
	}

	/**
	 * Cadastra uma nova movimentação do tipo entrada. 
	 * Caso o valor do tipo não esteja presente, ele é inserido.
	 * @param movimentacao
	 * @return
	 */
	@PostMapping(value = "/entrada", consumes="application/json", produces="application/json")
	public MovimentacaoDTO createEntrada(@RequestBody MovimentacaoDTO movimentacao) {
		if (movimentacao.getTipo() == null) {
			movimentacao.setTipo(TipoMovimentacaoEnum.ENTRADA.getId());
		}
		
		return movimentacaoService.create(movimentacao);
	}
	
	/**
	 * Cadastra uma nova movimentação do tipo saída. 
	 * Caso o valor do tipo não esteja presente, ele é inserido.
	 * @param movimentacao
	 * @return
	 */
	@PostMapping(value = "/saida", consumes="application/json", produces="application/json")
	public MovimentacaoDTO createSaida(@RequestBody MovimentacaoDTO movimentacao) {
		if (movimentacao.getTipo() == null) {
			movimentacao.setTipo(TipoMovimentacaoEnum.SAIDA.getId());
		}
		
		return movimentacaoService.create(movimentacao);
	}
	
	/**
	 * Altera o registro de uma movimentação.
	 * @param movimentacao
	 * @return
	 */
	@PutMapping(consumes="application/json", produces="application/json")
	public MovimentacaoDTO update(@RequestBody MovimentacaoDTO movimentacao) {
		return movimentacaoService.update(movimentacao);
	}

	/**
	 * Estorna uma movimentação a partir do id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		movimentacaoService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
