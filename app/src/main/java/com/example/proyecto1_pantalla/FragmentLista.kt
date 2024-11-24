package com.example.proyecto1_pantalla

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.example.proyecto1_pantalla.ui.theme.Proyecto1pantallaTheme

data class Personne(
    val nombre: String,
    val apellido: String,
    val edad: String,
) {
    constructor() : this(nombre = "", apellido = "", edad = "")
}

class FragmentLista : Fragment() {

    private val personList = mutableStateListOf<Personne>()
    private val PREFS_NAME = "PersonPrefs"
    private val KEY_PERSON_COUNT = "person_count"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Proyecto1pantallaTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .padding(top = 300.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var nombre by remember { mutableStateOf("") }
                        var apellido by remember { mutableStateOf("") }
                        var edad by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = apellido,
                            onValueChange = { apellido = it },
                            label = { Text("Apellido") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = edad,
                            onValueChange = { edad = it },
                            label = { Text("Edad") },
                            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (nombre.isNotEmpty() && apellido.isNotEmpty() && edad.isNotEmpty()) {
                                    val newPerson = Personne(
                                        nombre = nombre,
                                        apellido = apellido,
                                        edad = edad,
                                    )
                                    personList.add(newPerson)

                                    nombre = ""
                                    apellido = ""
                                    edad = ""
                                }
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Agregar")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn(modifier = Modifier.fillMaxSize().weight(1f)) {
                            items(personList.size) { index ->
                                val person = personList[index]
                                PersonRow(person) {
                                    updateSharedPreferences(person)

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PersonRow(person: Personne, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onClick() },
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(
                text = person.nombre,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }


    private fun updateSharedPreferences(person: Personne) {
        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("nombre", person.nombre)
            putString("apellido", person.apellido)
            putString("edad", person.edad)
            apply()
        }

        var personCount = sharedPreferences.getInt(KEY_PERSON_COUNT, 0)
        personCount++
        with(sharedPreferences.edit()) {
            putInt(KEY_PERSON_COUNT, personCount)
            apply()
        }
    }
}


// application pour stocker des personnes
// en gros tu fais un comme pour novelas mais avec deux fragments
// frag 1 tu peux "créer une personne" -> sur la liste on voit son prénom seulement
// frag 2 tu peux voir le détail, nom, prénom, âge
// widget, voir le nombre de clients avec un bouton pour actualiser
//https://stackoverflow.com/questions/7149802/how-to-transfer-some-data-to-another-fragment