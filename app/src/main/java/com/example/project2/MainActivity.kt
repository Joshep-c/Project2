package com.example.project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project2.Pantallas.PantallaFormulario
import com.example.project2.Pantallas.PantallaInicio

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopPlyApp()
        }
    }
}

@Composable
fun ShopPlyApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "inicio") {
        composable("inicio"){ PantallaInicio(navController) }
        composable("formulario") { PantallaFormulario(navController) }

    }

}
