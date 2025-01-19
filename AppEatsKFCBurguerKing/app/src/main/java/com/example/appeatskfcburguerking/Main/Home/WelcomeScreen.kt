package com.example.appeatskfcburguerking.Main.Home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appeatskfcburguerking.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreenContent(navController: NavController) {
    var showSplash by remember { mutableStateOf(true) }

    val scale by animateFloatAsState(
        targetValue = if (showSplash) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 300f,
        )
    )
    val opacity by animateFloatAsState(
        targetValue = if (showSplash) 0.56f else 0.87f,
        animationSpec = tween(1000)
    )

    val rotation by animateFloatAsState(
        targetValue = if (showSplash) 360f else 0f,
        animationSpec = tween(1000, easing = LinearOutSlowInEasing)
    )

    val infiniteTransition = rememberInfiniteTransition()
    val backgroundColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF76C7C0),
        targetValue = Color(0xFF05445E),
        animationSpec = infiniteRepeatable(
            animation = tween(1540),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) {
        delay(2800)
        showSplash = false
        navController.navigate("entrarApp") {
            popUpTo("home") { inclusive = true }
        }
    }
    if (showSplash) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de la empresa",
                    modifier = Modifier
                        .size(240.dp)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            alpha = opacity,
                            rotationZ = rotation
                        )
                        .clip(CircleShape)
                        .border(2.9.dp, Color.Black, CircleShape)
                        .shadow(elevation = 100.dp, shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
