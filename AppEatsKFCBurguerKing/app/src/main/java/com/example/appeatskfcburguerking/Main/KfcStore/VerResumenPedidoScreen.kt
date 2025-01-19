package com.example.appeatskfcburguerking.Main.KfcStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.CarritoViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.PedidoViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.Model.TipoDeOpcion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerResumenPedidoScreen(navController: NavController, pedidoViewModel: PedidoViewModel = viewModel(), carritoViewModel: CarritoViewModel = viewModel()) {
    val productos = pedidoViewModel.productosSeleccionados
    val opciones = pedidoViewModel.opcionesSeleccionadas
    val total = pedidoViewModel.calcularTotal()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Red
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
                        carritoViewModel.productosSeleccionados.addAll(pedidoViewModel.productosSeleccionados)
                        carritoViewModel.opcionesSeleccionadas.addAll(pedidoViewModel.opcionesSeleccionadas)
                        pedidoViewModel.limpiarSeleccion()

                        carritoViewModel.confirmarPedido()

                        navController.navigate("tienda_kfc")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Confirmar Pedido", color = Color.White)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.95f))
        ) {
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
                        Text("Productos seleccionados", style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
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
                        Text("Opciones seleccionadas", style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
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
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                        )
                    }
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
                    Text(nombre, style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))

                    if (tipo !in listOf(TipoDeOpcion.PRESA, TipoDeOpcion.SABORBEBIDA, TipoDeOpcion.SABORJUGO)) {
                        Text("Precio: \$${"%.2f".format(precio)}", color = Color.White)

                    }
                }

                if (tipo !in listOf(TipoDeOpcion.PRESA, TipoDeOpcion.SABORBEBIDA, TipoDeOpcion.SABORJUGO)) {
                    Text(cantidad.toString(), style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))
                }
            }
        }
    }
}












