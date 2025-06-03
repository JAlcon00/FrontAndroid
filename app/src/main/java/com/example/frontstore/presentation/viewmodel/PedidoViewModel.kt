package com.example.frontstore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.domain.model.Pedido
import com.example.frontstore.domain.upercase.CreatePedidoUseCase
import com.example.frontstore.domain.upercase.GetPedidosPorUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PedidoViewModel @Inject constructor(
    private val createPedidoUseCase: CreatePedidoUseCase,
    private val getPedidosPorUsuarioUseCase: GetPedidosPorUsuarioUseCase
) : ViewModel() {

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos: StateFlow<List<Pedido>> = _pedidos.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadPedidos(usuarioId: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val list = getPedidosPorUsuarioUseCase(usuarioId)
                _pedidos.value = list
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar pedidos"
            } finally {
                _loading.value = false
            }
        }
    }

    fun createPedido(pedido: Pedido) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                createPedidoUseCase(pedido)
                // Luego de crear, recargar la lista:
                loadPedidos(pedido.usuarioId)
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al crear pedido"
            } finally {
                _loading.value = false
            }
        }
    }
}
