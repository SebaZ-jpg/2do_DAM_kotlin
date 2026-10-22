package Nivel_Medio

data class Product(
    val name: String,
    val category: String,
    val price: Double,
    val stock: Int
)

fun main() {
    val products = listOf(
        Product("Portátil", "Electrónica", 800.0, 5),
        Product("Ratón", "Electrónica", 20.0, 50),
        Product("Silla", "Hogar", 120.0, 10),
        Product("Mesa", "Hogar", 200.0, 4),
        Product("Libro", "Cultura", 15.0, 100)
    )

    println(products.first())

    products
        .groupBy { it.category }
        .mapValues { (_, items) -> items.sumOf { it.stock } }
        .toSortedMap()
        .forEach { (category, total) -> println("$category: $total") }

    //Producto más caro
    val masCaro = products.maxByOrNull { it.price }
    if (masCaro != null) {
        println("Más caro: ${masCaro.name} (${masCaro.price})")
    }
}