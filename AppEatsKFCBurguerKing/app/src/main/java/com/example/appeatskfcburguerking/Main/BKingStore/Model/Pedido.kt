package com.example.appeatskfcburguerking.Main.BKingStore.Model

data class Pedido(
    val id: Int,
    val productos: List<Producto>,
    val opciones: List<Opcion>,
    val total: Double
)
