package com.example.project2.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project2.components.BannerView
import com.example.project2.components.HeadView

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
    ){
        HeadView(modifier)
        BannerView(modifier)
    }
}

