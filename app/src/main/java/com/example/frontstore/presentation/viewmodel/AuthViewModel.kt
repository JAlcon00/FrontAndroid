package com.example.frontstore.presentation.viewmodel

import android.util.Log
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

    private val _userId = MutableStateFlow<String?>(null)
    val userId : StateFlow<String?> = _userId.asStateFlow()

    fun login(email: String, password: String) {
        val loginAuth = loginAuth(
            email = email,
            password = password
        )
        viewModelScope.launch {
            try {
                val response = loginUseCase(loginAuth = loginAuth)
                if (response.message == "Login exitoso") {
                    // Login exitoso
                    _loginEvent.emit("Success")
                    _userId.value = response.cliente.id
                } else {
                    // Login fallido, emitir mensaje genérico
                    _loginEvent.emit("Credenciales inválidas")
                }
            } catch (e: retrofit2.HttpException) {
                // Manejo de errores HTTP
                if (e.code() == 401) {
                    _loginEvent.emit("Credenciales inválidas")
                } else {
                    _loginEvent.emit("Error en el servidor: ${e.message}")
                }
            } catch (e: Exception) {
                // Manejo de excepciones generales
                _loginEvent.emit("Error desconocido: ${e.message}")
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
                _registerEvent.emit("Error al registrar")
            }
        }
    }
}
