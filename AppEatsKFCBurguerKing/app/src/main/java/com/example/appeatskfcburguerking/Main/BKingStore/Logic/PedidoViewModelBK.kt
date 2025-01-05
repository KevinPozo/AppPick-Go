package com.example.appeatskfcburguerking.Main.BKingStore.Logic

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.appeatskfcburguerking.Main.BKingStore.Model.Opcion
import com.example.appeatskfcburguerking.Main.BKingStore.Model.Producto

class PedidoViewModelBK : ViewModel() {
    val productosSeleccionados = mutableListOf<Producto>()

    private val _opcionesSeleccionadas = mutableStateListOf<Opcion>()
    val opcionesSeleccionadas: List<Opcion> get() = _opcionesSeleccionadas

    fun agregarProducto(producto: Producto) {
        productosSeleccionados.add(producto)
    }

    fun agregarOpcion(opcion: Opcion) {
        if (!_opcionesSeleccionadas.contains(opcion)) {
            _opcionesSeleccionadas.add(opcion)
            println("Opción agregada: ${opcion.nombre}")
        }
    }

    fun eliminarOpcion(opcion: Opcion) {
        _opcionesSeleccionadas.remove(opcion)
        println("Opción eliminada: ${opcion.nombre}")
    }

    fun calcularTotal(): Double {
        var total = productosSeleccionados.sumOf { it.precio.toDouble() * it.cantidad }
        total += opcionesSeleccionadas.sumOf { it.precio * it.cantidad }
        return total
    }

    fun actualizarCantidadOpcion(opcion: Opcion, nuevaCantidad: Int) {
        val index = _opcionesSeleccionadas.indexOfFirst { it.nombre == opcion.nombre }
        if (index != -1) {
            val opcionExistente = _opcionesSeleccionadas[index]
            opcionExistente.cantidad = nuevaCantidad.coerceAtLeast(1)
        }
    }

    fun limpiarSeleccion() {
        productosSeleccionados.clear()
        _opcionesSeleccionadas.clear()
    }
}


