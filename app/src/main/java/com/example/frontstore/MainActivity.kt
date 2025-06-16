// app/src/main/java/com/example/frontstore/MainActivity.kt
package com.example.frontstore

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontstore.domain.upercase.GetArticulosUseCase
import com.example.frontstore.presentation.navigation.Screens
import com.example.frontstore.presentation.screens.ListaArticulosScreenPreviewable
import com.example.frontstore.presentation.screens.auth.LoginScreen
import com.example.frontstore.presentation.screens.auth.RegistroScreen
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        // 1) En el onCreate lanzamos una corrutina para llamar a la API y loguear resultados
//        lifecycleScope.launch {
//            try {
//                // Llamada a la API (GetArticulosUseCase ya mapea DTO → model)
//                val listaArticulos = getArticulosUseCase()
//                Log.i("MainActivity", "Artículos recibidos: ${listaArticulos.size}")
//                // Opcional: imprime el nombre del primer artículo, si existe
//                if (listaArticulos.isNotEmpty()) {
//                    Log.i("MainActivity", "Primer artículo: ${listaArticulos[0].nombre}")
//                }
//            } catch (e: Exception) {
//                Log.e("MainActivity", "Error al obtener artículos: ${e.message}")
//            }
//        }

        // 2) El Content normal de tu Compose
        setContent {
            FrontStoreTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.LoginScreenRoute) {
                    composable<Screens.LoginScreenRoute> {
                        LoginScreen(navController = navController)
                    }
                    composable<Screens.RegisterScreenRoute> {
                        RegistroScreen(navController = navController)
                    }
                    composable<Screens.ListaArticulosScreenRoute> {
                        Scaffold(
                            bottomBar = {
                                NavigationBar {
                                    NavigationBarItem(
                                        selected = true,
                                        onClick = { /* Acción de navegación */ },
                                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                                        label = { Text("Inicio") }
                                    )
                                    // Puedes agregar más items aquí
                                }
                            }
                        ) { paddingValues ->
                            ListaArticulosScreenPreviewable(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

