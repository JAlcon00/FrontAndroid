package com.example.frontstore.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel
import com.example.frontstore.presentation.viewmodel.CategoriaViewModel
import com.example.frontstore.ui.theme.PricePurple

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

// --- Pantalla principal previewable ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaArticulosScreenPreviewable(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    val viewModel : ArticuloViewModel = hiltViewModel()
    val categoriaViewModel : CategoriaViewModel = hiltViewModel()

    val articulos by viewModel.articulos.collectAsState()
    val categorias by categoriaViewModel.categorias.collectAsState()

    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    var categoriaSeleccionada by remember { mutableStateOf<String?>(null) }
    val articulosFiltrados = articulos.filter { articulo ->
        categoriaSeleccionada == null || articulo.categoria == categoriaSeleccionada
    }

    LaunchedEffect(key1 = true) {
        viewModel.loadArticulos()
        categoriaViewModel.loadCategorias()
    }

    val articuloRandom = remember {
        if (articulos.isNotEmpty()) articulos.random() else null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FontStore",
                color = Color(0xFF6200EE), // Verde brillante (hex)
                fontSize = 30.sp,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFCDC6D9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Ir",
                    tint = Color(0xFF6200EE)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar...") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color(0xFF6200EE),
                cursorColor = Color(0xFF6200EE),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray,
                containerColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Text(
                    text = error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = articuloRandom?.imagenes[0],
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Categorías",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 2.dp, bottom = 8.dp)
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(categorias) { categoria ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFE0E0E0)) // Fondo gris
                                .height(35.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable { categoriaSeleccionada = categoria.id }
                        ) {
                            Text(
                                text = categoria.nombre,
                                color = Color.Black // Asegura contraste
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(articulosFiltrados) { articulo ->
                        CardArticulo(
                            titulo = articulo.nombre,
                            imagenUrl = articulo.imagenes[0],
                            precio = articulo.precio,
                            onClick = {
                                navController.navigate("articulo/${articulo._id}")
                            }
                        )
                    }
                }
            }
        }

    }
}


// --- Preview ---
@Preview(showBackground = true)
@Composable
fun ListaArticulosScreenPreview() {

//    ListaArticulosScreenPreviewable(uiState = mockUiState)
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
