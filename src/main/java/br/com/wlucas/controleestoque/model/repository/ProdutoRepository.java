package br.com.wlucas.controleestoque.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wlucas.controleestoque.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
