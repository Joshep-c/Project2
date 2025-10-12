package com.example.project2.Pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun PantallaFormulario(navController: NavController){

    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(Modifier.padding(16.dp)){
        Text("Formulario de Inicio de Sesión", style=MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            isError = correo.isBlank()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Correo") },
            isError = password.isBlank()
        )

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (password.isNotBlank() && correo.isNotBlank() && password.isNotBlank()) {
                navController.popBackStack()
            }
        }) {
            Text("Iniciar Sesión")
        }

    }


}