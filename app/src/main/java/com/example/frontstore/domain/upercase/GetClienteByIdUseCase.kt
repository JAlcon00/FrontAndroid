package com.example.frontstore.domain.upercase

import android.util.Log
import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.domain.repository.ClienteRepository
import javax.inject.Inject

class GetClienteByIdUseCase @Inject constructor(
    private val repo : ClienteRepository
) {
    suspend operator fun invoke(id: String) : UsuarioDto {
        return repo.getClienteById(id)
    }
}