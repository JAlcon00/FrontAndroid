package com.example.frontstore.domain.upercase

import com.example.frontstore.domain.model.Pedido
import com.example.frontstore.domain.repository.PedidoRepository
import javax.inject.Inject

class CreatePedidoUseCase @Inject constructor(
    private val repo: PedidoRepository
) {
    suspend operator fun invoke(pedido: Pedido): Pedido {
        return repo.createPedido(pedido)
    }
}
