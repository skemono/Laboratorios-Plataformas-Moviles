package com.uvg.lab05

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.lab05.ui.theme.CafeNoir
import com.uvg.lab05.ui.theme.Citron
import com.uvg.lab05.ui.theme.Citrus
import com.uvg.lab05.ui.theme.DarkPurple
import com.uvg.lab05.ui.theme.PersianOrange
import com.uvg.lab05.ui.theme.RedWood
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HandleLab05States(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(color = RedWood)
                    )
                }
            }
        }
    }
}

@Composable
fun HandleLab05States(modifier: Modifier){
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
        googleMapsCoords = "https://www.google.com/maps/dir//18+Avenida+A+0-63,+Cdad.+de+Guatemala+01004/@14.5922261,-90.5779737,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x8589a2308cee0f53:0x14990c3c030032f4!2m2!1d-90.4955719!2d14.5922405?entry=ttu",
        modifier = Modifier.background(color = RedWood),
        currentContext = context
    )
}

@Composable
fun Lab05(
    modifier: Modifier,
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
    currentContext : Context
){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = CafeNoir)
                .defaultMinSize(minHeight = 30.dp)

        ) {
            Row (modifier = Modifier.padding(horizontal = 10.dp)){
                Icon(
                    Icons.Rounded.Info,
                    contentDescription = "Info"
                )
                Text(modifier = Modifier
                    .padding(horizontal = 10.dp),
                    text = "Actualización Disponible",
                    fontSize = 10.sp
                    )

            }
            Text(text = "Descargar", color = Citrus, modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable(enabled = true){
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data =
                            Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                    }
                    currentContext.startActivity(intent)
                },
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
        ){
            Column(
            ) {
                Text(text = birthdayDay, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(text = birthdayDate)
            }
            OutlinedButton(onClick = { /*TODO*/ },modifier = Modifier
                .align(Alignment.Bottom)
                .height(30.dp),
                border = BorderStroke(2.dp, Citron)
            ) {
                Text(text = "Terminar Jornada", style = TextStyle(
                    color = DarkPurple,
                    fontWeight = FontWeight.Bold
                ))
            }
        }
        Card(onClick = { /*TODO*/ }, elevation = CardDefaults.cardElevation(defaultElevation = 5.dp), colors = CardDefaults.cardColors(
            containerColor = RedWood
        )) {
            Column(verticalArrangement = Arrangement.spacedBy(7.dp) ,modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Text(text = restaurantName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Icon(
                        Icons.Rounded.LocationOn,
                        contentDescription = "Location",
                        Modifier.clickable {
                            val latitude = 14.592235321822738
                            val longitude = -90.49557452501665
                            val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setPackage("com.google.android.apps.maps")
                            currentContext.startActivity(intent)
                        }
                    )
                }
                Text(text = restaurantAddress, style = TextStyle(
                    fontSize = 13.sp,
                    color = Color.Black
                ))
                Text(text = restaurantTime, style = TextStyle(
                    fontSize = 13.sp
                )
                )
                
                Row (horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Button(onClick = {
                        Toast.makeText(currentContext, fullName, Toast.LENGTH_LONG).show()
                    }, colors = ButtonDefaults.buttonColors(containerColor = Citrus), modifier = Modifier
                        .fillMaxWidth(0.5f)
                    ) {
                       Text(text = "Iniciar", style = TextStyle(
                           color = Color.Black,
                           fontWeight = FontWeight.Bold
                       ))
                    }
                    Button(onClick = {
                        val message = "$restaurantType\n$restaurantPrice"
                        Toast.makeText(currentContext, message, Toast.LENGTH_LONG).show()
                    }, colors = ButtonDefaults.buttonColors(containerColor = CafeNoir),modifier = Modifier
                        .fillMaxWidth()
                    ) {
                        Text(text = "Detalles", style = TextStyle(
                            color = Citrus,
                            fontWeight = FontWeight.Bold
                        ))
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
    Lab05Theme (){
        Surface(
            color = PersianOrange,
            modifier = Modifier
                .fillMaxSize()
        ){
            HandleLab05States(modifier = Modifier.background(RedWood))
        }
    }
}