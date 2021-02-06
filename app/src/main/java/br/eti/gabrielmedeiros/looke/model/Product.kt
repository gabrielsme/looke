package br.eti.gabrielmedeiros.looke.model

data class Product(
    val batters: Batters,
    val id: String,
    val name: String,
    val ppu: Double,
    val topping: List<Topping>,
    val type: String
)