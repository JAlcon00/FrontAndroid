// app/src/main/java/com/example/frontstore/MainActivity.kt
package com.example.frontstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.frontstore.domain.upercase.GetArticulosUseCase
import com.example.frontstore.ui.theme.FrontStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @AndroidEntryPoint habilita la inyección de dependencias con Hilt.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Inyectamos el use case que obtiene todos los artículos
    @Inject
    lateinit var getArticulosUseCase: GetArticulosUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1) En el onCreate lanzamos una corrutina para llamar a la API y loguear resultados
        lifecycleScope.launch {
            try {
                // Llamada a la API (GetArticulosUseCase ya mapea DTO → model)
                val listaArticulos = getArticulosUseCase()
                Log.i("MainActivity", "Artículos recibidos: ${listaArticulos.size}")
                // Opcional: imprime el nombre del primer artículo, si existe
                if (listaArticulos.isNotEmpty()) {
                    Log.i("MainActivity", "Primer artículo: ${listaArticulos[0].nombre}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener artículos: ${e.message}")
            }
        }

        // 2) El Content normal de tu Compose
        setContent {
            FrontStoreTheme {
                // Aquí iría tu contenido Compose, por ejemplo:
                // HomeScreen() o cualquier otra pantalla que tengas
            }
        }
    }
}
