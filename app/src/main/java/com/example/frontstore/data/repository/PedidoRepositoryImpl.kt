package com.example.frontstore.data.repository

import com.example.frontstore.data.model.DetallePedidoDto
import com.example.frontstore.data.model.PedidoDto
import com.example.frontstore.data.remote.api.PedidoApi
import com.example.frontstore.domain.model.DetallePedido
import com.example.frontstore.domain.model.Pedido
import com.example.frontstore.domain.repository.PedidoRepository
import javax.inject.Inject

class PedidoRepositoryImpl @Inject constructor(
    private val api: PedidoApi
) : PedidoRepository {

    override suspend fun createPedido(pedido: Pedido): Pedido {
        val dto = PedidoDto(
            _id = "",                     // la API lo ignorará
            usuario = pedido.usuarioId,
            detalles = pedido.detalles.map { detalle ->
                DetallePedidoDto(
                    articulo = detalle.articuloId,
                    cantidad = detalle.cantidad,
                    precioUnitario = detalle.precioUnitario,
                    subtotal = detalle.subtotal
                )
            },
            total = pedido.total,
            estado = pedido.estado,
            direccionEntrega = pedido.direccionEntrega,
            fechaCreacion = "",           // la API lo rellenará
            fechaActualizacion = "",      // la API lo rellenará
            activo = true
        )
        val created = api.createPedido(dto)
        return Pedido(
            id = created._id,
            usuarioId = created.usuario,
            detalles = created.detalles.map { d ->
                DetallePedido(
                    articuloId = d.articulo,
                    cantidad = d.cantidad,
                    precioUnitario = d.precioUnitario,
                    subtotal = d.subtotal
                )
            },
            total = created.total,
            estado = created.estado,
            direccionEntrega = created.direccionEntrega,
            activo = created.activo
        )
    }

    override suspend fun getPedidosPorUsuario(usuarioId: String): List<Pedido> {
        val dtos = api.getPedidosPorUsuario(usuarioId)
        return dtos.map { dto ->
            Pedido(
                id = dto._id,
                usuarioId = dto.usuario,
                detalles = dto.detalles.map { d ->
                    DetallePedido(
                        articuloId = d.articulo,
                        cantidad = d.cantidad,
                        precioUnitario = d.precioUnitario,
                        subtotal = d.subtotal
                    )
                },
                total = dto.total,
                estado = dto.estado,
                direccionEntrega = dto.direccionEntrega,
                activo = dto.activo
            )
        }
    }
}
