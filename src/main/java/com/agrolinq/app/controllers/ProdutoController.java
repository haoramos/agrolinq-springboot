package com.agrolinq.app.controllers;

import com.agrolinq.app.config.AuthenticatedUserHelper;
import com.agrolinq.app.dtos.ProdutoRequestDTO;
import com.agrolinq.app.dtos.ProdutoResponseDTO;
import com.agrolinq.app.repository.ProdutoRepository;
import com.agrolinq.app.services.AuthService;
import com.agrolinq.app.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final AuthenticatedUserHelper  authenticatedUserHelper;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @RequestBody @Valid ProdutoRequestDTO requestDTO
    ) {
        UUID produtorId = authenticatedUserHelper.getAuthenticatedUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(requestDTO, produtorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid ProdutoRequestDTO requestDTO
    ){
        UUID produtorId = authenticatedUserHelper.getAuthenticatedUserId();
        return ResponseEntity.ok().body(produtoService.atualizar(id, requestDTO, produtorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @GetMapping("/produtor/{produtorId}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorProdutor(
            @PathVariable UUID produtorId
    ) {
        return ResponseEntity.ok().body(produtoService.listarPorProdutor(produtorId));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(
            @PathVariable String categoria
    ) {
        return ResponseEntity.ok().body(produtoService.listarPorCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(produtoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable UUID id
    ) {
        UUID produtorId = authenticatedUserHelper.getAuthenticatedUserId();
        produtoService.deletar(id, produtorId);
        return ResponseEntity.noContent().build();
    }

}
