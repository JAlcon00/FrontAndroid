//package com.example.frontstore.presentation.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
//
//@Composable
//fun DetalleArticuloScreen(
//    navController: NavController,
//    idArticulo: String,
//    viewModel: DetalleArticuloViewModel = hiltViewModel()
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    LaunchedEffect(idArticulo) {
//        viewModel.cargarArticulo(idArticulo)
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
//                    Button(onClick = { viewModel.cargarArticulo(idArticulo) }) {
//                        Text("Reintentar")
//                    }
//                }
//            }
//            uiState.articulo != null -> {
//                val articulo = uiState.articulo
//                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//                    Image(
//                        painter = rememberAsyncImagePainter(articulo.imagenUrl),
//                        contentDescription = null,
//                        modifier = Modifier.fillMaxWidth().height(220.dp),
//                        contentScale = ContentScale.Crop
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = articulo.titulo, style = MaterialTheme.typography.headlineSmall)
//                    Text(text = articulo.descripcion, style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Precio: ${articulo.precio}", style = MaterialTheme.typography.titleMedium)
//                    Text(text = "Stock: ${articulo.stock}", style = MaterialTheme.typography.bodySmall)
//                    Spacer(modifier = Modifier.height(24.dp))
//                    Row {
//                        Button(onClick = { viewModel.agregarAFavoritos(articulo.id) }) {
//                            Text("Agregar a favoritos")
//                        }
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Button(onClick = { /* lógica compartir */ }) {
//                            Text("Compartir")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
