package com.example.bitcoinapp.presentation.screens.detalle

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel

@Composable
fun DetalleArticuloScreen(articuloId : String) {
    val articuloViewModel : ArticuloViewModel = hiltViewModel()

    val loading = articuloViewModel.loading.collectAsState()
    val error = articuloViewModel.error.collectAsState()

    val articulo = articuloViewModel.articulo.collectAsState().value

    LaunchedEffect(key1 = true) {
        articuloViewModel.loadArticuloById(articuloId)
    }

    Log.e("DetalleArticuloScreen", "articuloId: $articulo")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        // Encabezado con íconos
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Details",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Carrito",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            loading.value -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error.value != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error.value ?: "Error desconocido",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            else -> {
                // Imagen del producto en un contenedor
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = articulo?.imagenes[0]),
                        contentDescription = "Imagen del artículo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Icono de corazón en la esquina inferior derecha
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorito",
                            tint = Color.Red
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = articulo?.nombre.toString(), style = MaterialTheme.typography.titleMedium, fontSize = 30.sp)
                Text(text = "$${articulo?.precio}", style = MaterialTheme.typography.titleLarge, color = Color(0xFF6200EE))
                Text(
                    text = "${articulo?.descripcion}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                )

                Button(
                    onClick = { /* Acción para agregar al carrito */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add to Cart", color = Color.White)
                }
            }
        }


    }
}

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
