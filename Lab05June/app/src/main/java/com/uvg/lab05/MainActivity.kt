package com.uvg.lab05

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.lab05.ui.theme.Lab05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05Theme {
                Surface (modifier = Modifier
                    .fillMaxSize()
                ) {
                    HandleLab05States()
                }
            }
        }
    }
}

@Composable
fun HandleLab05States(){
    val context = LocalContext.current
    Lab05(
        birthdayDay = "Domingo",
        birthdayDate = "28 de Enero",
        fullName = "June Herrera Watanabe",
        restaurantName = "Kaffa",
        restaurantAddress = "18 Avenida A 0-63, Cdad. de Guatemala 01004",
        restaurantTime = "8:00 a.m. – 9:00 p.m",
        restaurantType = "Café Restaurante",
        restaurantPrice = "QQ",
        appLink = "https://play.google.com/store/apps/details?id=com.whatsapp",
        googleMapsCoords = "https://www.google.com/maps/dir//18+Avenida+A+0-63,+Cdad.+de+Guatemala+01004/@14.5922261,-90.5779737,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x8589a2308cee0f53:0x14990c3c030032f4!2m2!1d-90.4955719!2d14.5922405?entry=ttu"
    )
}

@Composable
fun Lab05(
    birthdayDay: String,
    birthdayDate: String,
    fullName: String,

    restaurantName : String,
    restaurantAddress: String,
    restaurantTime: String,
    restaurantType: String,
    restaurantPrice : String,

    appLink : String,
    googleMapsCoords : String,
){
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = birthdayDay)
                Text(text = birthdayDate)
            }
            Button(onClick = { /*TODO*/ }) {
                
            }
        }
        Card(onClick = { /*TODO*/ }) {
            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Text(text = restaurantName)
                    //Icon
                }
                Text(text = restaurantAddress)
                Text(text = restaurantTime)
                
                Row (modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        
                    }
                    Button(onClick = { /*TODO*/ }) {
                        
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab05Theme {
        Greeting("Android")
    }
}