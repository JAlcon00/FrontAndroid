package com.example.frontstore.data.model

data class LoginResponseDto(
    val user: UsuarioDto,
    // Si en el futuro implementas token JWT, aquí podrías agregar: val token: String
)

