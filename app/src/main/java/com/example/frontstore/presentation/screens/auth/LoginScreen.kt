package com.example.frontstore.presentation.screens.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontstore.presentation.navigation.Screens
import com.example.frontstore.presentation.viewmodel.AuthViewModel
import com.example.frontstore.ui.theme.Black
import com.example.frontstore.ui.theme.DeepPurple
import com.example.frontstore.ui.theme.PricePurple
import com.example.frontstore.ui.theme.Violet

@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val viewModel : AuthViewModel = hiltViewModel()

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        viewModel.loginEvent.collect { result ->
            Log.i("LoginScreen", "Recibiendo evento de login: $result")
            if (result != "Success") {
                // Ocurrio un error
                showErrorDialog = true
                errorMessage = result
            } else {
                // Esta correcto, navegar a la pantalla principal
                navController.navigate(Screens.ListaArticulosScreenRoute)
            }
        }
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            confirmButton = {
                TextButton( onClick = { showErrorDialog = false }) {
                    Text(text = "Aceptar")
                }
            },
            title = { Text(text = "Error" )},
            text = { Text(text = errorMessage )}
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Logo
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                // Aquí puedes poner tu Image
                // Ejemplo: Image(painterResource(id = R.drawable.tu_logo), contentDescription = "Logo")
            }
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = PricePurple // Morado claro
            )
            Text(
                text = "Inicia sesión para continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = Violet
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico", color = Violet) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = Violet
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Violet,
                    unfocusedBorderColor = DeepPurple,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Violet
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", color = Violet) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = Violet
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Violet,
                    unfocusedBorderColor = DeepPurple,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Violet
                )
            )
            Button(
                    onClick = {
                        viewModel.login(
                            email = email,
                            password = password
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DeepPurple) // Morado oscuro llamativo
                ) {
                    Text("Iniciar sesión", color = Color.White)
                }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "¿No tienes una cuenta? Crea una",
                    color = PricePurple, // Morado claro
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.RegisterScreenRoute)
                    }
                )
            }
        }
    }
}