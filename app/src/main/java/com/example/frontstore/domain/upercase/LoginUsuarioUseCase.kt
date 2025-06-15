package com.example.frontstore.domain.upercase

import com.example.frontstore.data.model.BodyResponse
import com.example.frontstore.data.model.loginAuth
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class LoginUsuarioUseCase @Inject constructor(
    private val repo: UsuarioRepository
) {
    suspend operator fun invoke(loginAuth: loginAuth): BodyResponse {
        return repo.login(loginAuth)
    }
}
