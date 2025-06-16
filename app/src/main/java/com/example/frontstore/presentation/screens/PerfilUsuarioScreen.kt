import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontstore.presentation.viewmodel.ClienteViewModel

@Composable
fun PerfilOpcionCard(titulo: String, subtitulo: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCDC6D9))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = titulo, style = MaterialTheme.typography.titleSmall)
                Text(text = subtitulo, color = Color.Gray, fontSize = 12.sp)
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ir",
                tint = Color.Gray
            )
        }
    }
}




@Composable
fun PerfilUsuarioScreen(
    navController: NavController,
    usuarioId : String
) {
    val clienteViewModel : ClienteViewModel = hiltViewModel()

    val cliente = clienteViewModel.cliente.collectAsState().value
    Log.e("PerfilUsuarioScreen", "UsuarioId : $usuarioId")

    LaunchedEffect(key1 = usuarioId) {
        if (usuarioId.isNotEmpty()) {
            clienteViewModel.loadClienteById(id = usuarioId)
        } else {
            Log.e("PerfilUsuarioScreen", "usuarioId está vacío o es inválido")
        }
    }

    Log.e("PerfilUsuarioScreen", "Cliente: $cliente")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Mi cuenta", style = MaterialTheme.typography.titleLarge)
        }

        // Información del usuario
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6200EE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Usuario",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = if (cliente != null) cliente.nombre + " " + cliente.apellido else "Usuario Invitado", fontSize = 18.sp)
                Text(text = if (cliente != null) cliente.email else "example@example.com ", color = Color.Gray)
            }
        }

        // Opciones
        PerfilOpcionCard(
            titulo = "Información personal",
            subtitulo = "Información de tu identificación oficial y tu actividad fiscal"
        )
        PerfilOpcionCard(
            titulo = "Datos de la cuenta",
            subtitulo = "Datos que representan a la cuenta en FrontStore",
        )
        PerfilOpcionCard(
            titulo = "Seguridad",
            subtitulo = "Seguridad de tu cuenta FrontStore"
        )
        PerfilOpcionCard(
            titulo = "Tarjetas",
            subtitulo = "Tarjetas guardadas en tu cuenta"
        )
        PerfilOpcionCard(
            titulo = "Direcciones",
            subtitulo = "Direcciones guardadas en tu cuenta"
        )
    }
}
