package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.BodyResponse
import com.example.frontstore.data.model.LoginResponseDto
import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.data.model.loginAuth
import com.example.frontstore.data.model.registerAuth
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("auth/login/cliente")
    suspend fun loginUsuario(@Body loginAuth: loginAuth): BodyResponse
    //    body contiene keys "email" y "password"


    @POST("auth/registro/cliente")
    suspend fun registerUsuario(@Body auth: registerAuth): BodyResponse
    //   puedes crear un DTO específico para registro si necesitas menos campos.
}
