package com.uvg.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.ActivityNavigator
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
@Serializable data object CharTreeDestination
@Serializable data object LocTreeDestination
@Serializable data object CharacterScreenDestination
@Serializable data class CharacterDescriptionDestination(val id: Int)
@Serializable data object LocationScreenDestination
@Serializable data class LocationDescriptionDestination(val id: Int)
@Serializable data object ProfileScreenDestination


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab08Theme {
                val navController = rememberNavController()
                val menuItems = listOf(
                    Triple("Characters", CharacterScreenDestination, Icons.Default.Person),
                    Triple("Locations", LocationScreenDestination, Icons.Default.Place),
                    Triple("Profile", ProfileScreenDestination, Icons.Default.AccountCircle)
                )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                var loggedIn by rememberSaveable {
                    mutableStateOf(false)
                }
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    when (loggedIn){
                        true -> NavigationBar {
                            menuItems.forEachIndexed { index, triple ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    label = {Text(text = triple.first)},
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(triple.second)
                                    },
                                    icon = {
                                        Icon(imageVector = triple.third, contentDescription = "NavItemIcon")
                                    })
                            }
                        }
                        else -> {}
                    }
                }) { innerPadding ->
                    NavHost(navController = navController, startDestination = LoginDestination){
                        composable<LoginDestination> {
                            loginScreen(
                                modifier = Modifier.padding(innerPadding),
                                onEntrar = {
                                    navController.navigate(route = CharTreeDestination)
                                    loggedIn = true
                                }
                            )
                        }
                        composable<ProfileScreenDestination> {
                            ProfileScreen(
                                onBack = {
                                    navController.navigate(route = LoginDestination)
                                    navController.popBackStack(route = CharTreeDestination, inclusive = false)
                                    loggedIn = false
                                }
                            )
                        }
                        navigation<CharTreeDestination>(startDestination = CharacterScreenDestination){
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
                        navigation<LocTreeDestination>(startDestination = LocationScreenDestination){
                            composable<LocationScreenDestination>{
                                LocationScreen(
                                    onClickLocation = {id: Int -> navController.navigate(LocationDescriptionDestination(id = id))}
                                )
                            }
                            composable<LocationDescriptionDestination>{
                                val locationArgDescDest = it.toRoute<LocationDescriptionDestination>()
                                LocationDescription(
                                    onBack = {navController.navigateUp()},
                                    id = locationArgDescDest.id
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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Lab08Theme {
        loginScreen(
            onEntrar = {}
        )
    }
}
