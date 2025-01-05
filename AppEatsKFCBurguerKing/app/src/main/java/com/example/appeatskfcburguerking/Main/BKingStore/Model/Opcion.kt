package com.example.appeatskfcburguerking.Main.BKingStore.Model

data class Opcion(
    val nombre: String,
    val precio: Double,
    val tipo: TipoDeOpcion,
    var cantidad: Int = 0
)

enum class TipoDeOpcion {
    CARNE,PAPAS,CAMOTE,NUGGETS,ALITAS,BEBIDA,SABORBEBIDA,SHAKE,SABORSHAKE
}
