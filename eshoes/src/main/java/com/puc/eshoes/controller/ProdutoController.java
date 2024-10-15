package com.puc.eshoes.controller;

import com.puc.eshoes.domain.Produto;
import com.puc.eshoes.domain.dto.SignupRequest;
import com.puc.eshoes.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produto", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Produto> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(produtoService.findOne(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return ResponseEntity.ok().body(produtoService.save(produto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Produto> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
