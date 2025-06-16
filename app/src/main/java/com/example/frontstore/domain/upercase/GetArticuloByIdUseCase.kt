package com.example.frontstore.domain.upercase

import com.example.frontstore.data.model.ArticuloDto
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.repository.ArticuloRepository
import javax.inject.Inject

class GetArticuloByIdUseCase @Inject constructor(
    private val repo: ArticuloRepository
) {
    suspend operator fun invoke(id: String): ArticuloDto {
        return repo.getArticuloById(id)
    }
}
