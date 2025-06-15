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

    private val _loginEvent = MutableSharedFlow<String>()
    val loginEvent = _loginEvent.asSharedFlow()

    fun login(email: String, password: String) {
        val loginAuth = loginAuth(
            email = email,
            password = password
        )
        viewModelScope.launch {
            val response = loginUseCase(loginAuth = loginAuth)
            if (response.message == "Login exitoso") {
                // Se logueo correctamente
                _loginEvent.emit("Success")
            } else {
                // Hubo un error
                _loginEvent.emit(response.message)
            }
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
