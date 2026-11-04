package Nivel_Avanzado.ultimo

import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

suspend fun fetchSize(name: String, size: Int, ms: Long): Pair<String, Int> {
    delay(ms)
    return name to size
}

fun main() = runBlocking {
    // 1. Tres descargas en paralelo con async
    val descargas = listOf(
        async { fetchSize("X", 5, 250) },
        async { fetchSize("Y", 15, 50) },
        async { fetchSize("Z", 25, 150) }
    )
    val resultados = descargas.awaitAll()
    println("Resultados: $resultados")
    println("Suma: ${resultados.sumOf { it.second }}")

    // 2. Flow del 1 al 6: impares por diez
    val imparesPorDiez = flow {
        for (i in 1..6) emit(i)
    }
        .filter { it % 2 == 1 }
        .map { it * 10 }
        .toList()
    println("Impares por diez: $imparesPorDiez")

    // 3. Descarga demasiado lenta: supera el tiempo límite
    val lenta = withTimeoutOrNull(100) { fetchSize("F", 99, 400) }
    println("Lenta: $lenta")

    // 4. Descarga rápida: termina a tiempo
    val rapida = withTimeoutOrNull(300) { fetchSize("G", 7, 50) }
    println("Rápida: $rapida")
}