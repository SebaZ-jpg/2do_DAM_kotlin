package Nivel_Avanzado.ultimo

const val MAX_SALDO = 200

sealed interface Command
data class Recharge(val amount: Int) : Command
data class Purchase(val amount: Int) : Command

fun parse(line: String): Result<Command> = runCatching {
    val parts = line.split(" ")
    require(parts.size == 2) { "Formato inválido" }

    val (op, raw) = parts
    val amount = raw.toIntOrNull() ?: error("Cantidad no numérica: $raw")
    require(amount > 0) { "Cantidad no positiva: $raw" }

    when (op) {
        "RECARGA" -> Recharge(amount)
        "COMPRA" -> Purchase(amount)
        else -> error("Operación desconocida: $op")
    }
}

fun apply(saldo: Int, cmd: Command): Int = when (cmd) {
    is Purchase -> {
        check(cmd.amount <= saldo) {
            "Fondos insuficientes (saldo $saldo, necesario ${cmd.amount})"
        }
        saldo - cmd.amount
    }
    is Recharge -> {
        check(saldo + cmd.amount <= MAX_SALDO) {
            "Límite superado (saldo $saldo, recarga ${cmd.amount})"
        }
        saldo + cmd.amount
    }
}

fun main() {
    val comandos = listOf(
        "RECARGA 80", "COMPRA 25", "COMPRA 100", "RECARGA 150", "BONO 10",
        "RECARGA xx", "COMPRA -3", "COMPRA", "RECARGA 120"
    )

    var saldo = 0
    var correctas = 0
    var errores = 0

    for (linea in comandos) {
        parse(linea)
            .mapCatching { cmd -> apply(saldo, cmd) }
            .onSuccess { nuevoSaldo ->
                saldo = nuevoSaldo
                correctas++
                println("$linea -> OK saldo=$saldo")
            }
            .onFailure { e ->
                errores++
                println("$linea -> ERROR: ${e.message}")
            }
    }

    println("Saldo final: $saldo")
    println("Operaciones OK: $correctas")
    println("Errores: $errores")
}