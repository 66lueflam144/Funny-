package com.example.mysoul
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mysoul.ui.theme.MySoulTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySoulTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MycustomUI(
                        msg = "All the girls are girling girling",
                        person = "LE SSERAFIM",
                        modifier = Modifier.padding(innerPadding)// 将 innerPadding 传递给 modifier
                    )

                    composeTest(
                        text1 = "damn I really make it look EASY",
                        text2 = "That's that me ESPRESSO",
                        text3 = "face, eyes, body go wild, you want this?",
                        text4 = "me"
                    )
//                    part1(
//                        full_name = "Crazy",
//                        title = "Hit me With the Lightning!",
//                        modifier = Modifier
//                    )
                }
            }
        }
    }


}




