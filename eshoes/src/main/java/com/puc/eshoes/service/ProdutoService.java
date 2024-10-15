package com.puc.eshoes.service;

import com.puc.eshoes.domain.Produto;
import com.puc.eshoes.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

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

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

}
