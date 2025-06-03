package com.example.frontstore.domain.upercase

import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class LoginUsuarioUseCase @Inject constructor(
    private val repo: UsuarioRepository
) {
    suspend operator fun invoke(email: String, password: String): Usuario {
        return repo.login(email, password)
    }
}
