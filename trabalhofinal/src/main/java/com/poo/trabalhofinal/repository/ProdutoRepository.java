package com.poo.trabalhofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.trabalhofinal.model.Loja;
import com.poo.trabalhofinal.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByLoja(Loja l);

}
