package com.puc.eshoes.controller;

import com.puc.eshoes.domain.Produto;
import com.puc.eshoes.domain.dto.ApiErrorResponse;
import com.puc.eshoes.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

    @GetMapping("/findBy/{nome}")
    public ResponseEntity<List<Produto>> findByName(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(produtoService.findByName(nome));
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(produtoService.findOne(id));
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> editOne(@PathVariable("id") Long id, @RequestBody Produto produto) {
        try {
            return ResponseEntity.ok().body(produtoService.editOne(id, produto));
        }  catch (HttpClientErrorException ex) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), re.getMessage()));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Produto produto) {
        try {
            return ResponseEntity.ok().body(produtoService.save(produto));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Produto> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
