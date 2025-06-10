//package com.example.frontstore.presentation.screens
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.frontstore.presentation.viewmodel.AuthViewModel
//
//@Composable
//fun LoginScreen(
//    navController: NavController,
//    viewModel: AuthViewModel = hiltViewModel()
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val uiState by viewModel.uiState.collectAsState()
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text(
//                text = "Bienvenido",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Inicia sesión para continuar",
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Correo electrónico") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Contraseña") },
//                visualTransformation = PasswordVisualTransformation(),
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Text(
//                    text = "¿Olvidaste tu contraseña?",
//                    color = Color(0xFFFF9800),
//                    style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier.clickable { /* Acción de recuperación */ }
//                )
//            }
//
//            if (uiState.error != null) {
//                Text(
//                    text = uiState.error,
//                    color = MaterialTheme.colorScheme.error,
//                    style = MaterialTheme.typography.bodySmall
//                )
//            }
//
//            if (uiState.isLoading) {
//                CircularProgressIndicator()
//            } else {
//                Button(
//                    onClick = { viewModel.login(email, password) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
//                ) {
//                    Text("Iniciar sesión", color = Color.White)
//                }
//            }
//
//            TextButton(onClick = { /* Acción de registro */ }) {
//                Text("¿No tienes cuenta? Regístrate", color = Color(0xFFFF9800))
//            }
//        }
//    }
//
//    // Navegación tras login exitoso
//    LaunchedEffect(uiState.isSuccess) {
//        if (uiState.isSuccess) {
//            navController.navigate("listaArticulos") {
//                popUpTo("login") { inclusive = true }
//            }
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    MaterialTheme {
//        LoginScreen()
//    }
//}
