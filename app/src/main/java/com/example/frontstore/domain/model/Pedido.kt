package com.example.frontstore.domain.model

data class Pedido(
    val id: String,
    val usuarioId: String,
    val detalles: List<DetallePedido>,
    val total: Double,
    val estado: String,
    val direccionEntrega: String,
    val activo: Boolean
)

data class DetallePedido(
    val articuloId: String,
    val cantidad: Int,
    val precioUnitario: Double,
    val subtotal: Double
)
