package com.example.appeatskfcburguerking.Main.BKingStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.CarritoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.PedidoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.Model.TipoDeOpcion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerResumenPedidoScreenBK(navController: NavController, pedidoViewModelBK: PedidoViewModelBK = viewModel(), carritoViewModelBK: CarritoViewModelBK = viewModel()) {
    val productos = pedidoViewModelBK.productosSeleccionados
    val opciones = pedidoViewModelBK.opcionesSeleccionadas
    val total = pedidoViewModelBK.calcularTotal()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Resumen de Pedido", color = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        carritoViewModelBK.productosSeleccionados.addAll(pedidoViewModelBK.productosSeleccionados)
                        carritoViewModelBK.opcionesSeleccionadas.addAll(pedidoViewModelBK.opcionesSeleccionadas)
                        pedidoViewModelBK.limpiarSeleccion()

                        carritoViewModelBK.confirmarPedido()

                        navController.navigate("tienda_brk")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)

                ) {
                    Text("Confirmar Pedido", color = Color.White)
                }
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 8.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Productos seleccionadas", style = MaterialTheme.typography.bodyMedium)
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        productos.forEach { producto ->
                            ResumenItemCard(
                                nombre = producto.nombre,
                                precio = producto.precio.toDouble(),
                                cantidad = producto.cantidad,
                                imagen = producto.imagen
                            )
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Opciones seleccionadas", style = MaterialTheme.typography.bodyMedium)
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        if (opciones.isEmpty()) {
                            Text("No se han seleccionado opciones", style = MaterialTheme.typography.bodySmall)
                        } else {
                            opciones.forEach { opcion ->
                                ResumenItemCard(
                                    nombre = opcion.nombre,
                                    precio = opcion.precio,
                                    cantidad = opcion.cantidad,
                                    tipo = opcion.tipo
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Total: \$${"%.2f".format(total)}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

        }
    }

}

@Composable
fun ResumenItemCard(
    nombre: String,
    precio: Double,
    cantidad: Int,
    tipo: TipoDeOpcion? = null,
    imagen: Int? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            imagen?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(nombre, style = MaterialTheme.typography.bodyMedium)

                    if (tipo !in listOf(TipoDeOpcion.CARNE, TipoDeOpcion.SABORBEBIDA, TipoDeOpcion.SABORSHAKE)) {
                        Text("Precio: \$${"%.2f".format(precio)}")
                    }
                }

                if (tipo !in listOf(TipoDeOpcion.CARNE, TipoDeOpcion.SABORBEBIDA, TipoDeOpcion.SABORSHAKE)) {
                    Text(cantidad.toString(), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}












