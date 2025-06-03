package com.example.frontstore.data.model

data class PedidoDto(
    val _id: String,
    val usuario: String,
    val detalles: List<DetallePedidoDto>,
    val total: Double,
    val estado: String,           // 'pendiente', 'confirmado', etc.
    val direccionEntrega: String,
    val fechaCreacion: String,
    val fechaActualizacion: String,
    val activo: Boolean
)
