package com.example.project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project2.ui.theme.Project2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Estado del tema (claro/oscuro)
            var darkTheme by remember { mutableStateOf(false) }

            Project2Theme(darkTheme = darkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    HomeView(
                        modifier = Modifier.padding(innerPadding),
                        onToggleTheme = { darkTheme = !darkTheme }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onToggleTheme: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // centrado vertical
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ShopPly",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onToggleTheme() }) {
            Text("Cambiar tema")
        }
    }
}
