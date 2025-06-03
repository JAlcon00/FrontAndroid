package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.LoginResponseDto
import com.example.frontstore.data.model.UsuarioDto

interface UsuarioApi {
    @POST("usuarios/login")
    suspend fun loginUsuario(@Body body: Map<String, String>): LoginResponseDto
    //    body contiene keys "email" y "password"


    @POST("usuarios")
    suspend fun registerUsuario(@Body body: UsuarioDto): UsuarioDto
    //   puedes crear un DTO específico para registro si necesitas menos campos.
}
