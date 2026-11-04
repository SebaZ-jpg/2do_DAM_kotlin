package Nivel_Avanzado.ultimo

class Vector2D(val x: Int, val y: Int) : Comparable<Vector2D> {

    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)

    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)

    operator fun times(k: Int) = Vector2D(x * k, y * k)

    operator fun unaryMinus() = Vector2D(-x, -y)

    private fun lengthSquared(): Int = x * x + y * y

    override fun compareTo(other: Vector2D): Int =
        lengthSquared().compareTo(other.lengthSquared())

    override fun toString(): String = "($x, $y)"
}

fun <T : Comparable<T>> smallest(list: List<T>): T? {
    if (list.isEmpty()) return null
    var min = list[0]
    for (e in list) {
        if (e < min) min = e
    }
    return min
}

fun main() {
    val a = Vector2D(1, 2)
    val b = Vector2D(3, -1)
    val c = Vector2D(-2, 0)
    val d = a * 3

    println("$a + $b = ${a + b}")
    println("$a - $b = ${a - b}")
    println("$b * 2 = ${b * 2}")
    println("-$c = ${-c}")

    println("Menor vector: ${smallest(listOf(a, b, c, d))}")
    println("Menor entero: ${smallest(listOf(7, 3, 9))}")
    println("Menor palabra: ${smallest(listOf("kiwi", "pera", "manzana"))}")
    println("Lista vacía: ${smallest(emptyList<Int>())}")

    println("Ordenados por longitud: ${listOf(a, b, c, d).sorted()}")
}