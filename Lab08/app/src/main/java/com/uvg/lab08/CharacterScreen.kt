package com.uvg.lab08

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.uvg.lab08.ui.theme.Lab08Theme
import com.uvg.lab08.util.Character
import com.uvg.lab08.util.CharacterDb
import kotlinx.serialization.Serializable

private val myDb = CharacterDb()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(onClickChar : (Int) -> Unit){
    Column (Modifier.fillMaxSize()){
        TopAppBar(
            title = { Text(text = "Characters") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        LazyColumn(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.9F)
        ) {
            items(myDb.getAllCharacters()){
                    char: Character ->
                CharacterRow(char = char, onClickChar = onClickChar)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CharacterRow(char: Character, onClickChar: (Int) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp)
        .clickable { onClickChar(char.id) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(char.image)
                .crossfade(true)
                .build(),
            contentDescription = char.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = char.name, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
            Text(text = char.species + " - " + char.status)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterScreenPreview() {
    Lab08Theme {
        CharacterScreen(
            onClickChar = { /* Do nothing in preview */ }
        )
    }
}