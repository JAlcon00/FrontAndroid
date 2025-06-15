package com.example.frontstore.presentation.viewmodel

import android.util.Log
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
                val articulosList = getArticulosUseCase().map { articuloDto ->
                    Articulo(
                        _id = articuloDto._id,
                        nombre = articuloDto.nombre,
                        descripcion = articuloDto.descripcion,
                        precio = articuloDto.precio,
                        stock = articuloDto.stock,
                        categoria = articuloDto.categoria,
                        activo = articuloDto.activo,
                        imagenes = articuloDto.imagenes,
                        fechaCreacion = articuloDto.fechaCreacion
                    )
                }
                _articulos.value = articulosList
                Log.d("ArticuloViewModel", "Artículos actualizados en _articulos: ${_articulos.value}")
            } catch (e: Exception) {
                Log.e("ArticuloViewModel", "Error al cargar artículos: ${e.message}", e)
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
