package com.example.appeatskfcburguerking.Main.Home
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallpaperhomescreen),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.7f),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = {
                android.os.Process.killProcess(android.os.Process.myPid())
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Cerrar aplicación",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate("about") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = "Acerca de",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                        .padding(1.dp)
                        .clickable { navController.navigate("tienda_kfc") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kfc_logo),
                        contentDescription = "Logo de KFC",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                        .padding(1.dp)
                        .clickable { navController.navigate("tienda_brk") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.burgerking_logo),
                        contentDescription = "Logo de Burger King",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}




