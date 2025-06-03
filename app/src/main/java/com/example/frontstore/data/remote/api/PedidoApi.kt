package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.PedidoDto

interface PedidoApi {
    @POST("pedidos")
    suspend fun createPedido(@Body body: PedidoDto): PedidoDto

    @GET("pedidos/usuario/{usuarioId}")
    suspend fun getPedidosPorUsuario(@Path("usuarioId") usuarioId: String): List<PedidoDto>
}
