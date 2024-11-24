package com.example.proyecto1_pantalla

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity // Modifié pour utiliser AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.proyecto1_pantalla.ui.theme.Proyecto1pantallaTheme
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val nombre = getUserName(this)
        setContent {
            Proyecto1pantallaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    Greeting(
                        name = "$nombre",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 90.dp),
                        backgroundColor = Color.LightGray
                    )
                }
            }
        }
        loadFragment(FragmentLista())
        loadFragment(FragmentDetails())
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment)
            .commit()

    }
}

@Composable
fun GetTime(): Int {
    val calendar = Calendar.getInstance()

    var hour = calendar.get(Calendar.HOUR_OF_DAY)
    return hour
}

// Función composable que muestra un saludo personalizado basado en la hora del día
@Composable
public fun Greeting(name: String, modifier: Modifier, backgroundColor: Color) {
    Surface(color = backgroundColor) {
        var hour = GetTime();

        if (hour < 19 && hour >= 12) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenas tardes $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }
        } else if (hour >= 19) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenas noches $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }
        } else if (hour < 12) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Buenos días $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = modifier
                )
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

// comme gestion novelas mais avec des personnes
// 2 frag, 1 qui print la liste de personnes (juste les prénoms)
// 1 autre qui montre les détails de la personne (nom, prénom, âge)
// widget qui print le compte de personne avec le bouton pour actualiser