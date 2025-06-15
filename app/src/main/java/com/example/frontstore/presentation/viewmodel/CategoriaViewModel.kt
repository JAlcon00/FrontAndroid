package com.example.frontstore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.domain.model.Categoria
import com.example.frontstore.domain.upercase.GetCategoriasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val getCategoriasUseCase: GetCategoriasUseCase
) : ViewModel() {
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadCategorias() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val categoriaList = getCategoriasUseCase().map { categoriaDto ->
                    Categoria(
                        id = categoriaDto._id,
                        nombre = categoriaDto.nombre,
                        descripcion = categoriaDto.descripcion,
                        fechaCreacion = categoriaDto.fechaCreacion,
                        activo = categoriaDto.activo,
                    )
                }
                _categorias.value = categoriaList
                Log.d("CategoriaViewModel", "Categorías actualizadas en _categorias: ${_categorias.value}")
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar categorías"
            } finally {
                _loading.value = false
            }
        }
    }
}
