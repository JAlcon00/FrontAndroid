package com.example.frontstore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontstore.data.model.loginAuth
import com.example.frontstore.data.model.registerAuth
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.upercase.LoginUsuarioUseCase
import com.example.frontstore.domain.upercase.RegisterUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUsuarioUseCase,
    private val registerUseCase: RegisterUsuarioUseCase
) : ViewModel() {
    private val _registerEvent = MutableSharedFlow<String>()
    val registerEvent = _registerEvent.asSharedFlow()

    private val _user = MutableStateFlow<Usuario?>(null)
    val user: StateFlow<Usuario?> = _user.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun login(email: String, password: String) {
        val loginAuth = loginAuth(
            email = email,
            password = password
        )
        viewModelScope.launch {

        }
    }

    fun register(nombre: String, apellido: String, email: String, password: String, direccion: String, telefono: String) {
        val auth = registerAuth(
            nombre = nombre,
            apellido = apellido,
            email = email,
            password = password,
            direccion = direccion,
            telefono = telefono
        )
        viewModelScope.launch {
            val response = registerUseCase(registerAuth = auth)
            if (response.message == "Cliente registrado exitosamente") {
                // Se registro correctamente
                _registerEvent.emit("Success")
            } else {
                // Hubo un error
                _registerEvent.emit(response.message)
            }
        }
    }
}
