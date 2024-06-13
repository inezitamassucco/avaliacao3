package com.poo.trabalhofinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.trabalhofinal.model.Loja;
import com.poo.trabalhofinal.model.Produto;
import com.poo.trabalhofinal.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public Produto insereProduto(Produto p) {
		return repository.save(p);
	}

	public Produto alteraProduto(Produto p) {
		return repository.save(p);
	}

	public Produto buscaPorCodigo(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public void excluiProduto(Produto p) {
		repository.delete(p);
	}

	public List<Produto> listaTodosProdutos() {
		return repository.findAll();
	}

	public List<Produto> listaPorLoja(Loja l) {
		return repository.findByLoja(l);
	}

}
