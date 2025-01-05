package com.example.appeatskfcburguerking.Main.KfcStore.Logic

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.Model.Opcion
import com.example.appeatskfcburguerking.Main.KfcStore.Model.Pedido
import com.example.appeatskfcburguerking.Main.KfcStore.Model.Producto

class CarritoViewModel : ViewModel() {
    val _productosSeleccionados = mutableStateListOf<Producto>()
    val _opcionesSeleccionadas = mutableStateListOf<Opcion>()
    val _historialDePedidos = mutableStateListOf<Pedido>()

    val productosSeleccionados: MutableList<Producto> get() = _productosSeleccionados
    val opcionesSeleccionadas: MutableList<Opcion> get() = _opcionesSeleccionadas

    fun calcularTotal(): Double {
        val totalProductos = productosSeleccionados.sumOf { (it.precio.toDouble()) * (it.cantidad.toDouble()) }
        val totalOpciones = opcionesSeleccionadas.sumOf { it.precio.toDouble() }
        return totalProductos + totalOpciones
    }

    fun confirmarPedido() {
        val total = calcularTotal()
        val nuevoPedido = Pedido(
            id = _historialDePedidos.size + 1,
            productos = _productosSeleccionados.toList(),
            opciones = _opcionesSeleccionadas.toList(),
            total = total
        )
        _historialDePedidos.add(nuevoPedido)
    }
    fun limpiarHistorial() {
        _historialDePedidos.clear()
    }
    fun limpiarCarrito() {
        _productosSeleccionados.clear()
        _opcionesSeleccionadas.clear()
    }

}

