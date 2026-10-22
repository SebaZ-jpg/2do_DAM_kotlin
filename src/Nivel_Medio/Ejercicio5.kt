package Nivel_Medio

class Queue<T> {
    private val items = ArrayDeque<T>()

    fun enqueue(x: T) {
        items.addLast(x)
    }

    fun dequeue(): T? = items.removeFirstOrNull()

    val isEmpty: Boolean
        get() = items.isEmpty()

    val size: Int
        get() = items.size
}

fun main() {
    val comandos = listOf("ADD A", "ADD B", "PRINT", "ADD C", "PRINT", "PRINT", "PRINT", "ADD D")
    val cola = Queue<String>()

    for (comando in comandos) {
        val partes = comando.split(" ")
        when (partes[0]) {
            "ADD" -> cola.enqueue(partes[1])
            "PRINT" -> {
                val documento = cola.dequeue()
                if (documento != null) {
                    println("Imprimiendo: $documento")
                } else {
                    println("Cola vacía")
                }
            }
        }
    }

    println("Pendientes: ${cola.size}")
}