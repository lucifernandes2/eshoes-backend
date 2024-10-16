package com.puc.eshoes.service;

import com.puc.eshoes.domain.Produto;
import com.puc.eshoes.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findOne(Long idProduto) {
        return produtoRepository.findById(idProduto).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Produto> findByName(String nome) {
        return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Produto editOne(Long idProduto, Produto produto) {
        Produto produtoDb = produtoRepository.findById(idProduto).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if (!produtoDb.getNome().equals(produto.getNome())) {
            produtoDb.setNome(produto.getNome());
        }

        if (!produtoDb.getTamanho().equals(produto.getTamanho())) {
            produtoDb.setTamanho(produto.getTamanho());
        }

        if (!produtoDb.getPreco().equals(produto.getPreco())) {
            if (produto.getPreco() < 0) {
                throw new RuntimeException("Preço inválido");
            }
            produtoDb.setPreco(produto.getPreco());
        }

        if (!produtoDb.getEstoque().equals(produto.getEstoque())) {
            produtoDb.setEstoque(produto.getEstoque());
        }


        if ((produto.getCategoriaProduto() != null && produtoDb.getCategoriaProduto() != null) && !produtoDb.getCategoriaProduto().equals(produto.getCategoriaProduto())) {
            produtoDb.setCategoriaProduto(produto.getCategoriaProduto());
        }

        return produtoRepository.save(produtoDb);
    }

    public Produto save(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new RuntimeException("Nome é um campo obrigatório");
        }
        if (produto.getPreco() == null) {
            throw new RuntimeException("Preço é um campo obrigatório");
        }
        if (produto.getTamanho() == null || produto.getTamanho().isEmpty()) {
            throw new RuntimeException("Tamanho é um campo obrigatório");
        }
        return produtoRepository.save(produto);
    }

    public void delete(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

}