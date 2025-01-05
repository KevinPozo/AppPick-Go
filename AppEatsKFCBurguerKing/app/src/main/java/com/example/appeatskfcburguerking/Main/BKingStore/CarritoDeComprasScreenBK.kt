package com.example.appeatskfcburguerking.Main.BKingStore
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.CarritoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.Model.TipoDeOpcion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoDeComprasScreenBK(carritoViewModelBK: CarritoViewModelBK, navController: NavController) {
    val productos = carritoViewModelBK.productosSeleccionados
    val opciones = carritoViewModelBK.opcionesSeleccionadas
    val total = carritoViewModelBK.calcularTotal()
    val historialDePedidos = carritoViewModelBK._historialDePedidos

    var expandedProductos by remember { mutableStateOf(false) }
    var expandedOpciones by remember { mutableStateOf(false) }
    var expandedHistorial by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Carrito de Compras", color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
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
                        navController.navigate("realizarpagocarritobk")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Realizar Pago", color = Color.White)
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Productos Seleccionados (Carrito)", style = MaterialTheme.typography.bodyMedium)
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { expandedProductos = !expandedProductos },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Ver Productos",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        if (expandedProductos) {
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
                        .padding(vertical = 8.dp)
                        .clickable { expandedOpciones = !expandedOpciones },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Ver Opciones",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        if (expandedOpciones) {
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

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Historial de Pedidos", style = MaterialTheme.typography.bodyMedium)
                }
            }

            if (historialDePedidos.isNotEmpty()) {
                val ultimoPedido = historialDePedidos.last()
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("------------------------------------------------------", style = MaterialTheme.typography.bodyMedium)
                        Text("Pedido Registrado", style = MaterialTheme.typography.bodyMedium)
                        Text("Total: \$${"%.2f".format(ultimoPedido.total)}", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Productos:", style = MaterialTheme.typography.bodySmall)
                        ultimoPedido.productos.forEach { producto ->
                            Text("${producto.nombre} - Cantidad: ${producto.cantidad}", style = MaterialTheme.typography.bodySmall)
                        }

                        Text("Opciones:", style = MaterialTheme.typography.bodySmall)
                        ultimoPedido.opciones.forEach { opcion ->
                            if (opcion.tipo == TipoDeOpcion.CARNE) {
                                Text("Tipo de Carne: ${opcion.nombre}", style = MaterialTheme.typography.bodySmall)
                            } else {
                                Text("${opcion.nombre} - Cantidad: ${opcion.cantidad}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        Text("------------------------------------------------------", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                item {
                    Text("No hay pedidos anteriores.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}







