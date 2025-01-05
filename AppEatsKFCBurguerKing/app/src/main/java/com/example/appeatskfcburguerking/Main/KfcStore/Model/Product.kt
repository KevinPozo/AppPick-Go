package com.example.appeatskfcburguerking.Main.KfcStore.Model

data class Producto(
    val nombre: String,
    val precio: String,
    val categoria: String,
    val imagen: Int,
    val descripcion: String,
    var cantidad: Int = 0
)




