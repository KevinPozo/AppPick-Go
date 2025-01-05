package com.example.appeatskfcburguerking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appeatskfcburguerking.Main.BKingStore.BRKScreen
import com.example.appeatskfcburguerking.Main.BKingStore.CarritoDeComprasScreenBK
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.CarritoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.Logic.PedidoViewModelBK
import com.example.appeatskfcburguerking.Main.BKingStore.PersonalizacionScreenBK
import com.example.appeatskfcburguerking.Main.BKingStore.RealizarPagoCarritoScreenBK
import com.example.appeatskfcburguerking.Main.BKingStore.VerResumenPedidoScreenBK
import com.example.appeatskfcburguerking.Main.Home.HomeScreen
import com.example.appeatskfcburguerking.Main.Home.KnowAboutOurApp
import com.example.appeatskfcburguerking.ui.theme.AppEatsKFCBurguerKingTheme
import com.example.appeatskfcburguerking.Main.Home.WelcomeScreenContent
import com.example.appeatskfcburguerking.Main.KfcStore.CarritoDeComprasScreen
import com.example.appeatskfcburguerking.Main.KfcStore.KfcScreen
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.CarritoViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.Logic.PedidoViewModel
import com.example.appeatskfcburguerking.Main.KfcStore.PersonalizacionScreen
import com.example.appeatskfcburguerking.Main.KfcStore.RealizarPagoCarritoScreen
import com.example.appeatskfcburguerking.Main.KfcStore.VerResumenPedidoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEatsKFCBurguerKingTheme {
                val navController = rememberNavController()

                val pedidoViewModel: PedidoViewModel = viewModel()
                val carritoViewModel: CarritoViewModel = viewModel()
                val pedidoViewModelBK: PedidoViewModelBK = viewModel()
                val carritoViewModelBK: CarritoViewModelBK = viewModel()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { WelcomeScreenContent(navController) }
                    composable("about") { KnowAboutOurApp() }
                    composable("entrarApp") { HomeScreen(navController) }
                    //TIENDA KFC:
                    composable("tienda_kfc") { KfcScreen(navController, pedidoViewModel) }
                    composable("personalizacion_screen") { PersonalizacionScreen(navController, pedidoViewModel) }
                    composable("verresumenpedido") { VerResumenPedidoScreen(navController,pedidoViewModel,carritoViewModel) }
                    composable("cart_screen") { CarritoDeComprasScreen(carritoViewModel,navController) }
                    composable("realizarpagocarrito"){ RealizarPagoCarritoScreen(carritoViewModel,navController) }
                    //TIENDA BK:
                    composable("tienda_brk") { BRKScreen(navController, pedidoViewModelBK) }
                    composable("personalizacion_screenBK"){ PersonalizacionScreenBK(navController,pedidoViewModelBK) }
                    composable("verresumenpedidoBK"){ VerResumenPedidoScreenBK(navController,pedidoViewModelBK,carritoViewModelBK) }
                    composable("cart_screenBK"){ CarritoDeComprasScreenBK(carritoViewModelBK,navController) }
                    composable("realizarpagocarritobk"){ RealizarPagoCarritoScreenBK(carritoViewModelBK,navController) }
                }
            }
        }
    }
}
