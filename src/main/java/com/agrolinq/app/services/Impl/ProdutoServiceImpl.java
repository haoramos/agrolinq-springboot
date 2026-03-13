package com.agrolinq.app.services.Impl;

import com.agrolinq.app.dtos.ProdutoRequestDTO;
import com.agrolinq.app.dtos.ProdutoResponseDTO;
import com.agrolinq.app.models.Produto;
import com.agrolinq.app.models.Produtor;
import com.agrolinq.app.repository.ProdutoRepository;
import com.agrolinq.app.repository.ProdutorRepository;
import com.agrolinq.app.services.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutorRepository produtorRepository;

    @Override
    public ProdutoResponseDTO criar(ProdutoRequestDTO requestDTO, UUID produtorId) {

        Produtor produtor = produtorRepository.findById(produtorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtor não encontrado"));

        Produto produto = new Produto();
        preencherProduto(produto, requestDTO);
        produto.setProdutor(produtor);

        produtoRepository.save(produto);

        return toResponseDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(UUID id, ProdutoRequestDTO requestDTO, UUID produtorId) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (!produto.getProdutor().getId().equals(produtorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para editar este produto");
        }

        preencherProduto(produto, requestDTO);
        produto.setUpdatedAt(LocalDateTime.now());

        produtoRepository.save(produto);

        return toResponseDTO(produto);
    }

    @Override
    public ProdutoResponseDTO buscarPorId(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return toResponseDTO(produto);
    }

    @Override
    public List<ProdutoResponseDTO> listarPorProdutor(UUID produtorId) {
        return produtoRepository.findByProdutor_Id(produtorId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProdutoResponseDTO> listarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public void deletar(UUID id, UUID produtorId) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (!produto.getProdutor().getId().equals(produtorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para editar este produto");
        }

        produtoRepository.delete(produto);
    }

    private void preencherProduto(Produto produto, ProdutoRequestDTO requestDTO) {
        produto.setNome(requestDTO.getNome());
        produto.setDescricao(requestDTO.getDescricao());
        produto.setPreco(requestDTO.getPreco());
        produto.setImagemUrl(requestDTO.getImagemUrl());
        produto.setCategoria(requestDTO.getCategoria());
        produto.setEstoque(requestDTO.getEstoque());
        produto.setUnidade(requestDTO.getUnidade());
        produto.setDescricaoDetalhada(requestDTO.getDescricaoDetalhada());
        produto.setOrigem(requestDTO.getOrigem());
        produto.setCertificacoes(requestDTO.getCertificacoes());
        produto.setImagensAdicionais(requestDTO.getImagensAdicionais());
        produto.setEspecificacoes(requestDTO.getEspecificacoes());
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getImagemUrl(),
                produto.getCategoria(),
                produto.getProdutor().getId().toString(),
                produto.getProdutor().getNome(),
                produto.getEstoque(),
                produto.getUnidade(),
                produto.getDescricaoDetalhada(),
                produto.getOrigem(),
                produto.getCertificacoes(),
                produto.getImagensAdicionais(),
                produto.getEspecificacoes(),
                produto.getCreatedAt(),
                produto.getUpdatedAt()
        );
    }

}
