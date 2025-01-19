package com.example.appeatskfcburguerking.Main.KfcStore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.Main.KfcStore.Model.Producto
import com.example.appeatskfcburguerking.R
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.PedidoViewModel

val productosFestin = listOf(
    Producto(
        "Mega Festin 8 Presas",
        "17.99",
        "Festines de Presas",
        R.drawable.mf8presas,
        "8 presas de pollo + 8 alitas picantes + 1 papa frita grande + 1 gaseosa 1L",
        0
    ),
    Producto(
        "Festin Familiar 3",
        "24.99",
        "Festines de Presas",
        R.drawable.ff8presas,
        "12 presas + 1 papa frita grande + 1 ensalada de col grande + 1 gaseosa 1L ",
        0
    ),
    Producto(
        "Festin Familiar 2",
        "21.99",
        "Festines de Presas",
        R.drawable.ff2presas,
        "10 presas + 1 papa frita grande + 1 ensalada de col grande + 1 gaseosa de 1L",
        0
    ),
    Producto(
        "Festin Familiar 1",
        "21.99",
        "Festines de Presas",
        R.drawable.ff1presas,
        "8 presas + 1 papa frita grande + 1 ensalada de col grande + 1 gaseosa de 1L",
        0
    ),
    Producto(
        "Mega Festin 10 Presas",
        "21.99",
        "Festines de Presas",
        R.drawable.mf10presas,
        "10 presas de pollo + 10 alitas picantes + 1 papa frita grande ",
        0
    ),
    Producto(
        "Wow Bucket",
        "15.99",
        "Festines de Presas",
        R.drawable.mf10presas,
        "8 presas de pollo + 1 papa frita grande ",
        0
    )
)
val productosPresasSolas = listOf(
    Producto("10 Presas KFC",
        "16.50",
        "Presas Solas",
        R.drawable.cubetageneral,
        "10 presas de pollo",
        0),
    Producto("7 Presas KFC",
        "12.99",
        "Presas Solas",
        R.drawable.cubetageneral,
        "7 presas de pollo",
        0),
    Producto("12 Presas KFC",
        "19.75",
        "Presas Solas",
        R.drawable.cubetageneral,
        "12 presas de pollo",
        0),
    Producto("15 Presas KFC",
        "23.99",
        "Presas Solas",
        R.drawable.cubetageneral,
        "15 presas de pollo",
        0)
    )
val productosCompartir = listOf(
    Producto(
        "Parte y Comparte 5 Presas",
        "11.99",
        "Para Compartir",
        R.drawable.compartir5pre,
        "5 presas de pollo + 2 papas pequeñas + 1 gaseosa 1L",
        0
    ),
    Producto(
        "Parte y Comparte Variedad",
        "14.50",
        "Para Compartir",
        R.drawable.parteycvar,
        "4 presas de pollo + 4 alitas picantes + 1 pop corn med + 2 papas fritas pequeñas + 1 gaseosa 1L ",
        0
    ),
    Producto(
        "Parte y Comparte Completo",
        "13.99",
        "Para Compartir",
        R.drawable.parteycc,
        "4 presas de pollo KFC + 2 papas fritas peq + 1 gaseosa 1L + 2 sundaes de Galak o Manicho (según disponibilidad)",
        0
    ),
    Producto(
        "Parte y Comparte Alitas",
        "14.99",
        "Para Compartir",
        R.drawable.parteyca,
        "4 presas de pollo kf + 4 alitas de sabores + 1 pop corn reg + 2 papas reg + 1 Gaseosa 1lt ",
        0
    )
)
val productosBoxes = listOf(
    Producto(
        "Big Box Ultra",
        "6.50",
        "Boxes",
        R.drawable.ultraboxes,
        "1 Ruster Sandwich + 4 Nuggets de pollo + 1 papa frita peq + 1 gaseosa 355ml + 1 helado jr de mora ",
        0
    ),
    Producto(
        "Big Box Gamer",
        "6.99",
        "Boxes",
        R.drawable.bbgamer,
        "1 presa de pollo + 2 alitas picantes +1 papa frita reg + 1 pop corn reg + gaseosa 355ml + 1 helado de mora jr ",
        0
    ),
    Producto(
        "Big Box Recargado",
        "7.50",
        "Boxes",
        R.drawable.bbrecargado,
        "2 presas + 2 alitas picantes + 1 papa frita pequeña + 1 ensalada de col pequeña + 1 gaseosa 355ml + 1 helado jr de mora ",
        0
    ),
    Producto(
        "Big Box Kentucky",
        "7.75",
        "Boxes",
        R.drawable.bbkentucky,
        "2 presas + 2 alitas picantes + 1 papa frita pequeña + 1 ensalada de col pequeña + 1 gaseosa 355ml + 1 helado jr de mora ",
        0
    )
)
val productoCombos = listOf(
    Producto(
        "Mega Combo 1 Alita",
        "5.99",
        "Combos",
        R.drawable.combo1alita,
        "1 presa de pollo + 3 alitas picantes + arroz + menestra + ensalada + 1 gaseosa 355ml",
        0
    ),
    Producto(
        "Combo Completo",
        "5.50",
        "Combos",
        R.drawable.combocompleto,
        "2 presas de pollo + 1 papa frita pequeña + 1 gaseosa 355ml ",
        0
    ),
    Producto(
        "Super Combo 3",
        "7.25",
        "Combos",
        R.drawable.spcomb3,
        "3 presas de pollo +arroz y menestra + ensalada mixta + 1 gaseosa 355ml ",
        0
    ),
    Producto(
        "Super Combo 2",
        "5.99",
        "Combos",
        R.drawable.spcomb2,
        "2 presas de pollo + arroz y menestra + ensalada mixta + 1 gaseosa 355ml ",
        0
    ),
    Producto(
        "Super Combo 1",
        "3.75",
        "Combos",
        R.drawable.spcomb1,
        "1 presa + arroz y menestra + ensalada mixta + 1 gaseosa 355ml",
        0
    ),
    Producto(
        "Combo Ideal",
        "6.50",
        "Combos",
        R.drawable.combideal,
        "3 presas de pollo KFC + 1 papa frita pequeña + 1 gaseosa 355ml",
        0
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KfcScreen(navController: NavController, pedidoViewModel: PedidoViewModel) {
    var selectedCategory by remember { mutableStateOf("Combos") }
    var searchQuery by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(0) }
    var showSnackbar by remember { mutableStateOf(false) }

    val categories = listOf("Festines de Presas", "Presas Solas", "Para Compartir", "Boxes", "Combos")
    val categoryImages = listOf(
        R.drawable.festineslogo,
        R.drawable.presassolaslogo,
        R.drawable.paracompartirlogo,
        R.drawable.boxeslogo,
        R.drawable.combologo
    )

    val allProductos = productosFestin + productosPresasSolas + productosCompartir + productosBoxes + productoCombos
    val filteredProductos = allProductos.filter { producto ->
        producto.categoria == selectedCategory && producto.nombre.contains(searchQuery, ignoreCase = true)
    }
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Tienda KFC",
                            style = TextStyle(fontSize = 20.sp, color = Color.White),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("entrarApp")
                    }) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Salir", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("cart_screen") },
                containerColor = Color(0xFFD0A50F)
            ) {
                Icon(Default.ShoppingCart, contentDescription = "Carrito")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White.copy(alpha = 0.95f))
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Buscar productos", color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(categories.size) { index ->
                        val category = categories[index]
                        val imageResId = categoryImages[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(120.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            onClick = { selectedCategory = category }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = "Imagen de $category",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.BottomCenter)
                                        .background(Color.Gray.copy(alpha = 0.5f))
                                        .padding(vertical = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = category,
                                        style = TextStyle(fontSize = 12.sp, color = Color.White),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    itemsIndexed(filteredProductos) { index, producto ->
                        var cantidadProducto by remember { mutableStateOf(0) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = producto.imagen),
                                    contentDescription = producto.nombre,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .background(Color.Gray)
                                        .clip(MaterialTheme.shapes.medium),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(producto.nombre, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text("Desde $ ${producto.precio}", color = Color.Gray)
                                    Text(
                                        producto.descripcion,
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        IconButton(
                                            onClick = {
                                                if (cantidadProducto > 0) {
                                                    cantidadProducto -= 1
                                                }
                                            }
                                        ) {
                                            Icon(Default.RemoveCircle, contentDescription = "Disminuir cantidad")
                                        }

                                        TextField(
                                            value = cantidadProducto.toString(),
                                            onValueChange = { newValue ->
                                                val newCantidad = newValue.toIntOrNull() ?: cantidadProducto
                                                if (newCantidad >= 0) cantidadProducto = newCantidad
                                            },
                                            modifier = Modifier.width(50.dp),
                                            textStyle = TextStyle(textAlign = TextAlign.Center),
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                        )

                                        IconButton(onClick = {
                                            cantidadProducto += 1
                                        }) {
                                            Icon(Default.Add, contentDescription = "Aumentar cantidad")
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Button(
                                        onClick = {
                                            if (cantidadProducto == 0) {
                                                showSnackbar = true
                                            } else {
                                                val productoConCantidad = producto.copy(cantidad = cantidadProducto)
                                                pedidoViewModel.agregarProducto(productoConCantidad)
                                                navController.navigate("personalizacion_screen")
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Red,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("Ordenarlo ¡Ahora!")
                                    }
                                }
                            }
                        }
                    }
                }


            }

            if (showSnackbar) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Text("Por favor selecciona una cantidad a ordenar", color = Color.Black)
                }
            }
        }
    }
}


