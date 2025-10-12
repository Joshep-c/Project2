package com.example.project2.Pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaFormulario(navController: NavController) {

    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var correoError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

    val correoRegex = Regex("^[^@\\s]+@[^@\\s]+\\.[a-zA-Z]{2,}\$")

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
        Text(
            "Formulario de Inicio de Sesión",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                correoError = when {
                    it.isBlank() -> "El correo no debe estar vacío"
                    !correoRegex.matches(it) -> "Correo inválido (debe contener @ y dominio)"
                    else -> null
                }
            },
            label = { Text("Correo") },
            isError = correoError != null,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        if (correoError != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = correoError ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = when {
                    it.isBlank() -> "La contraseña no puede estar vacía"
                    else -> null
                }
            },
            label = { Text("Contraseña") },
            isError = passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (passwordError != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = passwordError ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    correoError = when {
                        correo.isBlank() -> "El correo no debe estar vacío"
                        !correoRegex.matches(correo) -> "Correo inválido (debe contener @ y dominio)"
                        else -> null
                    }

                    passwordError = when {
                        password.isBlank() -> "La contraseña no puede estar vacía"
                        else -> null
                    }

                    if (correoError == null && passwordError == null) {
                        navController.navigate("inicio") {
                            // opcional: limpiar stack si quieres
                            popUpTo("inicio") { inclusive = false }
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Iniciar Sesión")
            }

            Button(
                onClick = {
                    navController.navigate("inicio")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }
        }
    }
}
