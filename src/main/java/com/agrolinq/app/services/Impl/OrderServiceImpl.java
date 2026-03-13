package com.agrolinq.app.services.Impl;

import com.agrolinq.app.dtos.OrderItemResponseDTO;
import com.agrolinq.app.dtos.OrderRequestDTO;
import com.agrolinq.app.dtos.OrderResponseDTO;
import com.agrolinq.app.models.*;
import com.agrolinq.app.models.enums.CanceladoPor;
import com.agrolinq.app.models.enums.OrderStatus;
import com.agrolinq.app.repository.ConsumidorRepository;
import com.agrolinq.app.repository.OrderRepository;
import com.agrolinq.app.repository.ProdutoRepository;
import com.agrolinq.app.repository.ProdutorRepository;
import com.agrolinq.app.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ConsumidorRepository consumidorRepository;
    private final ProdutorRepository produtorRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public OrderResponseDTO criar(OrderRequestDTO requestDTO, UUID consumidorId) {
        Consumidor consumidor = consumidorRepository.findById(consumidorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consumidor não encontrado"));

        Produtor produtor = produtorRepository.findById(requestDTO.getProdutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtor não encontrado"));

        Order order = new Order();
        order.setConsumidor(consumidor);
        order.setProdutor(produtor);
        order.setStatus(OrderStatus.NOVO);

        List<OrderItem> itens = requestDTO.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado: " + itemDTO.getProdutoId()));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduto(produto);
            item.setNome(itemDTO.getNome());
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(itemDTO.getPrecoUnitario());
            return item;
        }).toList();

        order.setItens(itens);
        order.setTotal(itens.stream()
                .mapToDouble(i -> i.getQuantidade() * i.getPrecoUnitario())
                .sum());

        orderRepository.save(order);
        return toResponseDTO(order);
    }

    @Override
    public OrderResponseDTO buscarPorId(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));

        return toResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> listarPorConsumidor(UUID consumidorId) {
        return orderRepository.findByConsumidor_Id(consumidorId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> listarPorProdutor(UUID produtorId) {
        return orderRepository.findByProdutor_Id(produtorId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO autalizarStatus(UUID id, OrderStatus status, UUID produtorId) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (!order.getProdutor().getId().equals(produtorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para alterar este pedido");
        }

        if (order.getStatus() == OrderStatus.CANCELADO || order.getStatus() == OrderStatus.CONCLUIDO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível atualizar um pedido cancelado ou concluído.");
        }

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);
        return toResponseDTO(order);
    }

    @Override
    public OrderResponseDTO cancelar(UUID id, UUID userId, String motivo) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (order.getStatus() == OrderStatus.CANCELADO || order.getStatus() == OrderStatus.CONCLUIDO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é cancelar este pedido.");
        }

        if (order.getConsumidor().getId().equals(userId)) {
            order.setCanceladoPor(CanceladoPor.CONSUMIDOR);
        } else if (order.getProdutor().getId().equals(userId)) {
            order.setCanceladoPor(CanceladoPor.PRODUTOR);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para cancelar este pedido.");
        }

        order.setStatus(OrderStatus.CANCELADO);
        order.setMotivoCancelamento(motivo);
        order.setCanceladoEm(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);
        return toResponseDTO(order);
    }

    @Override
    public OrderResponseDTO avaliar(UUID id, Integer nota, String comentario, UUID consumidorId) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (!order.getProdutor().getId().equals(consumidorId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para alterar este pedido");
        }

        if (order.getStatus() != OrderStatus.CONCLUIDO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível avaliar pedidos concluídos.");
        }

        if (order.getAvaliacaoNota() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este pedido já foi avaliado.");
        }

        order.setAvaliacaoNota(nota);
        order.setAvaliacaoComentario(comentario);
        order.setAvaliadoEm(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);

        return toResponseDTO(order);
    }

    // Helper method

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> itensDTO = order.getItens().stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario()
                ))
                .toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getConsumidor().getId().toString(),
                order.getConsumidor().getNome(),
                order.getProdutor().getId().toString(),
                order.getProdutor().getNome(),
                itensDTO,
                order.getTotal(),
                order.getStatus(),
                order.getProdutorNotificado(),
                order.getAvaliacaoNota(),
                order.getAvaliacaoComentario(),
                order.getAvaliadoEm(),
                order.getCanceladoPor(),
                order.getMotivoCancelamento(),
                order.getCanceladoEm(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
