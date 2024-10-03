package com.example.mysoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MycustomUI(msg: String, person: String, modifier: Modifier = Modifier) {
    Surface(color = Color.White, modifier = modifier.fillMaxSize()) {
        val image = painterResource(R.drawable.ic_launcher_foreground)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(24.dp) // Apply padding here or use innerPadding from Scaffold
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = image,
                    contentDescription = "if you want forever"
                )
                Text(
                    text = msg,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 8.dp)
                )
                Text(
                    text = person,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun composeTest(text1: String, text2: String, text3: String, text4: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEADDFF))
                    .fillMaxSize()
            ) {
                Text(
                    text = text1,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFD0BCFF))
                    .fillMaxSize()
            ) {
                Text(
                    text = text2,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFB69DF8))
                    .fillMaxSize()
            ) {
                Text(
                    text = text3,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF6EDFF))
                    .fillMaxSize()
            ) {
                Text(
                    text = text4,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }






















}

