package Nivel_Avanzado.ultimo

data class Parcel(val name: String, val grams: Int, val qty: Int)

data class Shipment(val id: Int, val city: String, val parcels: List<Parcel>) {
    val weight: Int
        get() = parcels.sumOf { it.grams * it.qty }
}

fun kilos(g: Int): String = "%d.%03d".format(g / 1000, g % 1000)

fun main() {
    val shipments = listOf(
        Shipment(1, "Madrid", listOf(Parcel("Lámpara", 1200, 2))),
        Shipment(2, "Sevilla", listOf(Parcel("Libro", 400, 3), Parcel("Lámpara", 1200, 1))),
        Shipment(3, "Madrid", listOf(Parcel("Libro", 400, 5), Parcel("Cuaderno", 250, 4))),
        Shipment(4, "Bilbao", listOf(Parcel("Cuaderno", 250, 10), Parcel("Libro", 400, 1)))
    )

    // 1. Peso total general con fold
    val total = shipments.fold(0) { acc, s -> acc + s.weight }
    println("Peso total: ${kilos(total)} kg")

    // 2. Peso por ciudad, de mayor a menor
    println("Peso por ciudad:")
    shipments
        .groupBy { it.city }
        .mapValues { (_, envios) -> envios.sumOf { it.weight } }
        .entries
        .sortedByDescending { it.value }
        .forEach { (city, gramos) -> println("  $city: ${kilos(gramos)} kg") }

    // 3. Unidades por producto, de mayor a menor
    println("Unidades por producto:")
    shipments
        .flatMap { it.parcels }
        .groupBy { it.name }
        .mapValues { (_, bultos) -> bultos.sumOf { it.qty } }
        .entries
        .sortedByDescending { it.value }
        .forEach { (name, unidades) -> println("  $name: $unidades") }

    // 4. Primer envío que supera 2.800 kg
    val primero = shipments.asSequence().firstOrNull { it.weight > 2800 }
    if (primero != null) {
        println("Primer envío > 2.800 kg: #${primero.id} (${kilos(primero.weight)} kg)")
    }
}