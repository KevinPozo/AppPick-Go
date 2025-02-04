package com.example.appeatskfcburguerking.Main.BKingStore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.PedidoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.Model.Opcion
import com.example.appeatskfcburguerking.Main.BKingStore.Model.TipoDeOpcion
import com.example.appeatskfcburguerking.R

val opciones = listOf(
    Opcion("Res", 0.0, TipoDeOpcion.CARNE,0),
    Opcion("Pollo", 0.0, TipoDeOpcion.CARNE,0),
    Opcion("Vegetal", 0.0, TipoDeOpcion.CARNE,0),

    Opcion("Porción de Papas Personales", 1.65, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Familiar", 3.85, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Tumbay Mediana", 6.45, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Tumbay Grande", 7.85, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Tumbay Familiar", 9.75, TipoDeOpcion.PAPAS,0),

    Opcion("Porción de Camote Personal", 7.45, TipoDeOpcion.CAMOTE,0),
    Opcion("Porción de Camote Junior", 4.65, TipoDeOpcion.CAMOTE,0),
    Opcion("Porción de Camote Familiar", 10.45, TipoDeOpcion.CAMOTE,0),

    Opcion("Porción de 6 Alitas", 4.29, TipoDeOpcion.ALITAS,0),
    Opcion("Porción de 12 Alitas", 8.39, TipoDeOpcion.ALITAS,0),
    Opcion("Porción de 20 Alitas", 14.99, TipoDeOpcion.ALITAS,0),

    Opcion("Porción de 6 Nuggets", 3.58, TipoDeOpcion.NUGGETS,0),
    Opcion("Porción de 10 Nuggets", 5.25, TipoDeOpcion.NUGGETS,0),
    Opcion("Porción de 18 Nuggets", 9.65, TipoDeOpcion.NUGGETS,0),

    Opcion("Bebida de 1L", 1.45, TipoDeOpcion.BEBIDA,0),
    Opcion("Bebida de 355ML", 1.87, TipoDeOpcion.BEBIDA,0),

    Opcion("Marca Coca Cola", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Pepsi", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Fanta", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Inka Kola", 0.0, TipoDeOpcion.SABORBEBIDA,0),

    Opcion("Shake Oreo de 130ML", 1.17, TipoDeOpcion.SHAKE,0),
    Opcion("Shake Oreo de 270ML", 2.75, TipoDeOpcion.SHAKE,0),
    Opcion("Shake Oreo de 400ML", 4.39, TipoDeOpcion.SHAKE,0),

    Opcion("Artificial de Vainilla", 0.0, TipoDeOpcion.SABORSHAKE,0),
    Opcion("Artificial de Chocolate", 0.0, TipoDeOpcion.SABORSHAKE,0),
    Opcion("Artificial de Fresa", 0.0, TipoDeOpcion.SABORSHAKE,0)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalizacionScreenBK(navController: NavController, pedidoViewModelBK: PedidoViewModelBK) {
    val groupedOptions = opciones.groupBy { it.tipo }
    val seleccionPorGrupo = remember { mutableStateMapOf<TipoDeOpcion, Opcion?>() }
    val expandedGroups = remember { mutableStateMapOf<TipoDeOpcion, Boolean>() }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            "Personaliza tu pedido",
                            style = TextStyle(fontSize = 20.sp, color = Color.White)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFDA7D0B)),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White.copy(alpha = 0.95f))
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Elige tus complementos favoritos, Recuerda Seleccionar el Tipo de Carne:",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f).padding(bottom = 8.dp)
                ) {
                    groupedOptions.forEach { (tipo, opcionesPorTipo) ->
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        expandedGroups[tipo] = !(expandedGroups[tipo] ?: false)
                                    }
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = when (tipo) {
                                        TipoDeOpcion.CARNE -> "Selección de Tipo de Carne:"
                                        TipoDeOpcion.PAPAS -> "Selección de Papas:"
                                        TipoDeOpcion.CAMOTE -> "Selección de Camotes:"
                                        TipoDeOpcion.ALITAS -> "Selección de Alitas:"
                                        TipoDeOpcion.NUGGETS -> "Selección de Nuggets:"
                                        TipoDeOpcion.BEBIDA -> "Selección de Tamaño Bebida:"
                                        TipoDeOpcion.SABORBEBIDA -> "Selección de Sabor de Bebida:"
                                        TipoDeOpcion.SHAKE -> "Selección de Tamaño de Shake Oreo:"
                                        TipoDeOpcion.SABORSHAKE -> "Selección de Sabor de Shake Oreo:"
                                        else -> "Otros"
                                    },
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = if (expandedGroups[tipo] == true) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = "Expandir o Contraer",
                                    tint = Color.Black
                                )
                            }
                        }

                        if (expandedGroups[tipo] == true) {
                            items(opcionesPorTipo) { opcion ->
                                Card(
                                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = opcion.nombre,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start,
                                            modifier = Modifier.padding(vertical = 8.dp)
                                        ) {
                                            val isSelected = seleccionPorGrupo[tipo] == opcion
                                            RadioButton(
                                                selected = isSelected,
                                                onClick = {
                                                    if (!isSelected) {
                                                        seleccionPorGrupo[tipo] = opcion
                                                        pedidoViewModelBK.agregarOpcion(opcion)
                                                    } else {
                                                        seleccionPorGrupo[tipo] = null
                                                        pedidoViewModelBK.eliminarOpcion(opcion)
                                                    }
                                                }
                                            )
                                            Text(
                                                opcion.nombre,
                                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                            )
                                        }

                                        if (tipo !in listOf(TipoDeOpcion.CARNE)) {
                                            var cantidad by remember { mutableStateOf(opcion.cantidad) }

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                modifier = Modifier.padding(vertical = 8.dp)
                                            ) {
                                                IconButton(
                                                    onClick = {
                                                        if (cantidad > 1) {
                                                            cantidad -= 1
                                                            pedidoViewModelBK.actualizarCantidadOpcion(
                                                                opcion,
                                                                cantidad
                                                            )
                                                        } else if (cantidad == 1) {
                                                            seleccionPorGrupo[tipo] = null
                                                            pedidoViewModelBK.eliminarOpcion(opcion)
                                                            cantidad = 0
                                                        }
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.Default.RemoveCircle,
                                                        contentDescription = "Disminuir cantidad"
                                                    )
                                                }

                                                Text(
                                                    text = cantidad.toString(),
                                                    style = MaterialTheme.typography.bodyMedium
                                                )

                                                IconButton(
                                                    onClick = {
                                                        cantidad += 1
                                                        pedidoViewModelBK.actualizarCantidadOpcion(
                                                            opcion,
                                                            cantidad
                                                        )
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.Default.Add,
                                                        contentDescription = "Incrementar cantidad"
                                                    )
                                                }
                                            }
                                        }

                                        if (tipo !in listOf(
                                                TipoDeOpcion.CARNE,
                                                TipoDeOpcion.SABORBEBIDA,
                                                TipoDeOpcion.SABORSHAKE
                                            )
                                        ) {
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text(
                                                "Precio: \$${opcion.precio}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (seleccionPorGrupo.values.any { it != null }) {
                            navController.navigate("verresumenpedidoBK")
                        } else {
                            println("Por favor, selecciona al menos una opción")
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Continuar con el Pedido", color = Color.White)
                }
            }
        }
    }
}
