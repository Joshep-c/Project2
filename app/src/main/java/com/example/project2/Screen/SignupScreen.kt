// Kotlin
package com.example.project2.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project2.AppUtil
import com.example.project2.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel(),
    onCancel: () -> Unit = {}
) {

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Welcome to ShopPly",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
            )
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Create an account",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Full name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer (modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onCancel() },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = "Cancel", fontSize = 22.sp)
            }

            Button(
                onClick = {
                    authViewModel.signup(email, name, password) { success, errorMessage ->
                        if(success){
                            // Signup successful
                        } else {
                            AppUtil.showToast(context, errorMessage ?: "Something went wrong")
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = "Sign Up", fontSize = 22.sp)
            }
        }
    }
}
