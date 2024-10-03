package com.example.mysoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun part1(full_name: String, title: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_launcher_foreground)
    Box(
        modifier = modifier
            .fillMaxSize() // Fill the entire screen
            .padding(60.dp) // Optional padding from the edges
    ) {
        Box(
            modifier = Modifier
                .size(450.dp, 200.dp)
                .background(Color.LightGray)
                .border(2.dp, Color.Black)
                .padding(8.dp)
                .align(Alignment.Center) // Center the Box in the parent Box
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                        .border(2.dp, Color.Black)
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Image(
                        painter = image,
                        contentDescription = "if you want forever"
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(2.dp, Color.Black)
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Text(
                        text = full_name,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(2.dp, Color.Black)
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Text(
                        text = title
                    )
                }
            }
        }
    }
}
