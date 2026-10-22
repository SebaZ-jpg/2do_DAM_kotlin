package Nivel_Medio

import java.util.Locale

sealed class Payment

data class Cash(val amount: Double) : Payment()
data class Card(val amount: Double, val feePercent: Double) : Payment()
data class Transfer(val amount: Double, val fixedFee: Double) : Payment()

fun totalCharged(p: Payment): Double = when (p) {
    is Cash -> p.amount
    is Card -> p.amount + p.amount * p.feePercent / 100
    is Transfer -> p.amount + p.fixedFee
}

fun main() {
    val payments = listOf(
        Cash(50.0),
        Card(200.0, 2.5),
        Transfer(1000.0, 3.0)
    )

    for (p in payments) {
        val total = "%.2f".format(Locale.US, totalCharged(p))
        println("${p::class.simpleName}: $total")
    }

    val suma = payments.sumOf { totalCharged(it) }
    println("Total: ${"%.2f".format(Locale.US, suma)}")
}