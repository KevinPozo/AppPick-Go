package com.example.appeatskfcburguerking.Main.KfcStore.Model

data class Opcion(
    val nombre: String,
    val precio: Double,
    val tipo: TipoDeOpcion,
    var cantidad: Int = 0
)

enum class TipoDeOpcion {
    PRESA,PAPAS,ALITAS,NUGGETS,POPCORN,ENSALADA,BEBIDA,SABORBEBIDA, JUGO,SABORJUGO
}
