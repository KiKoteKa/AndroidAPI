package zubkov.vadim.pruebasandroiddiseo

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp

/**
 * usando el AnimatedVisibility
 */

@Composable
fun Animacion(){
    val state = remember {
        MutableTransitionState(false).apply {
            // Empieza la animacion inmediatamente
            targetState = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = LinearEasing
                )
            ) + slideInHorizontally(
                animationSpec = tween(
                    durationMillis = 3000,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            Text(text = "Hola")
        }
    }
}