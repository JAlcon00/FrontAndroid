package com.example.frontstore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.upercase.LoginUsuarioUseCase
import com.example.frontstore.domain.upercase.RegisterUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUsuarioUseCase,
    private val registerUseCase: RegisterUsuarioUseCase
) : ViewModel() {
    private val _user = MutableStateFlow<Usuario?>(null)
    val user: StateFlow<Usuario?> = _user.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val u = loginUseCase(email, password)
                _user.value = u
            } catch (e: Exception) {
                _error.value = e.message ?: "Credenciales inválidas"
            } finally {
                _loading.value = false
            }
        }
    }

    fun register(nombre: String, email: String, password: String, direccion: String, telefono: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val nuevo = Usuario(
                    id = "", // la API lo asigna
                    nombre = nombre,
                    email = email,
                    direccion = direccion,
                    telefono = telefono,
                    rol = "cliente"
                )
                val u = registerUseCase(nuevo)
                _user.value = u
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al registrar"
            } finally {
                _loading.value = false
            }
        }
    }
}
