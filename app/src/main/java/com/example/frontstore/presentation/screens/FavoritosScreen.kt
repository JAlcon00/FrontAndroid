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



@Composable
fun FavoritosScreen() {
    val favoritosAnime = listOf(
        Triple(
            "Playera Naruto",
            "https://via.placeholder.com/150",
            "$299" to "Playera de algodón con estampado de Naruto. Esta playera es perfecta para los fans del anime, cómoda para el día a día y con un diseño exclusivo que destaca entre la multitud. ¡No te quedes sin la tuya y muestra tu pasión por Naruto en cualquier ocasión!"
        ),
        Triple(
            "Sudadera One Piece",
            "https://via.placeholder.com/150",
            "$599" to "Sudadera cómoda con logo de One Piece."
        ),
        Triple(
            "Taza Attack on Titan",
            "https://via.placeholder.com/150",
            "$199" to "Taza de cerámica edición especial AOT."
        )
    )
    val favoritosPeliculas = listOf(
        Triple(
            "Camiseta Star Wars",
            "https://via.placeholder.com/150",
            "$349" to "Camiseta oficial de Star Wars, edición limitada."
        ),
        Triple(
            "Taza Harry Potter",
            "https://via.placeholder.com/150",
            "$179" to "Taza mágica con el logo de Hogwarts."
        ),
        Triple(
            "Gorra Marvel",
            "https://via.placeholder.com/150",
            "$259" to "Gorra ajustable con logo de Marvel."
        )
    )
    val favoritosVideojuegos = listOf(
        Triple(
            "GTA 6",
            "https://via.placeholder.com/150",
            "$1599" to "Grand Theft Auto VI para PS5/Xbox, la nueva entrega de la saga más exitosa de mundo abierto. ¡Reserva ya y sé de los primeros en jugar!"
        ),
        Triple(
            "Playera Zelda",
            "https://via.placeholder.com/150",
            "$329" to "Playera oficial de The Legend of Zelda, edición Breath of the Wild."
        ),
        Triple(
            "Taza Mario Bros",
            "https://via.placeholder.com/150",
            "$189" to "Taza de cerámica con diseño de Mario y Luigi."
        ),
        Triple(
            "Gorra Pokémon",
            "https://via.placeholder.com/150",
            "$249" to "Gorra ajustable con logo de Pikachu."
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // Fondo gradiente superior
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
        // Título principal
        Text(
            text = "Tus favoritos",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        )
        // Carruseles apilados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 180.dp)
        ) {
            Text(
                text = "Favoritos de anime",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favoritosAnime) { (titulo, imagenUrl, precioDesc) ->
                    val (precio, descripcion) = precioDesc
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
                                // Aquí iría la imagen
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = titulo,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = descripcion,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = precio,
                                style = MaterialTheme.typography.bodyLarge,
                                color = PricePurple
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Favoritos de películas",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favoritosPeliculas) { (titulo, imagenUrl, precioDesc) ->
                    val (precio, descripcion) = precioDesc
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
                                // Aquí iría la imagen
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = titulo,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = descripcion,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = precio,
                                style = MaterialTheme.typography.bodyLarge,
                                color = PricePurple
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Videojuegos",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favoritosVideojuegos) { (titulo, imagenUrl, precioDesc) ->
                    val (precio, descripcion) = precioDesc
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
                                // Aquí iría la imagen
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = titulo,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = descripcion,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = precio,
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

@Preview(showBackground = true)
@Composable
fun FavoritosScreenPreview() {
    MaterialTheme {
        FavoritosScreen()
    }
}
