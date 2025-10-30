package com.example.project2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Composable
fun HeadView(modifier: Modifier = Modifier) {

    var name by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("users")
            .document(Firebase.auth.currentUser?.uid!!)
            .get().addOnCompleteListener () {
                name = it.result.get("name").toString().split("").get(0)
            }

    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(text = "Bienvenido")
            Text(text = name, style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ))
        }
    }
}