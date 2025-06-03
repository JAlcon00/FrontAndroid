// app/src/main/java/com/example/frontstore/MainActivity.kt
package com.example.frontstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.frontstore.ui.theme.FrontStoreTheme
import com.example.frontstore.navigation.AppNavHost

/**
 * MainActivity: punto de entrada de la app.
 * @AndroidEntryPoint habilita la inyección de dependencias con Hilt.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate: MainActivity iniciado")

        setContent {
            FrontStoreTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // Aquí arrancamos la navegación entre pantallas
                    AppNavHost()
                }
            }
        }
    }
}
