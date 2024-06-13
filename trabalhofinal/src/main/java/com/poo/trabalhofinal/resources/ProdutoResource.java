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
import com.poo.trabalhofinal.model.Produto;
import com.poo.trabalhofinal.services.LojaService;
import com.poo.trabalhofinal.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

	@Autowired
	ProdutoService service;

	@Autowired
	LojaService lojaService;

	@GetMapping
	public ResponseEntity<List<Produto>> listaTodas() {
		List<Produto> produtos = service.listaTodosProdutos();
		if (produtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(produtos);
		}
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer id) {
		Produto produto = service.buscaPorCodigo(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com o ID: " + id);
	}

	@GetMapping("/loja/{id}")
	public ResponseEntity<?> buscarPorLoja(@PathVariable Integer id) {
		Loja loja = lojaService.buscaPorCodigo(id);
		if (loja != null) {
			List<Produto> produtos = service.listaPorLoja(loja);
			if (produtos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("Nenhum produto encontrado para a loja com o ID: " + id);
			}
			return ResponseEntity.ok(produtos);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja não encontrada com o ID: " + id);
	}

	@PostMapping
	public Produto cadastraProduto(@RequestBody Produto p) {
		return service.insereProduto(p);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> altera(@PathVariable Integer id, @RequestBody Produto p) {
		Produto antigo = service.buscaPorCodigo(id);
		if (antigo != null) {
			antigo.setNome(p.getNome());
			antigo.setLoja(p.getLoja());
			Produto atualizado = service.alteraProduto(antigo);
			return ResponseEntity.ok(atualizado);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com o ID: " + id);
	}

	@DeleteMapping("/{id}")
	public String exclui(@PathVariable Integer id) {
		Produto antigo = service.buscaPorCodigo(id);
		if (antigo != null) {
			service.excluiProduto(antigo);
			return "Produto excluído com sucesso";
		}
		return "Produto não encontrado";
	}

}
