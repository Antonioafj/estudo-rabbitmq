package com.antonio.pedido.controller;


import com.antonio.pedido.model.Pedido;
import com.antonio.pedido.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
 public class PedidoController {

    private final PedidoService pedidoService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("", routingKey, pedidoSalvo);

        return "Pedido saldo e enviado para processamento: "+ pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }
}
