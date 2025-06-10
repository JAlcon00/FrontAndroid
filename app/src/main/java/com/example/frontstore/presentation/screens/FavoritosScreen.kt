package com.example.frontstore.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.frontstore.ui.theme.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator

@Composable
fun FavoritosScreen(
    viewModel: ArticuloViewModel = hiltViewModel()
) {
    val articulos by viewModel.articulos.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val favoritos = articulos.filter { it.activo /* o tu lógica de favorito */ }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DeepPurple, Violet, Color.Transparent)
                    )
                )
                .align(Alignment.TopCenter)
        )
        Text(
            text = "Tus favoritos",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        )
        when {
            loading -> {
                CircularProgressIndicator(
                    color = PricePurple,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            error != null -> {
                Text(
                    text = error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            favoritos.isEmpty() -> {
                Text(
                    text = "No tienes favoritos aún",
                    color = PricePurple,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 180.dp)
                ) {
                    items(favoritos) { articulo ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = CardBg,
                            shadowElevation = 6.dp,
                            border = null,
                            modifier = Modifier
                                .width(220.dp)
                                .height(170.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .background(Violet, RoundedCornerShape(12.dp))
                                ) {
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = articulo.nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = articulo.descripcion,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary,
                                    maxLines = 2
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$${articulo.precio}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = PricePurple
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritosScreenPreview() {
    MaterialTheme {
        FavoritosScreen()
    }
}
