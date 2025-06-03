package com.example.frontstore.domain.upercase

import com.example.frontstore.domain.model.Pedido
import com.example.frontstore.domain.repository.PedidoRepository
import javax.inject.Inject

class GetPedidosPorUsuarioUseCase @Inject constructor(
    private val repo: PedidoRepository
) {
    suspend operator fun invoke(usuarioId: String): List<Pedido> {
        return repo.getPedidosPorUsuario(usuarioId)
    }
}
