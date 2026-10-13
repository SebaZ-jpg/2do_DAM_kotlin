package Nivel_Medio

// Recibe una cadena que puede ser nula y devuelve una edad válida
// como Int; si no se puede interpretar o está fuera del rango, devuelve null.
fun parseAge(s: String?): Int? =
    s
        ?.trim()                        // Si s no es null, quita espacios al inicio y al final.
        ?.toIntOrNull()                  // Intenta convertir el texto a entero; devuelve null si falla.
        ?.takeIf { it in 0..120 }        // Conserva el número solo si está entre 0 y 120, inclusive.

        fun main() {
        // Lista de ejemplos: edades válidas, texto inválido, un valor nulo y valores fuera del rango.
            val inputs = listOf("25", " 40 ", null, "abc", "200", "-3", "0")
            var sum = 0                     // Acumulador para sumar las edades válidas.

        // Recorre cada elemento de la lista.
            for (i in inputs) {
                val age = parseAge(i)       // Intenta convertir el elemento en una edad válida.

            // Muestra el valor original y la edad; si es null, muestra "inválido".
                println("[$i] -> ${age ?: "invalido"}")

            // Suma la edad si es válida; si es null, suma 0.
                sum += age ?: 0
            }

        // Muestra la suma total de las edades válidas.
            println("Suma: $sum")
        }