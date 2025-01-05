package com.example.appeatskfcburguerking.Main.KfcStore.Model

data class Pedido(
    val id: Int,
    val productos: List<Producto>,
    val opciones: List<Opcion>,
    val total: Double
)
