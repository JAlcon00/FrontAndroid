package com.example.frontstore.domain.upercase

import android.util.Log
import com.example.frontstore.data.model.ArticuloDto
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.repository.ArticuloRepository
import javax.inject.Inject

class GetArticulosUseCase @Inject constructor(
    private val repo: ArticuloRepository
) {
    suspend operator fun invoke(): List<ArticuloDto> {
        val articulos = repo.getArticulos()
        Log.d("GetArticulosUseCase", "Artículos obtenidos del repositorio: $articulos")
        return articulos
    }
}
