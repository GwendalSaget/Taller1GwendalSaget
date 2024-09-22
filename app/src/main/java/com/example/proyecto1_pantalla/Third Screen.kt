package com.example.proyecto1_pantalla

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto1_pantalla.ui.theme.Proyecto1pantallaTheme
import java.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

public class ThirdScreen : ComponentActivity() {

    // Supresión de advertencia por usar Scaffold sin el parámetro de padding
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Proyecto1pantallaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Fondo(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}

// Función composable que define el fondo de la pantalla
@Composable
public fun Fondo(modifier: Modifier) {
    // Variable para almacenar el color seleccionado, recuerda el valor durante la recomposición
    var selectedColor by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = GetColor(selectedColor) // Obtiene el color en función del valor seleccionado
    ) {
        inter(
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
            onColorSelected = { selectedColor = it } // Actualiza el color seleccionado cuando se selecciona uno nuevo
        )
    }
}

// Función para guardar el color seleccionado en las preferencias compartidas (SharedPreferences)
public fun SaveSelectedColor(context: Context, color: Int) {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        // Guarda el valor del color seleccionado
        putInt("SELECTED_COLOR", color)
        apply() // Aplica los cambios
    }
}

// Función para obtener el color guardado en las preferencias
public fun getSelectedColor(context: Context): Int {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    // Recupera el color guardado o devuelve 0 (gris) si no se ha seleccionado ninguno
    return sharedPref.getInt("SELECTED_COLOR", 0)
}

// Función composable que muestra los botones para seleccionar colores
@Composable
public fun inter(modifier: Modifier, onColorSelected: (Int) -> Unit): Int {
    var selectedColor2 by remember { mutableStateOf(0) } // Almacena el color seleccionado en el estado

    // Título que indica al usuario que elija un color de fondo
    Column(modifier = Modifier.offset(0.dp, -55.dp)) {
        Text(
            text = "¡Elige el color de fondo!",
            color = Color.White,
            fontSize = 30.sp,
            modifier = modifier
        )
    }

    val context = LocalContext.current // Obtiene el contexto actual

    // Botón para ir a la pantalla principal (MainActivity)
    Column(modifier = Modifier.offset(150.dp, 200.dp)) {
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent) // Inicia la actividad principal
            }
        ) {
            Text(text = stringResource(R.string.Principal3), fontSize = 20.sp)
        }
    }
    // Botón para ir a otra actividad (Actividad)
    Column(modifier = Modifier.offset(132.dp, 115.dp)) {
        Button(
            onClick = {
                val intent = Intent(context, Actividad::class.java)
                context.startActivity(intent) // Inicia la actividad "Actividad"
            }
        ) {
            Text(text = stringResource(R.string.Principal), fontSize = 20.sp)
        }
    }

    // Botones para seleccionar diferentes colores, cada botón guarda el color seleccionado y lo muestra
    Column(modifier = Modifier.offset(75.dp, 500.dp)) {
        Button(
            onClick = {
                onColorSelected(1) // Selecciona el color rojo
                selectedColor2 = 1
                SaveSelectedColor(context, selectedColor2) // Guarda el color seleccionado
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {}
    }

    Column(modifier = Modifier.offset(175.dp, 500.dp)) {
        Button(
            onClick = {
                onColorSelected(2) // Selecciona el color azul
                selectedColor2 = 2
                SaveSelectedColor(context, selectedColor2)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {}
    }

    Column(modifier = Modifier.offset(275.dp, 500.dp)) {
        Button(
            onClick = {
                onColorSelected(3) // Selecciona el color gris
                selectedColor2 = 3
                SaveSelectedColor(context, selectedColor2)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {}
    }

    Column(modifier = Modifier.offset(75.dp, 600.dp)) {
        Button(
            onClick = {
                onColorSelected(4) // Selecciona el color verde
                selectedColor2 = 4
                SaveSelectedColor(context, selectedColor2)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {}
    }

    Column(modifier = Modifier.offset(175.dp, 600.dp)) {
        Button(
            onClick = {
                onColorSelected(5) // Selecciona el color amarillo
                selectedColor2 = 5
                SaveSelectedColor(context, selectedColor2)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {}
    }

    Column(modifier = Modifier.offset(275.dp, 600.dp)) {
        Button(
            onClick = {
                onColorSelected(6) // Selecciona el color magenta
                selectedColor2 = 6
                SaveSelectedColor(context, selectedColor2)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
        ) {}
    }
    return 0
}

// Función composable que devuelve el color basado en el valor recibido
@Composable
fun GetColor(value: Int): Color {
    return when (value) {
        1 -> Color.Red
        2 -> Color.Blue
        3 -> Color.Gray
        4 -> Color.Green
        5 -> Color.Yellow
        6 -> Color.Magenta
        else -> Color.Gray // Valor predeterminado
    }
}

// Función de vista previa que muestra cómo se verá el componente "Fondo"
@Preview(showBackground = true)
@Composable
fun GetColorPreview() {
    Proyecto1pantallaTheme {
        Fondo(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
    }
}
