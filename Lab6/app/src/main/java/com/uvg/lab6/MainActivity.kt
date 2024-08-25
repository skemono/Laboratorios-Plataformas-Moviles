package com.uvg.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.lab6.ui.theme.Lab6Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.uvg.lab6.ui.theme.CherryBlossomPink
import com.uvg.lab6.ui.theme.MountbattenPink
import com.uvg.lab6.ui.theme.Pink
import com.uvg.lab6.ui.theme.Platinum
import com.uvg.lab6.ui.theme.White


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    handleLabStates(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun handleLabStates(modifier: Modifier){
    var contador by rememberSaveable { mutableStateOf(0) }
    var incrementos by rememberSaveable { mutableStateOf(0) }
    var decrementos by rememberSaveable { mutableStateOf(0) }
    var maximo by rememberSaveable { mutableStateOf(0) }
    var minimo by rememberSaveable { mutableStateOf(0) }
    var cambios by rememberSaveable { mutableStateOf(0) }
    var historial = remember { mutableStateListOf<Pair<Int, Boolean>>() }

    fun onContadorChange(dir: String) {
        val oldValue = contador
        if (dir == "+") {
            contador++
            incrementos++
            historial.add(Pair(contador, true))
        } else {
            contador--
            decrementos++
            historial.add(Pair(contador, false))
        }
        maximo = historial.maxOf { it.first }
        minimo = historial.minOf { it.first }
        cambios++
    }

    fun onReiniciar(){
        contador = 0
        incrementos = 0
        decrementos = 0
        maximo = 0
        minimo = 0
        cambios = 0
        historial.clear()
    }


    Lab6(modifier,
        ::onContadorChange,
        ::onReiniciar,
        contador,
        incrementos,
        decrementos,
        maximo,
        minimo,
        cambios,
        historial
        )
}


@Composable
fun Lab6(
    modifier: Modifier,
    onContadorChange: (String) -> Unit,
    onReiniciar: () -> Unit,
    contador: Int,
    incrementos: Int,
    decrementos: Int,
    maximo: Int,
    minimo: Int,
    cambios: Int,
    historial: SnapshotStateList<Pair<Int, Boolean>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Platinum),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Centered Text",
            modifier = Modifier.padding(vertical = 16.dp),
            color = MountbattenPink
        )

        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onContadorChange("-")
                },
                colors = ButtonDefaults.buttonColors(containerColor = CherryBlossomPink)
            ) {
                Text("-", color = White)
            }
            Text(
                text = contador.toString(),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MountbattenPink
            )
            Button(
                onClick = {
                    onContadorChange("+")
                },
                colors = ButtonDefaults.buttonColors(containerColor = CherryBlossomPink)
            ) {
                Text("+", color = White)
            }
        }

        // Estilo para las filas de información
        val textStyle = TextStyle(color = MountbattenPink)
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)

        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total incrementos:", style = textStyle)
            Text(incrementos.toString(), style = textStyle)
        }
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total decrementos:", style = textStyle)
            Text(decrementos.toString(), style = textStyle)
        }
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Valor máximo:", style = textStyle)
            Text(maximo.toString(), style = textStyle)
        }
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Valor mínimo:", style = textStyle)
            Text(minimo.toString(), style = textStyle)
        }
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total cambios:", style = textStyle)
            Text(cambios.toString(), style = textStyle)
        }

        Text(
            text = "Historial",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            color = MountbattenPink
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(historial.size) { index ->
                val (value, isIncrease) = historial[index]
                Button(
                    onClick = { /* Button action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isIncrease) Color.Green else Color.Red
                    )
                ) {
                    Text("$value", color = MountbattenPink)
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = { onReiniciar() },
                modifier = Modifier.padding(bottom = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CherryBlossomPink)
            ) {
                Text(text = "Reiniciar", color = White)
            }
        }
    }
}
