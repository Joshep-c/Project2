package com.example.project2.Screen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

/**
 * Pantalla principal
 */
@Composable
fun AnimationScreen(modifier: Modifier = Modifier, navController: NavHostController? = null) {
    var currentState by remember { mutableStateOf(CircleVisualState.SMALL_BLUE) }
    var isAutoSequenceRunning by remember { mutableStateOf(false) }

    // Control de secuencia automática
    LaunchedEffect(isAutoSequenceRunning) {
        if (isAutoSequenceRunning) {
            val states = CircleVisualState.entries
            var index = states.indexOf(currentState)

            while (isActive) {
                delay(MotionTokens.AUTO_SEQUENCE_DELAY)
                index = (index + 1) % states.size
                currentState = states[index]
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            // Círculo animado
            AnimatedCircle(
                visualState = currentState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            // Controles de estado manual
            StateControls(
                currentState = currentState,
                onStateChange = { currentState = it },
                enabled = !isAutoSequenceRunning
            )

            // Control de secuencia automática
            SequenceControl(
                isRunning = isAutoSequenceRunning,
                onToggle = { isAutoSequenceRunning = !isAutoSequenceRunning }
            )

            Button(
                onClick = {
                    navController?.navigate("auth") {
                        popUpTo("animation") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = "Ir al inicio")
            }
        }
    }
}

/**
 * Motion design tokens centralizados con Material Design 3
 */
private object MotionTokens {
    const val DURATION_SHORT = 200
    const val DURATION_MEDIUM = 400
    const val DURATION_LONG = 600
    const val PULSE_DURATION = 220
    const val AUTO_SEQUENCE_DELAY = 1400L

    val EASING_STANDARD = FastOutSlowInEasing
    val EASING_EMPHASIZED = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    fun <T> springLowStiffness() = spring<T>(
        stiffness = Spring.StiffnessLow,
        dampingRatio = Spring.DampingRatioNoBouncy
    )

    fun <T> springMediumBouncy() = spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy
    )
}

/**
 * Estados visuales del círculo
 */
private enum class CircleVisualState {
    SMALL_BLUE,
    MEDIUM_GREEN,
    BIG_YELLOW;

    val size: Dp
        get() = when (this) {
            SMALL_BLUE -> 80.dp
            MEDIUM_GREEN -> 140.dp
            BIG_YELLOW -> 220.dp
        }

    val color: Color
        get() = when (this) {
            SMALL_BLUE -> Color(0xFF2196F3)
            MEDIUM_GREEN -> Color(0xFF4CAF50)
            BIG_YELLOW -> Color(0xFFFFC107)
        }

    val label: String
        get() = when (this) {
            SMALL_BLUE -> "Pequeño"
            MEDIUM_GREEN -> "Medio"
            BIG_YELLOW -> "Grande"
        }
}

/**
 * Círculo animado
 */
@Composable
private fun AnimatedCircle(
    visualState: CircleVisualState,
    modifier: Modifier = Modifier
) {
    // Transición principal para tamaño y color
    val transition = updateTransition(
        targetState = visualState,
        label = "circleTransition"
    )

    val size by transition.animateDp(
        transitionSpec = {
            tween(
                durationMillis = MotionTokens.DURATION_MEDIUM,
                easing = MotionTokens.EASING_EMPHASIZED
            )
        },
        label = "circleSize"
    ) { it.size }

    val color by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = MotionTokens.DURATION_MEDIUM,
                easing = MotionTokens.EASING_STANDARD
            )
        },
        label = "circleColor"
    ) { it.color }

    // Efecto de pulso al cambiar de estado
    val pulseScale = remember { Animatable(1f) }

    LaunchedEffect(visualState) {
        pulseScale.snapTo(1f)
        pulseScale.animateTo(
            targetValue = 1.08f,
            animationSpec = tween(
                durationMillis = MotionTokens.PULSE_DURATION,
                easing = MotionTokens.EASING_STANDARD
            )
        )
        pulseScale.animateTo(
            targetValue = 1f,
            animationSpec = MotionTokens.springMediumBouncy()
        )
    }

    // Contenedor con el círculo
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .graphicsLayer {
                    scaleX = pulseScale.value
                    scaleY = pulseScale.value
                }
                .clip(CircleShape)
                .background(color)
                .semantics {
                    contentDescription = "Círculo animado en estado ${visualState.label}"
                }
        )
    }
}

/**
 * Controles para cambiar el estado manualmente
 */
@Composable
private fun StateControls(
    currentState: CircleVisualState,
    onStateChange: (CircleVisualState) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CircleVisualState.entries.forEach { state ->
            Button(
                onClick = { onStateChange(state) },
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentState == state) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = state.label,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

/**
 * Control de secuencia automática
 */
@Composable
private fun SequenceControl(
    isRunning: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onToggle,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = if (isRunning) Icons.Filled.Pause else Icons.Filled.PlayArrow,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if (isRunning) "Detener secuencia" else "Iniciar secuencia",
            style = MaterialTheme.typography.labelLarge
        )
    }
}