package com.example.appeatskfcburguerking.Main.BKingStore

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
import com.example.appeatskfcburguerking.Main.BKingStore.Model.*
import com.example.appeatskfcburguerking.R
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.PedidoViewModelBK

val productosClasicas = listOf(
    Producto(
        "Whopper",
        "7.50",
        "Las Clásicas",
        R.drawable.whoppercl1,
        "Hamburguesa grande a la parrilla con tomates, lechuga, mayonesa, ketchup, pepinillos y aros de cebolla",
        0
    ),
    Producto(
        "Whopper Cheese Grande",
        "8.70",
        "Las Clásicas",
        R.drawable.whoperquesograndecl1,
        "Hamburguesa grande a la parrilla con queso, tomates, lechuga, mayonesa, ketchup, pepinillos y aros de cebolla",
        0
    ),
    Producto(
        "Whopper Crispy",
        "9.95",
        "Las Clásicas",
        R.drawable.whopercryspicl1,
        "Hamburguesa grande a la parrilla con queso, cebollitas crujientes, salsa BBQ, tomate, lechuga y mayonesa",
        0
    ),
    Producto(
        "Whopper Tejana",
        "10.89",
        "Las Clásicas",
        R.drawable.whopertejanacl2,
        "Hamburguesa grande a la parrilla con queso, tocino, tomates, lechuga, mayonesa, salsa BBQ, pepinillos y aros de cebolla",
        0
    )
)
val productosJunior = listOf(
    Producto("Whopper Cheese Junior",
        "5.65",
        "Junior",
        R.drawable.whoperchesjuniorcl1,
        "Hamburguesa junior a la parrilla con queso, tomates, lechuga, mayonesa, ketchup, pepinillos y aros de cebolla",
        0),
    Producto("Whopper Crispy Junior",
        "6.85",
        "Junior",
        R.drawable.whopercruspyjuniorcl1,
        "Hamburguesa junior a la parrilla con queso, cebollitas crujientes, salsa BBQ, tomate, lechuga y mayonesa",
        0),
    Producto("Whopper Tejana Junior",
        "7.50",
        "Junior",
        R.drawable.whoppertejanajuniorcl1,
        "Hamburguesa junior a la parrilla con queso, tocino, tomates, lechuga, mayonesa, salsa BBQ, pepinillos y aros de cebolla",
        0),
    Producto("Stacker Doble Junior",
        "11.45",
        "Junior",
        R.drawable.stackerdoblejuniorcl1,
        "Hamburguesa regular de doble carne a la parrilla con doble queso, tocino y salsa BK STACKER",
        0)
    )
val productosCompartir = listOf(
    Producto(
        "El Rey Whopper Para 3",
        "14.99",
        "Promos Grupales",
        R.drawable.promosgrupales3,
        "3 Hamburguesas WHOPPER grandes, 1 papa familiar",
        0
    ),
    Producto(
        "Rey Stacker Para 3",
        "17.69",
        "Promos Grupales",
        R.drawable.reystackerpara3,
        "3 Hamburguesas BK STACKER Doble junior, 1 papa familiar",
        0
    ),
    Producto(
        "Rey Stacker Para 4",
        "18.45",
        "Promos Grupales",
        R.drawable.reystackerpara4,
        "4 Hamburguesas BK STACKER Doble junior, 1 papa familiar",
        0
    ),
    Producto(
        "Pack Mega Familiar",
        "16.45",
        "Promos Grupales",
        R.drawable.packmegafamiliarwhoper,
        "2 Hamburguesas WHOPPER grandes, 3 Hamburguesas con Queso junior, 2 papas familiares",
        0
    ),
    Producto(
        "Fiesta Whopper",
        "12.65",
        "Promos Grupales",
        R.drawable.fiestawhoper,
        "2 Hamburguesas WHOPPER grande, 2 papas junior y 1 gaseosa 1L",
        0
    ),
    Producto(
        "Duo Stacker + Gas",
        "14.50",
        "Promos Grupales",
        R.drawable.stackerduo,
        "2 Hamburguesas STACKER simple junior, 2 papas junior y 1 gaseosa 1L",
        0
    ),
    Producto(
        "Pack Parrillero + Gas",
        "16.90",
        "Promos Grupales",
        R.drawable.packparrllero,
        "2 Hamburguesas Parrillera junior, 2 papas junior y 1 gaseosa 1L",
        0
    ),
    Producto(
        "2x1 El Rey",
        "14.90",
        "Promos Grupales",
        R.drawable.dosporunoelrey,
        "2 Hamburguesas junior de doble carne a la parrilla, acompañadas de queso, tocino, pepinillos, ketchup y mostaza, 2 papas junior",
        0
    ),
    Producto(
        "Pack Parrillero",
        "11.50",
        "Promos Grupales",
        R.drawable.packparrilleroclasic1,
        "2 Hamburguesas Parrillera junior y 2 papas junior",
        0
    )
)
val productosVegetales = listOf(
    Producto(
        "Whopper Vegetal",
        "8.65",
        "Las Vegetales",
        R.drawable.whopervegetal1,
        "Hamburguesa grande hecha a base de plantas grande a la parrilla con tomates, lechuga, mayonesa, ketchup, pepinillos y aros de cebolla",
        0
    ),
    Producto(
        "Whopper Vegetal con Queso",
        "9.69",
        "Las Vegetales",
        R.drawable.whopervegetalconqueso1,
        "Hamburguesa grande hecha a base de plantas grande a la parrilla con queso, tomates, lechuga, mayonesa, ketchup, pepinillos y aros de cebolla",
        0
    ),
    Producto(
        "Whopper Vegetal Crispy",
        "10.65",
        "Las Vegetales",
        R.drawable.whopervegetalcrispy1,
        "Hamburguesa grande hecha a base de plantas grande a la parrilla con queso, cebollitas crujientes, salsa BBQ, tomate, lechuga y mayonesa",
        0
    ),
    Producto(
        "Whopper Vegetal Tejana",
        "8.75",
        "Las Vegetales",
        R.drawable.vegetaltejana1,
        "Hamburguesa grande hecha a base de plantas grande a la parrilla con queso, tocino, tomates, lechuga, mayonesa, salsa BBQ, pepinillos y aros de cebolla",
        0
    )
)
val productosLasContundentes = listOf(
    Producto(
        "Philly Cheese",
        "6.49",
        "Las Contundentes",
        R.drawable.phillychese,
        "Conoce nuestra nueva Philly Cheese. Disfruta de doble carne 100% a la parrilla, 4 lascas de queso, tocino, cebolla frita y nuestra exclusiva Cheese sauce",
        0
    ),
    Producto(
        "Mega Stacker Triple",
        "12.65",
        "Las Contundentes",
        R.drawable.megastackertriple,
        "Nuestra BK STACKER Triple. Disfruta de triple hamburguesa 100% carne de res a la parrilla, queso, tocino y nuestra exclusiva salsa BK STACKER",
        0
    ),
    Producto(
        "Mega Stacker",
        "11.25",
        "Las Contundentes",
        R.drawable.megastackernormal,
        "Hamburguesa grande de doble carne a la parrilla con queso, tocino y salsa BK STACKER",
        0
    ),
    Producto(
        "Mega Doble con Queso",
        "12.65",
        "Las Contundentes",
        R.drawable.megadobleconqueso,
        "Hamburguesa grande de doble carne a la parrilla con queso, mostaza, pepinillos y ketchup",
        0
    ),
    Producto(
        "Mega Stacker Cuadruple",
        "14.99",
        "Las Contundentes",
        R.drawable.megastackercuadruple,
        "Nuestra BK STACKER Cuádruple. Disfruta de cuádruple hamburguesa 100% carne de res a la parrilla, queso, tocino y nuestra exclusiva salsa BK STACKER",
        0
    ),
    Producto(
        "Parrillera XL",
        "9.75",
        "Las Contundentes",
        R.drawable.parrilleraxl,
        "Hamburguesa grande a la parrilla con chorizo, papitas al hilo, mayonesa y ají",
        0
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BRKScreen(navController: NavController, pedidoViewModelBK: PedidoViewModelBK) {
    var selectedCategory by remember { mutableStateOf("Las Contundentes") }
    var searchQuery by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(0) }
    var showSnackbar by remember { mutableStateOf(false) }

    val categories = listOf("Las Clásicas", "Junior", "Promos Grupales", "Las Vegetales", "Las Contundentes")
    val categoryImages = listOf(
        R.drawable.clasicaslogo,
        R.drawable.juniorlogo,
        R.drawable.compartirlogo,
        R.drawable.vegetallogo,
        R.drawable.contundenteslogo
    )

    val allProductos = productosClasicas + productosJunior + productosCompartir + productosVegetales + productosLasContundentes

    val filteredProductos = allProductos.filter { producto ->
        producto.categoria == selectedCategory && producto.nombre.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Tienda Burger King",
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
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFDA7D0B))
            )
        }
        ,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("cart_screenBK") },
                containerColor = Color(0xFF092CA9)
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
                                                pedidoViewModelBK.agregarProducto(productoConCantidad)
                                                navController.navigate("personalizacion_screenBK")
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


