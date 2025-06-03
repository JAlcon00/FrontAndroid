package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.PedidoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PedidoApi {
    @POST("pedidos")
    suspend fun createPedido(@Body body: PedidoDto): PedidoDto

    @GET("pedidos/usuario/{usuarioId}")
    suspend fun getPedidosPorUsuario(@Path("usuarioId") usuarioId: String): List<PedidoDto>
}
