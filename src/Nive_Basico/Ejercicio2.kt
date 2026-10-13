package Nive_Basico

fun categoria(edad: Int): String = when (edad) {
    in 0..12 -> "Niño"
    in 13..17 -> "Adolescente"
    in 18..64 -> "Adulto"
    in 65..120 -> "Mayor"
    else -> "Inválida"
}

fun main() {
    val edad = listOf(5, 13, 30, 65, 17, 120, 150)
    for (n in edad) println("$n -> ${categoria(n)}")
    val mayores = edad.count { it in 18..120 }
    println("Mayores de edad: $mayores de ${edad.size}")
}
