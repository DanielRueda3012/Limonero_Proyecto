package com.example.limonero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LemonadeApp() {
    var posicion by remember { mutableStateOf(1) }
    var exprimirLimon by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Yellow)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "LIMOMUCHO LIMONADA",
                color = Color.Black,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        when (posicion) {
            1 -> {
                Imagen_Texto(
                    textLabelResourceId = R.string.SeleccionarLimon,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.arbolLimones
                ) {
                    posicion = 2
                    exprimirLimon = (2..4).random()
                }
            }
            2 -> {
                Imagen_Texto(
                    textLabelResourceId = R.string.ExprimirLimon,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.Limon
                ) {
                    exprimirLimon--
                    if (exprimirLimon == 0) {
                        posicion = 3
                    }
                }
            }
            3 -> {
                Imagen_Texto(
                    textLabelResourceId = R.string.LimonBeber,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.vasoLimonada
                ) {
                    posicion = 4
                }
            }
            4 -> {
                Imagen_Texto(
                    textLabelResourceId = R.string.vasoLimonVacio,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.vasoVacio
                ) {
                    posicion = 1
                }
            }
        }
    }
}

@Composable
fun Imagen_Texto(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onClick: () -> Unit
) {
    val textLabel = stringResource(textLabelResourceId)
    val isLimonada = textLabel == "Limonada"
    val isLimonInYellow = textLabel.contains("limón", ignoreCase = true) // Verifica si el texto contiene "limón"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(100.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(120.dp)
        ) {
            if (isLimonada) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Yellow) // Fondo amarillo
                ) {
                    Text(
                        text = "Texto", // Reemplaza con el texto que desees
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(600.dp),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Image(
                        painter = painterResource(drawableResourceId),
                        contentDescription = stringResource(contentDescriptionResourceId),
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = textLabel,
            color = if (isLimonInYellow) Color.Black else Color.Black,
            fontWeight = if (isLimonInYellow) FontWeight.Bold else FontWeight.Normal
        )
    }
}



@Preview
@Composable
fun LemonPreview() {
    LemonadeApp()
}