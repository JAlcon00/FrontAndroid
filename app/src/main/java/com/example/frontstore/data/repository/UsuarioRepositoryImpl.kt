package com.example.frontstore.data.repository

import android.util.Log
import com.example.frontstore.data.model.BodyResponse
import com.example.frontstore.data.model.Cliente
import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.data.model.loginAuth
import com.example.frontstore.data.model.registerAuth
import com.example.frontstore.data.remote.api.UsuarioApi
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val api: UsuarioApi
) : UsuarioRepository {
    override suspend fun login(loginAuth: loginAuth): BodyResponse {
        try {
            val loginAuth = loginAuth(
                email = loginAuth.email,
                password = loginAuth.password
            )
            val response = api.loginUsuario(loginAuth)
            return BodyResponse(
                message = "Login exitoso",
                cliente = Cliente(
                    id = response.cliente.id,
                    nombre = response.cliente.nombre,
                    email = response.cliente.email
                )
            )
        } catch (e: retrofit2.HttpException) {
            // Registrar el error HTTP
            Log.e("UsuarioRepositoryImpl", "Error HTTP: ${e.response()?.errorBody()?.string()}")
            throw Exception("Error en el login: ${e.message}")
        } catch (e: Exception) {
            // Registrar errores desconocidos
            Log.e("UsuarioRepositoryImpl", "Error desconocido: ${e.message}")
            throw Exception("Error desconocido: ${e.message}")
        }
    }

    override suspend fun register(auth: registerAuth): BodyResponse {
        try {
            // Convertir registerAuth → UsuarioDto para enviar a la API
            val registerAuth = registerAuth(
                nombre = auth.nombre,
                apellido = auth.apellido,
                email = auth.email,
                password = auth.password,
                direccion = auth.direccion,
                telefono = auth.telefono
            )
            val response = api.registerUsuario(registerAuth)
            return BodyResponse(
                message = "Cliente registrado exitosamente",
                cliente = Cliente(
                    id = response.cliente.id,
                    nombre = response.cliente.nombre,
                    email = response.cliente.email
                )
            )
        } catch (e: retrofit2.HttpException) {
            // Registrar el error HTTP
            Log.e("UsuarioRepositoryImpl", "Error HTTP: ${e.response()?.errorBody()?.string()}")
            throw Exception("Error en el registro: ${e.message}")
        } catch (e: Exception) {
            // Registrar errores desconocidos
            Log.e("UsuarioRepositoryImpl", "Error desconocido: ${e.message}")
            throw Exception("Error desconocido: ${e.message}")
        }
    }
}
