package com.poo.trabalhofinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.trabalhofinal.model.Loja;
import com.poo.trabalhofinal.repository.LojaRepository;

@Service
public class LojaService {

	@Autowired
	LojaRepository repository;

	public Loja insereLoja(Loja l) {
		return repository.save(l);
	}

	public Loja alteraLoja(Loja l) {
		return repository.save(l);
	}

	public Loja buscaPorCodigo(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public Loja buscaPorNome(String nome) {
		return repository.findByNome(nome);
	}

	public void excluiLoja(Loja l) {
		repository.delete(l);
	}

	public List<Loja> listaTodasLojas() {
		return repository.findAll();
	}

}
