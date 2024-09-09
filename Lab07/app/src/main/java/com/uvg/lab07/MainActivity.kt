package com.uvg.lab07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.lab07.ui.theme.Lab07Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab07Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {

                    }
                ) { innerPadding ->
                    HandleNotification(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HandleNotification(
    modifier: Modifier = Modifier
){
    var notificacionesList = generateFakeNotifications()
    // State to keep track of the selected notification type
    val selectedType = remember { mutableStateOf<NotificationType?>(null) }

    // Filter the notifications based on the selected type
    var filteredNotifications = if (selectedType.value != null) {
        notificacionesList.filter { it.type == selectedType.value }
    } else {
        notificacionesList
    }

    fun onFilterChange(){
        filteredNotifications = if (selectedType.value != null) {
            notificacionesList.filter { it.type == selectedType.value }
        } else {
            notificacionesList
        }
    }

    NotificationsMain(filteredNotifications = filteredNotifications, selectedType = selectedType)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsMain(
    filteredNotifications: List<Notification>,
    selectedType: MutableState<NotificationType?>
) {

    Lab07Theme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),modifier = Modifier
                .fillMaxWidth(), title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                    Text(
                        text = "Notificaciones", modifier = Modifier
                            .padding(10.dp, 0.dp),
                        style = TextStyle(color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)
                    )
                }
            })

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 30.dp)
            ) {
                Text(
                    text = "Tipos de Notificaciones",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(20.dp, 0.dp)
                )

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(15.dp, 0.dp)
                ) {
                    item {
                        FilterChip(
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary),

                            modifier = Modifier.padding(2.5.dp, 10.dp),
                            selected = selectedType.value == NotificationType.GENERAL,
                            onClick = {
                                selectedType.value = if (selectedType.value == NotificationType.GENERAL) null else NotificationType.GENERAL
                            },
                            label = {
                                Text(text = "General")
                            }
                        )
                    }

                    item {
                        FilterChip(
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary),

                            modifier = Modifier.padding(2.5.dp, 10.dp),
                            selected = selectedType.value == NotificationType.NEW_POST,
                            onClick = {
                                selectedType.value = if (selectedType.value == NotificationType.NEW_POST) null else NotificationType.NEW_POST
                            },
                            label = {
                                Text(text = "Publicaciones")
                            }
                        )
                    }

                    item {
                        FilterChip(
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary),

                            modifier = Modifier.padding(2.5.dp, 10.dp),
                            selected = selectedType.value == NotificationType.NEW_MESSAGE,
                            onClick = {
                                selectedType.value = if (selectedType.value == NotificationType.NEW_MESSAGE) null else NotificationType.NEW_MESSAGE
                            },
                            label = {
                                Text(text = "Mensajes")
                            }
                        )
                    }

                    item {
                        FilterChip(
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary),

                            modifier = Modifier.padding(2.5.dp, 10.dp),
                            selected = selectedType.value == NotificationType.NEW_LIKE,
                            onClick = {
                                selectedType.value = if (selectedType.value == NotificationType.NEW_LIKE) null else NotificationType.NEW_LIKE
                            },
                            label = {
                                Text(text = "Likes")
                            }
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp)
                    ,horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(0.9F)
                            .clip(RoundedCornerShape(20.dp))
                            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    ) {
                        items(filteredNotifications) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(10.dp, 10.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .graphicsLayer {
                                            clip = true
                                            shape = CircleShape
                                        }
                                        .background(item.type.getColor())
                                ) {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        imageVector = item.type.getIcon(),
                                        contentDescription = item.title,
                                        tint = item.type.getOnColor()
                                    )
                                }
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = item.title, style = TextStyle(fontWeight = FontWeight.Bold))
                                        Text(text = item.sendAt, style = TextStyle(fontSize = 12.5.sp, color = Color.DarkGray))
                                    }
                                    Text(text = item.body)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


