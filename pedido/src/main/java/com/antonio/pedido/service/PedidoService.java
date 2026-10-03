package com.antonio.pedido.service;

import com.antonio.pedido.model.ItemPedido;
import com.antonio.pedido.model.Pedido;
import com.antonio.pedido.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {


    private final PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido){

        if (pedido.getItens() != null){
            for (ItemPedido itemPedido : pedido.getItens() ) {
                itemPedido.setPedido(pedido);
            }
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

}
