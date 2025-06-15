package com.example.frontstore.domain.upercase

import com.example.frontstore.data.model.BodyResponse
import com.example.frontstore.data.model.registerAuth
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class RegisterUsuarioUseCase @Inject constructor(
    private val repo: UsuarioRepository
) {
    suspend operator fun invoke(registerAuth: registerAuth): BodyResponse {
        return repo.register(registerAuth)
    }
}
