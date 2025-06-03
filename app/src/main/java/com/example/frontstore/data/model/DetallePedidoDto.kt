package com.example.frontstore.data.model

data class DetallePedidoDto(
    val articulo: String,       // id del artículo
    val cantidad: Int,
    val precioUnitario: Double,
    val subtotal: Double
)
