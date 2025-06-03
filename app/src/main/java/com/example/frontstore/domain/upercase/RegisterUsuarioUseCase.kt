package com.example.frontstore.domain.upercase

import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class RegisterUsuarioUseCase @Inject constructor(
    private val repo: UsuarioRepository
) {
    suspend operator fun invoke(usuario: Usuario): Usuario {
        return repo.register(usuario)
    }
}
