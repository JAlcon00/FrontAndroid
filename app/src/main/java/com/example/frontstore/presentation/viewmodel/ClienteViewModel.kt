package com.example.frontstore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.domain.upercase.GetClienteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val getClienteByIdUseCase: GetClienteByIdUseCase
) : ViewModel() {
    private val _cliente = MutableStateFlow<UsuarioDto?>(null)
    val cliente: StateFlow<UsuarioDto?> = _cliente.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error.asStateFlow()

    fun loadClienteById(id: String) {
        viewModelScope.launch {
            if (id.isNullOrEmpty()) {
                Log.e("ClienteViewModel", "El ID proporcionado está vacío")
                _error.value = "El ID proporcionado está vacío"
            } else {
                Log.d("ClienteViewModel", "Cargando cliente por ID: $id")
                val clienteDto = getClienteByIdUseCase(id = id)
                try {
                    _cliente.value = UsuarioDto(
                        _id = clienteDto._id,
                        nombre = clienteDto.nombre,
                        apellido = clienteDto.apellido,
                        email = clienteDto.email,
                        password = clienteDto.password,
                        telefono = clienteDto.telefono,
                        direccion = clienteDto.direccion,
                        fechaCreacion = clienteDto.fechaCreacion,
                        fechaActualizacion = clienteDto.fechaActualizacion,
                        activo = clienteDto.activo,
                    )
                    Log.d("ClienteViewModel", "Cliente cargado: ${_cliente.value}")
                } catch (e: Exception) {
                    Log.e("ClienteViewModel", "Error al cargar cliente por ID: ${e.message}", e)
                    _error.value = e.message ?: "Error al cargar cliente"
                } finally {
                    _loading.value = false
                }
            }


        }
    }
}