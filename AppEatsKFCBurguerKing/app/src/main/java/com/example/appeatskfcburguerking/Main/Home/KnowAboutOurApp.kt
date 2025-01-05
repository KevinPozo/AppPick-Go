package com.example.appeatskfcburguerking.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appeatskfcburguerking.R
import kotlin.text.trimIndent

@Composable
fun KnowAboutOurApp() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.imgabapp),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .graphicsLayer(alpha = 0.5f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(4.dp, Color.Black, RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "¡Bienvenido a Pick&Go!",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = """
                            Pick&Go es la app definitiva para hacer tus pedidos de comida más fáciles, rápidos y personalizados. 
                            
                            Ya sea que estés en KFC o Burger King, te ofrecemos una experiencia única adaptada a tus gustos y necesidades alimenticias. 
                            En nuestra plataforma, puedes crear menús personalizados según tus preferencias gastronómicas. 
                            ¿Eres vegetariano, vegano o tienes alguna restricción alimenticia? ¡No te preocupes! Con Pick&Go podrás ajustar tus pedidos según tus necesidades y disfrutar de un menú hecho a tu medida.
    
                            La app te permite realizar tu pedido directamente desde tu celular de forma rápida y sencilla. 
                            Solo debes elegir lo que más te guste, personalizarlo a tu gusto, y hacer el pago de manera segura desde la misma aplicación. 
                            Además, te notificamos cuándo tu pedido está listo para ser recogido, para que no tengas que esperar en el restaurante. Puedes elegir una hora específica para recoger tu comida y disfrutarla dentro o fuera del local, según prefieras.
    
                            Pero eso no es todo, Pick&Go también te ofrece promociones especiales mensuales, para que siempre encuentres algo nuevo y emocionante. ¡No olvides revisar las ofertas y descuentos exclusivos que tenemos para ti!
    
                            Creemos que cada cliente es único, por eso nuestra app está diseñada para ofrecerte una experiencia personalizada, cómoda y eficiente para cada ocasión. Ya no tendrás que preocuparte por las largas filas o la espera en el restaurante, todo estará al alcance de tu mano con solo un par de clics.
    
                            Esta app ha sido desarrollada por:
                            - Kevin Pozo
                            - Wulfer Quiguango
                            - Santiago Maldonado
    
                            Estamos comprometidos en brindarte la mejor experiencia de usuario. ¡Esperamos que disfrutes de tu tiempo con Pick&Go!
                        """.trimIndent(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}


