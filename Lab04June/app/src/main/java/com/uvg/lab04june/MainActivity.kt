package com.uvg.lab04june

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.lab04june.ui.theme.Lab04JuneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04JuneTheme {
                LabFourScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LabFourScreen(){
    Box(
        modifier = Modifier
            .border(10.dp, Color(0xFF1c4a27))
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = "Logo UVG",
            modifier = Modifier
                .fillMaxSize(0.5F)
                .align(Alignment.Center)
                .alpha(0.15F)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight()
        ) {
            Text(text = "Universidad del Valle de Guatemala",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(text = "Programación de plataformas móviles, Sección 30",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(text = "INTEGRANTES",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left
                    )
                    Text(text = "Daniela Ramirez\nJavier López\nJune Herrera",
                        textAlign = TextAlign.Center)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(text = "CATEDRÁTICO",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left
                    )
                    Text(text = "Juan Carlos Durini",
                        textAlign = TextAlign.Center)
                }
            }
            Text(text = "June Herrera Watanabe\n231038",
                    textAlign = TextAlign.Center)
        }
    }

}

