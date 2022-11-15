package br.com.wlucas.controleestoque.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wlucas.controleestoque.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

	List<Movimentacao> findByDataBetween(Date inicio, Date fim);

	List<Movimentacao> findByProduto(Long produtoId);

	@Query("SELECT m FROM Movimentacao m"
			+ " LEFT JOIN FETCH m.produto p"
			+ " WHERE 1=1"
			+ " AND (:produtoId is null OR p.id = :produtoId)"
			+ " AND (:tipo is null OR m.tipo = :tipo)"
			+ " AND ("
					+ "(cast(:dataInicio as date) is null OR cast(:dataFim as date) is null)"
					+ " OR m.data BETWEEN :dataInicio AND :dataFim)")
	List<Movimentacao> findByFilters(@Param("produtoId") Long produtoId, 
									@Param("tipo") Integer tipo, 
									@Param("dataInicio") Date dataInicio, 
									@Param("dataFim") Date dataFim);
}
