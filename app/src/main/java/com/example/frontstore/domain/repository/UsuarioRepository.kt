package com.example.frontstore.domain.repository

import com.example.frontstore.data.model.BodyResponse
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.data.model.registerAuth
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioRepository {
    suspend fun login(email: String, password: String): Usuario

    @POST("auth/registro/cliente")
    suspend fun register(@Body auth : registerAuth): BodyResponse
}
