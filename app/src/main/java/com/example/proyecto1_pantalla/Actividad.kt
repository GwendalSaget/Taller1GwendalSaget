package com.example.proyecto1_pantalla

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

public class Actividad : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Se obtiene el color seleccionado de las preferencias guardadas
        val selectedColor = getSelectedColor(this)

        // Se establece el contenido con la temática de la aplicación
        setContent {
            Proyecto1pantallaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    GreetingName(
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

// Función que guarda el nombre de usuario en SharedPreferences
fun saveUserName(context: Context, name: String) {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("USER_NAME", name)
        apply()
    }
}

// Función que recupera el nombre de usuario de SharedPreferences
fun getUserName(context: Context): String? {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPref.getString("USER_NAME", null) // Valor por defecto es null
}

// Función composable que permite ingresar y guardar un nombre
@Composable
public fun GreetingName(modifier: Modifier, backgroundColor: Color) {
    var userName by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Recupera el nombre guardado o muestra "Amigo" por defecto
    val savedName = getUserName(context) ?: "Amigo"

    Surface(color = backgroundColor) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "¿Un nuevo nombre?",
                color = Color.White,
                fontSize = 30.sp,
                modifier = modifier
            )
        }

        // Campo de texto para ingresar un nuevo nombre
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("¿Cómo te llamas?", color = Color.White) },
            modifier = Modifier.offset(50.dp, 550.dp)
        )

        // Botón para guardar el nombre
        Column(modifier = Modifier.offset(100.dp, 700.dp)) {
            Button(
                onClick = {
                    saveUserName(context, userName)
                    Toast.makeText(context, "¡Todo bien!", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("Guardar nombre", fontSize = 20.sp)
            }
        }
        Column(modifier = Modifier.offset(149.dp, 115.dp)) {
            Button(
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)},
            ) {
                Text(text = stringResource(R.string.Principal3), fontSize = 20.sp)
            }
        }
        Column(modifier = Modifier.offset(140.dp, 200.dp)) {
            Button(
                onClick = {
                    val intent = Intent(context, ThirdScreen::class.java)
                    context.startActivity(intent)
                }
            )  {
                Text(text = stringResource(R.string.Principal2), fontSize = 20.sp)
            }
        }
    }
}

// Vista previa del GreetingName en el modo de diseño
@Preview(showBackground = true)
@Composable
fun GreetingPreviewName() {
    Proyecto1pantallaTheme {
        GreetingName(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            backgroundColor = Color.Black
        )
    }
}
