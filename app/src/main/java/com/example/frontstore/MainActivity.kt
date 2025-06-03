package com.example.frontstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontstore.presentation.viewmodel.ArticuloViewModel
import com.example.frontstore.ui.theme.FrontStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val articuloViewModel: ArticuloViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Lanza la carga de artículos apenas se monte este Composable
            LaunchedEffect(Unit) {
                articuloViewModel.loadArticulos()
            }

            // Tu UI existente puede quedar aquí; por ejemplo:
            Column {
                if (articuloViewModel.loading.collectAsState().value) {
                    Text(text = "Cargando artículos…")
                } else if (articuloViewModel.error.collectAsState().value != null) {
                    Text(text = "Error: ${articuloViewModel.error.collectAsState().value}")
                } else {
                    Text(text = "Artículos cargados → Revisa Logcat")
                    // (o tu UI real; aquí solo mostramos este mensaje para probar)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontStoreTheme {
        Greeting("Android")
    }
}