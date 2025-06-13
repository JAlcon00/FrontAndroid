package com.example.frontstore.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.frontstore.presentation.components.CardArticulo
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel

// --- Modelo y estado ---
data class Articulo(val id: String, val titulo: String, val imagenUrl: String, val precio: Double)

data class ArticuloUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val articulos: List<Articulo> = emptyList()
)




// --- Componente de tarjeta ---
@Composable
fun CardArticulo(
    titulo: String,
    imagenUrl: String,
    precio: Double,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(2.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Ajusta la altura según lo que necesites
                    .padding(2.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imagenUrl),
                    contentDescription = titulo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = titulo, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${precio}", style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }

}







//@Composable
//fun ListaArticulosScreen(
//    navController: NavController,
//    viewModel: ArticuloViewModel = hiltViewModel()
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.cargarArticulos()
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        when {
//            uiState.loading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//            }
//            uiState.error != null -> {
//                Column(
//                    modifier = Modifier.align(Alignment.Center),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = uiState.error, color = MaterialTheme.colorScheme.error)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Button(onClick = { viewModel.cargarArticulos() }) {
//                        Text("Reintentar")
//                    }
//                }
//            }
//            uiState.articulos.isNotEmpty() -> {
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    items(uiState.articulos.size) { idx ->
//                        val articulo = uiState.articulos[idx]
//                        CardArticulo(
//                            titulo = articulo.titulo,
//                            imagenUrl = articulo.imagenUrl,
//                            precio = articulo.precio,
//                            onClick = {
//                                navController.navigate("detalleArticulo/${articulo.id}")
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
