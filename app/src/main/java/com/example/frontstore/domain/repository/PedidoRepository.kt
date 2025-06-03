package com.example.frontstore.domain.repository

import com.example.frontstore.domain.model.Pedido

interface PedidoRepository {
    suspend fun createPedido(pedido: Pedido): Pedido
    suspend fun getPedidosPorUsuario(usuarioId: String): List<Pedido>
}
