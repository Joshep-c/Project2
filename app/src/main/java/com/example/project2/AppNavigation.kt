package com.example.project2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project2.Screen.AuthScreen
import com.example.project2.Screen.LoginScreen
import com.example.project2.Screen.SignUpScreen
import com.example.project2.Screen.AnimationScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(modifier, navController)
        }

        composable("login") {
            LoginScreen()
        }

        composable("signup") {
            SignUpScreen(
                modifier = modifier,
                onCancel = {
                    navController.navigate("auth") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        composable("animation") {
            AnimationScreen(modifier = modifier, navController = navController)
        }

    }
}