package com.example.frontstore.domain.upercase

import com.example.frontstore.data.model.CategoriaDto
import com.example.frontstore.domain.model.Categoria
import com.example.frontstore.domain.repository.CategoriaRepository
import javax.inject.Inject

class GetCategoriasUseCase @Inject constructor(
    private val repo: CategoriaRepository
) {
    suspend operator fun invoke(): List<CategoriaDto> {
        return repo.getCategorias()
    }
}
