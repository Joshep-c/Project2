package com.example.project2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Clase de datos para la lista dinámica
data class DynamicUser(
    val cui: String,
    val nombres: String,
    val apellidos: String,
    val avatarColor: Color
)

// Función para generar los 20+ registros iniciales
fun generateDynamicUsers(): List<DynamicUser> {
    val colors = listOf(
        Color(0xFF90CAF9),
        Color(0xFFCE93D8),
        Color(0xFFA5D6A7),
        Color(0xFFFFCC80),
        Color(0xFFEF9A9A),
        Color(0xFF80DEEA)
    )

    val list = mutableListOf<DynamicUser>()
    for (i in 1..20) {
        val cui = "%05d".format(20240000 + i)
        list.add(
            DynamicUser(
                cui = cui,
                nombres = "Nombre$i",
                apellidos = "Apellido$i",
                avatarColor = colors[i % colors.size]
            )
        )
    }
    return list
}

@Composable
fun DynamicListScreen(modifier: Modifier = Modifier, navController: NavController) {
    var cui by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }

    val colors = listOf(
        Color(0xFF90CAF9),
        Color(0xFFCE93D8),
        Color(0xFFA5D6A7),
        Color(0xFFFFCC80),
        Color(0xFFEF9A9A),
        Color(0xFF80DEEA)
    )

    val users = remember {
        mutableStateListOf<DynamicUser>().apply {
            addAll(generateDynamicUsers())
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista Dinámica de Usuarios",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Card con formulario para agregar/modificar usuarios
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = cui,
                    onValueChange = { cui = it },
                    label = { Text("CUI") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = { Text("Nombres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            if (cui.isNotBlank() && nombres.isNotBlank() && apellidos.isNotBlank()) {
                                // Busca el índice del usuario por CUI
                                val index = users.indexOfFirst { it.cui == cui }
                                val randomColor = colors.random()

                                if (index != -1) {
                                    // Modifica el usuario existente
                                    users[index] = DynamicUser(cui, nombres, apellidos, randomColor)
                                } else {
                                    // Agrega nuevo usuario
                                    users.add(DynamicUser(cui, nombres, apellidos, randomColor))
                                }

                                // Limpia los campos
                                cui = ""
                                nombres = ""
                                apellidos = ""
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Agregar/Modificar")
                    }

                    OutlinedButton(
                        onClick = {
                            if (cui.isNotBlank()) {
                                // Elimina el usuario con el CUI especificado
                                users.removeAll { it.cui == cui }
                                cui = ""
                                nombres = ""
                                apellidos = ""
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de usuarios
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                DynamicUserCard(user = user) {
                    // Al hacer clic en una card, carga los datos en el formulario
                    cui = user.cui
                    nombres = user.nombres
                    apellidos = user.apellidos
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para volver
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver al Home")
        }
    }
}

@Composable
private fun DynamicUserCard(user: DynamicUser, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            // Avatar con iniciales
            val initials = (user.nombres.firstOrNull()?.toString() ?: "") +
                    (user.apellidos.firstOrNull()?.toString() ?: "")

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(color = user.avatarColor, shape = CircleShape)
            ) {
                Text(text = initials, color = Color.White, fontSize = 18.sp)
            }

            // Información del usuario
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = "CUI: ${user.cui}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Nombres: ${user.nombres}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Apellidos: ${user.apellidos}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}
