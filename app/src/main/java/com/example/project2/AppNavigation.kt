package com.example.project2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project2.Screen.AuthScreen
import com.example.project2.Screen.LoginScreen
import com.example.project2.Screen.SignUpScreen
import com.example.project2.Screen.HomeScreen
import com.example.project2.pages.CategoryProductsPage
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    GlobalNavigation.navController = navController

    val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"

    NavHost(navController = navController, startDestination = firstPage) {
        composable("auth") {
            AuthScreen(modifier, navController)
        }

        composable("login") {
            LoginScreen(modifier, navController)
        }

        composable("signup") {
            SignUpScreen(modifier, navController)
        }

        composable("home") {
            HomeScreen(modifier, navController)
        }

        composable("category-products/{categoryId}") {
            var categoryId = it.arguments?.getString("categoryId")
            CategoryProductsPage(modifier,categoryId?:"" )
        }

    }
}

object GlobalNavigation {
    lateinit var navController: NavHostController
}