Este proyecto es una aplicación Android desarrollada utilizando Jetpack Compose, que consiste en tres actividades principales: una pantalla de verificacion de credenciales (Connection), la pantalla de bienvenida (MainActivity), una pantalla para ingresar y guardar el nombre del usuario (Actividad), y una pantalla para seleccionar un color de fondo (ThirdScreen). 

Coneccion :

Esta actividad, Connexion.kt, es una pantalla de inicio de sesión en una aplicación Android utilizando Jetpack Compose y Firebase Realtime Database.

Inicialización de Firebase : Se conecta a la base de datos Firebase y verifica si está vacía.

Interfaz de Usuario : La pantalla contiene dos campos de texto para el "Nombre de usuario" y "Contraseña", junto con un botón que cambia de función:

Si la base de datos está vacía, el botón muestra "Nuevas Credenciales" y permite crear un nuevo usuario.
Si la base de datos ya contiene datos, el botón muestra "Conectar" y permite iniciar sesión.
Crear Nuevas Credenciales : Si la base de datos está vacía, al hacer clic en "Nuevas Credenciales", se guardan el nombre de usuario y la contraseña en Firebase y se redirige al usuario a MainActivity.

Validación de Credenciales : Si hay datos en la base de datos, al hacer clic en "Conectar", se verifica que los datos ingresados coincidan con los almacenados. Si coinciden, se abre MainActivity; si no, aparece un mensaje "Malas credenciales".

Mensajes de Error : Se muestran mensajes de error o advertencia si hay problemas de conexión o si los campos están incompletos.

Actividad 1: MainActivity
La MainActivity muestra un saludo personalizado según la hora del día y el nombre del usuario guardado en las preferencias compartidas. Además, permite navegar a otras dos actividades mediante botones.

getSelectedColor(this): Recupera el color de fondo previamente seleccionado por el usuario de las preferencias compartidas.
getUserName(this): Recupera el nombre del usuario almacenado en las preferencias compartidas.

Greeting(name: String, modifier: Modifier, backgroundColor: Color)
Este es el principal Composable que se utiliza en esta pantalla para mostrar el saludo.

name: El nombre del usuario que se muestra en el saludo.
modifier: Controla cómo se posiciona y ajusta el saludo dentro de la interfaz. En este caso, ocupa todo el espacio disponible y centra el texto.
backgroundColor: Define el color de fondo en el que se mostrará el saludo.
Dentro de Greeting(), el saludo cambia en función de la hora, obtenida a través de la función GetTime().

Esta misma función GetTime() utiliza la clase Calendar de Java para obtener la hora actual del día:
Retorno: Devuelve la hora en formato 24 horas (un número entre 0 y 23).
Dependiendo de la hora, la función Greeting() mostrará:

"Buenas días" si la hora es menor que 12.
"Buenas tardes" si la hora está entre las 12 y las 19.
"Buenas noches" si la hora es igual o mayor que 19.


Navegación entre pantallas :
Dentro de Greeting(), hay botones que permiten navegar a:

Actividad: Permite cambiar el nombre del usuario.
ThirdScreen: Permite cambiar el color de fondo.
Estos botones utilizan Intent para iniciar nuevas actividades.
Corresponde a las lineas suiguientes :

val intent = Intent(context, Actividad::class.java)
context.startActivity(intent)


Actividad 2: Actividad
Descripción General: En esta actividad, el usuario puede ingresar un nuevo nombre y guardarlo en SharedPreferences. Este nombre se puede recuperar y usar en otras pantallas, como en la MainActivity.

saveUserName(context: Context, name: String)
Esta función guarda el nombre ingresado por el usuario en SharedPreferences.

sharedPref.edit(): Obtiene el editor de las preferencias compartidas para hacer cambios.
putString("USER_NAME", name): Guarda el nombre bajo la clave "USER_NAME".

getUserName(context: Context): String?
Esta función obtiene el nombre del usuario almacenado en las preferencias compartidas gracias a la misma clave.

GreetingName(modifier: Modifier, backgroundColor: Color)
Es el Composable principal de esta actividad.

userName: Utiliza el estado remember para recordar el valor ingresado por el usuario en el campo de texto.
Dentro de este Composable, se muestra una entrada de texto (OutlinedTextField) que permite al usuario ingresar un nuevo nombre. Cuando el usuario presiona el botón "Quedar nombre", el nombre se guarda utilizando la función saveUserName().

OutlinedTextField() :
value: Es el valor actual del texto en el campo.
onValueChange: Actualiza el valor del estado userName cuando el usuario escribe algo.

Al igual que en MainActivity, hay botones que permiten regresar a la pantalla principal o navegar a la tercera pantalla.

Actividad 3: ThirdScreen
Descripción General: Esta actividad permite al usuario seleccionar un color de fondo, que luego se aplica a todas las actividades. El color se guarda en SharedPreferences para que persista entre sesiones.

SaveSelectedColor(context: Context, color: Int)
Guarda el color seleccionado en SharedPreferences.

putInt("SELECTED_COLOR", color): Almacena el color bajo la clave "SELECTED_COLOR".
getSelectedColor(context: Context)
Recupera el color de fondo guardado en las preferencias compartidas gracias a la misma clave.
Retorno: Devuelve el color guardado, o la color 0 si no hay ningún color guardado.

Fondo(modifier: Modifier)
Este Composable representa la pantalla donde el usuario puede llamar a "inter" para seleccionar un color de fondo. Utiliza un estado selectedColor para recordar el color actualmente seleccionado.

inter(modifier: Modifier, onColorSelected: (Int) -> Unit): Int
Este es el Composable donde el usuario selecciona el color de fondo a través de varios botones. Cada botón tiene un color predefinido : rojo, azul, amarillo, magenta, gris y verde.

onColorSelected: Es una función que se ejecuta cuando el usuario selecciona un color. Cambia el valor de selectedColor en la funcion "Fondo".
Por ejemplo, al presionar el botón rojo:
onClick = { onColorSelected(1) }
El valor 1 representa el color rojo.

No hay que equivocarse, en la funcion "inter", con selectedColor2, en este caso efectivamente, selectedColor es lo que permite almacenar el numero de la color para que despues podamos gardar al en las otras actividades.
En estas actividades, solo se necesita llamar a la funcion GetColor() con el numero de la color que vamos a recoger con getSelectedColor()

GetColor(value: Int): Color
Esta función traduce un valor entero en un color. Por ejemplo:

1 -> Rojo
2 -> Azul

Si el valor no coincide con ninguno de los colores predefinidos, se devuelve el color gris por defecto.

Como en las actividades anteriores, también hay botones para volver a la pantalla principal o navegar a la pantalla de ingreso de nombre.

enlace GitHub : https://github.com/GwendalSaget/Taller1GwendalSaget
