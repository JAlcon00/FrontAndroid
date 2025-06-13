package com.example.frontstore.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontstore.presentation.components.CardArticulo
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel

@Composable
fun ListaArticulosScreen(
    navController: NavController,
    viewModel: ArticuloViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarArticulos()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = uiState.error, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.cargarArticulos() }) {
                        Text("Reintentar")
                    }
                }
            }
            uiState.articulos.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.articulos.size) { idx ->
                        val articulo = uiState.articulos[idx]
                        CardArticulo(
                            titulo = articulo.titulo,
                            imagenUrl = articulo.imagenUrl,
                            precio = articulo.precio,
                            onClick = {
                                navController.navigate("detalleArticulo/${articulo.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}
