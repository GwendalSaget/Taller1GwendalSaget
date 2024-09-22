package com.example.proyecto1_pantalla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto1_pantalla.ui.theme.Proyecto1pantallaTheme
import java.util.Calendar
//import java.time.chrono.IsoEra
//import java.time.temporal.IsoFields

public class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Se obtiene el color seleccionado de las preferencias guardadas
        val selectedColor = getSelectedColor(this)

        // Se obtiene el nombre del usuario de las preferencias guardadas
        val nombre = getUserName(this)

        // Se establece el contenido con la temática de la aplicación
        setContent {
            Proyecto1pantallaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    // Se llama a la función Greeting que muestra el saludo
                    Greeting(
                        name = "$nombre",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        backgroundColor = GetColor(selectedColor)
                    )
                }
            }
        }
    }
}

// Función composable que obtiene la hora actual
@Composable
fun GetTime(): Int {
    val calendar = Calendar.getInstance()

    // Obtiene la hora en formato de 24 horas
    var hour = calendar.get(Calendar.HOUR_OF_DAY)
    return hour
}

// Función composable que muestra un saludo personalizado basado en la hora del día
@Composable
public fun Greeting(name: String, modifier: Modifier, backgroundColor: Color) {
    Surface(color = backgroundColor) {

        // Dependiendo de la hora, se muestra un saludo diferente
        if (GetTime() < 19 && GetTime() >= 12) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenas tardes $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }
        } else if (GetTime() >= 19) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenas noches $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }
        } else if (GetTime() < 12) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenos días $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }
        }

        // Botón que navega a la actividad 'Actividad'
        val context = LocalContext.current
        Column(modifier = Modifier.offset(132.dp, 115.dp)) {
            Button(
                onClick = {
                    val intent = Intent(context, Actividad::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text(text = stringResource(R.string.Principal), fontSize = 20.sp)
            }
        }

        // Botón que navega a 'ThirdScreen'
        Column(modifier = Modifier.offset(140.dp, 200.dp)) {
            Button(
                onClick = {
                    val intent = Intent(context, ThirdScreen::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text(text = stringResource(R.string.Principal2), fontSize = 20.sp)
            }
        }
    }
}

// Vista previa del saludo en modo de diseño
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Proyecto1pantallaTheme {
        Greeting(
            name = "Gwen",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            backgroundColor = Color.Black
        )
    }
}
