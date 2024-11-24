package com.example.proyecto1_pantalla

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.proyecto1_pantalla.ui.theme.Proyecto1pantallaTheme

class FragmentDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Proyecto1pantallaTheme {
                    val person = getPersonFromPreferences(requireContext())
                    DetailScreen(person)
                }
            }
        }
    }

    @Composable
    fun DetailScreen(person: Personne) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 200.dp),
        ) {
            Text(
                text = "Se llama ${person.nombre} ${person.apellido} y tiene ${person.edad} años.",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
    private fun getPersonFromPreferences(context: Context): Personne {
        val sharedPreferences = context.getSharedPreferences("PersonPrefs", Context.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "") ?: ""
        val apellido = sharedPreferences.getString("apellido", "") ?: ""
        val edad = sharedPreferences.getString("edad", "") ?: ""
        return Personne(nombre, apellido, edad)
    }
}
