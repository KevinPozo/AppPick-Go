package com.example.appeatskfcburguerking.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.R

@Composable
fun WelcomeScreenContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallpaperhomescreen),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
                .graphicsLayer(alpha = 0.75f),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.logoappempresa),
            contentDescription = "Logo Empresa",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 32.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
                .border(3.dp, Color.Black, RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            Box(
                modifier = Modifier
                    .offset(y = (-165).dp)
                    .size(250.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                        .padding(start = 3.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("entrarApp") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(bottom = 10.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                    .shadow(10.dp, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = "Entrar a la App",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate("about") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B2ACC)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 10.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                    .shadow(10.dp, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = "¿Quieres saber más de esta app?",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
