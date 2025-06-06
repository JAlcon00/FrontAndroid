package com.example.frontstore.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontstore.presentation.components.CardArticulo

@Composable
fun FavoritosScreen() {
    val favoritosEjemplo = listOf(
        Triple("Playera Naruto", "https://via.placeholder.com/150", "$299"),
        Triple("Sudadera One Piece", "https://via.placeholder.com/150", "$599"),
        Triple("Taza Attack on Titan", "https://via.placeholder.com/150", "$199")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Favoritos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favoritosEjemplo) { (titulo, imagenUrl, precio) ->
                CardArticulo(
                    titulo = titulo,
                    imagenUrl = imagenUrl,
                    precio = precio,
                    onClick = {}
                )
            }
        }
    }
}