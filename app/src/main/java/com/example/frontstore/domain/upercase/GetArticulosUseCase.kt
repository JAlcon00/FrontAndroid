package com.example.frontstore.domain.upercase

import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.repository.ArticuloRepository
import javax.inject.Inject

class GetArticulosUseCase @Inject constructor(
    private val repo: ArticuloRepository
) {
    suspend operator fun invoke(): List<Articulo> {
        return repo.getArticulos()
    }
}
