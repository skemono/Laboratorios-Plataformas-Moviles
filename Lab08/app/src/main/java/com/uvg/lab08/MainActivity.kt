package com.uvg.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uvg.lab08.ui.theme.Lab08Theme
import com.uvg.lab08.util.Character
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable data object LoginDestination
@Serializable data object MainDestination
@Serializable data object CharacterScreenDestination
@Serializable data class CharacterDescriptionDestination(val id: Int)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab08Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = LoginDestination){
                        composable<LoginDestination> {
                            loginScreen(
                                modifier = Modifier.padding(innerPadding),
                                onEntrar = { navController.navigate(route = CharacterScreenDestination) }
                            )
                        }
                        navigation<MainDestination>(startDestination = CharacterScreenDestination){
                            composable<CharacterScreenDestination>{
                                CharacterScreen(
                                    onClickChar = {id: Int -> navController.navigate(CharacterDescriptionDestination(id = id))}
                                )
                            }
                            composable<CharacterDescriptionDestination>{
                                val charArgDescDest = it.toRoute<CharacterDescriptionDestination>()
                                CharacterDescription(
                                    onBack = {navController.navigateUp()},
                                    id = charArgDescDest.id
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun loginScreen(
    modifier: Modifier = Modifier,
    onEntrar: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "xd",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(250.dp)
                )
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(0.6F),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = onEntrar
                ) {
                    Text(text = "Entrar")
                }
            }


            Text(
                text = "June Herrera Watanabe 231038", modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp)
            )
        }
    }
}

