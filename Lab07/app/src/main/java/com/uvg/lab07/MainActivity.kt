package com.uvg.lab07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.lab07.ui.theme.Lab07Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab07Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HandleNotification(
    modifier: Modifier
){
    var notificacionesList = generateFakeNotifications()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsMain(
    modifier: Modifier = Modifier,
    notificaciones : List<Notification>
){
    Column {
        TopAppBar(title = {
            Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "Back")
            Text(text = "Notificaciones")
        })


        Text(text = "Tipos de Notificaciones")
        Row {
            FilterChip(selected = false, onClick = { /*TODO*/ }, label = { /*TODO*/ })
            FilterChip(selected = false, onClick = { /*TODO*/ }, label = { /*TODO*/ })
        }

        LazyColumn {
            items(notificaciones){ item ->
                Row {
                    Icon(imageVector = item., contentDescription = item.IconDesc)
                    Row {
                        Text(text = item.Title)
                        Text(text = item.Date)
                    }
                    Text(text = item.Desc)
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
    Lab07Theme {
        Greeting("Android")
    }
}