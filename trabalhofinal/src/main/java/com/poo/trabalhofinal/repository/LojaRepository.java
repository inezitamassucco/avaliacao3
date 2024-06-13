package com.poo.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.trabalhofinal.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Integer> {

	public Loja findByNome(String nome);

}
