package Nive_Basico

fun main() {
    val palabras = listOf("Lucía", "Alejandro", "Ana", "Marta", "Iván")
    println("Total: ${palabras.size}")
    println("Más largo: ${palabras.maxByOrNull { it.length }}")
    println("Cortos: ${palabras.filter { it.length <= 4 }}")
    println("Ordenadas: ${palabras.sorted()}")
    println("Longitudes: ${palabras.associateWith { it.length }}")

    val frecuencias = "mississippi".groupBy { it }.mapValues { it.value.size }
    println("Frecuencias en mississippi: $frecuencias") }
