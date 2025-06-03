package com.example.frontstore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.upercase.GetArticulosPorCategoriaUseCase
import com.example.frontstore.domain.upercase.GetArticulosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticuloViewModel @Inject constructor(
    private val getArticulosUseCase: GetArticulosUseCase,
    private val getArticulosPorCategoriaUseCase: GetArticulosPorCategoriaUseCase
) : ViewModel() {

    private val _articulos = MutableStateFlow<List<Articulo>>(emptyList())
    val articulos: StateFlow<List<Articulo>> = _articulos.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadArticulos() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val list = getArticulosUseCase()
                _articulos.value = list
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar productos"
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadArticulosPorCategoria(categoriaId: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val list = getArticulosPorCategoriaUseCase(categoriaId)
                _articulos.value = list
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al filtrar productos"
            } finally {
                _loading.value = false
            }
        }
    }
}
