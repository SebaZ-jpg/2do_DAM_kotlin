package Nive_Basico

fun main() {
    val kilogramos = listOf(0, 5, 33, 100)
    var total = 0
    for (kg in kilogramos) {
        val lb = kg * 22 / 10
        println("$kg kg = lb $lb")
        total += kg
    }

    val media = total.toDouble() / kilogramos.size
    println("Suma: $total")
    println("Media: $media")
}
