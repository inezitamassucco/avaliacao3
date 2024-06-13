package com.poo.trabalhofinal.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.trabalhofinal.model.Loja;
import com.poo.trabalhofinal.services.LojaService;

@RestController
@RequestMapping("/loja")
public class LojaResource {

	@Autowired
	LojaService service;

	@GetMapping
	public ResponseEntity<List<Loja>> listaTodas() {
		List<Loja> lojas = service.listaTodasLojas();
		if (lojas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(lojas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer id) {
		Loja loja = service.buscaPorCodigo(id);
		if (loja != null) {
			return ResponseEntity.ok(loja);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja não encontrada com o ID: " + id);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
		Loja loja = service.buscaPorNome(nome);
		if (loja != null) {
			return ResponseEntity.ok(loja);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja não encontrada com o nome: " + nome);
	}

	@PostMapping
	public Loja cadastraLoja(@RequestBody Loja l) {
		return service.insereLoja(l);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> altera(@PathVariable Integer id, @RequestBody Loja l) {
		Loja antiga = service.buscaPorCodigo(id);
		if (antiga != null) {
			antiga.setNome(l.getNome());
			Loja atualizada = service.alteraLoja(antiga);
			return ResponseEntity.ok(atualizada);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja não encontrada com o ID: " + id);
	}

	@DeleteMapping("/{id}")
	public String exclui(@PathVariable Integer id) {
		Loja antiga = service.buscaPorCodigo(id);
		if (antiga != null) {
			service.excluiLoja(antiga);
			return "Exclusão realizada com sucesso";
		}
		return "Loja não encontrada";
	}

}
