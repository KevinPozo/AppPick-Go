package com.example.appeatskfcburguerking.Main.KfcStore

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
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.PedidoViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.Model.Opcion
import com.example.appeatskfcburguerking.Main.KfcStore.Model.TipoDeOpcion
import com.example.appeatskfcburguerking.R

val opciones = listOf(
    Opcion("Original", 0.0, TipoDeOpcion.PRESA,0),
    Opcion("Crispy", 0.0, TipoDeOpcion.PRESA,0),
    Opcion("Mitad y Mitad", 0.0, TipoDeOpcion.PRESA,0),

    Opcion("Porción de Papas Pequeña", 1.89, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Mediana", 3.69, TipoDeOpcion.PAPAS,0),
    Opcion("Porción de Papas Grande", 4.59, TipoDeOpcion.PAPAS,0),

    Opcion("Porción de 12 Alitas", 6.39, TipoDeOpcion.ALITAS,0),
    Opcion("Porción de 18 Alitas", 9.89, TipoDeOpcion.ALITAS,0),
    Opcion("Porción de 25 Alitas", 18.39, TipoDeOpcion.ALITAS,0),

    Opcion("Porción de 6 Nuggets", 2.58, TipoDeOpcion.NUGGETS,0),
    Opcion("Porción de 10 Nuggets", 4.23, TipoDeOpcion.NUGGETS,0),
    Opcion("Porción de 20 Nuggets", 7.75, TipoDeOpcion.NUGGETS,0),

    Opcion("Porción de PopCorn Mediano", 2.35, TipoDeOpcion.POPCORN,0),
    Opcion("Porción de PopCorn Familiar", 5.17, TipoDeOpcion.POPCORN,0),

    Opcion("Porción de Ensalada Mediana", 1.41, TipoDeOpcion.ENSALADA,0),
    Opcion("Porción de Ensalada Grande", 2.68, TipoDeOpcion.ENSALADA,0),

    Opcion("Bebida de 2L", 1.17, TipoDeOpcion.BEBIDA,0),
    Opcion("Bebida de 1L", 1.45, TipoDeOpcion.BEBIDA,0),
    Opcion("Bebida de 355ML", 1.87, TipoDeOpcion.BEBIDA,0),

    Opcion("Marca Coca Cola", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Pepsi", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Sprite", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Fanta", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Inka Kola", 0.0, TipoDeOpcion.SABORBEBIDA,0),
    Opcion("Marca Gallito", 0.0, TipoDeOpcion.SABORBEBIDA,0),

    Opcion("Jugo de 130ML", 1.17, TipoDeOpcion.JUGO,0),
    Opcion("Jugo de 270ML", 2.75, TipoDeOpcion.JUGO,0),
    Opcion("Jugo de 400ML", 4.39, TipoDeOpcion.JUGO,0),

    Opcion("Natural de Mora", 0.0, TipoDeOpcion.SABORJUGO,0),
    Opcion("Natural de Naranjilla", 0.0, TipoDeOpcion.SABORJUGO,0),
    Opcion("Natural de Mango", 0.0, TipoDeOpcion.SABORJUGO,0),
    Opcion("Natural de Hawaiano", 0.0, TipoDeOpcion.SABORJUGO,0),
    Opcion("Natural de Taxo", 0.0, TipoDeOpcion.SABORJUGO,0),
    Opcion("Natural de Tropical", 0.0, TipoDeOpcion.SABORJUGO,0)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalizacionScreen(navController: NavController, pedidoViewModel: PedidoViewModel) {
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
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red),
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
                    text = "Elige tus complementos favoritos, Recuerda Seleccionar el Tipo de Presa:",
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
                                        TipoDeOpcion.PRESA -> "Selección de Tipo de Presa:"
                                        TipoDeOpcion.PAPAS -> "Selección de Papas:"
                                        TipoDeOpcion.ALITAS -> "Selección de Alitas:"
                                        TipoDeOpcion.NUGGETS -> "Selección de Nuggets:"
                                        TipoDeOpcion.POPCORN -> "Selección de PopCorn:"
                                        TipoDeOpcion.ENSALADA -> "Selección de Ensalada de Col:"
                                        TipoDeOpcion.BEBIDA -> "Selección de Tamaño Bebida:"
                                        TipoDeOpcion.SABORBEBIDA -> "Selección de Sabor de Bebida:"
                                        TipoDeOpcion.JUGO -> "Selección de Tamaño Jugo:"
                                        TipoDeOpcion.SABORJUGO -> "Selección de Sabor de Jugo:"
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
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
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
                                                        pedidoViewModel.agregarOpcion(opcion)
                                                    } else {
                                                        seleccionPorGrupo[tipo] = null
                                                        pedidoViewModel.eliminarOpcion(opcion)
                                                    }
                                                }
                                            )
                                            Text(
                                                opcion.nombre,
                                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                            )
                                        }

                                        if (tipo !in listOf(TipoDeOpcion.PRESA)) {
                                            var cantidad by remember { mutableStateOf(opcion.cantidad) }

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                modifier = Modifier.padding(vertical = 8.dp)
                                            ) {
                                                IconButton(
                                                    onClick = {
                                                        if (cantidad > 0) {
                                                            cantidad -= 1
                                                            if (cantidad == 0) {
                                                                seleccionPorGrupo[tipo] = null
                                                                pedidoViewModel.eliminarOpcion(opcion)
                                                            } else {
                                                                pedidoViewModel.actualizarCantidadOpcion(opcion, cantidad)
                                                            }
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
                                                        pedidoViewModel.actualizarCantidadOpcion(
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
                                                TipoDeOpcion.PRESA,
                                                TipoDeOpcion.SABORBEBIDA,
                                                TipoDeOpcion.SABORJUGO
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
                            navController.navigate("verresumenpedido")
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
