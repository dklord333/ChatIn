package com.example.chatin.Animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.Bordeanimation(): Modifier {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return this.drawBehind {
        val strokeWidth = 4.dp.toPx()
        val cornerRadiusPx = 24.dp.toPx()

        // Static Blue Border
        drawRoundRect(
            color = Color(0xFF14469F),
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(cornerRadiusPx),
            style = Stroke(width = strokeWidth)
        )

        // Animated Pink shimmer (over border only)
        val shimmerBrush = Brush.linearGradient(
            colors = listOf(
                Color.Transparent,
                Color(0xFFDA3068),
                Color.Transparent
            ),
            start = Offset(
                x = size.width * animatedOffset - size.width,
                y = 0f
            ),
            end = Offset(
                x = size.width * animatedOffset,
                y = size.height
            )
        )

        drawRoundRect(
            brush = shimmerBrush,
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(cornerRadiusPx),
            style = Stroke(width = strokeWidth)
        )

    }

}
@Composable
fun Modifier.BoxBordeanimation(): Modifier {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return this.drawBehind {
        val strokeWidth = 2.dp.toPx()
        val cornerRadiusPx = 24.dp.toPx()

        // Static Blue Border
        drawRoundRect(
            color = Color(0xFF14469F),
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(cornerRadiusPx),
            style = Stroke(width = strokeWidth)
        )

        // Animated Pink shimmer (over border only)
        val shimmerBrush = Brush.linearGradient(
            colors = listOf(
                Color.Transparent,
                Color(0xFFDA3068),
                Color.Transparent
            ),
            start = Offset(
                x = size.width * animatedOffset - size.width,
                y = 0f
            ),
            end = Offset(
                x = size.width * animatedOffset,
                y = size.height
            )
        )

        drawRoundRect(
            brush = shimmerBrush,
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(cornerRadiusPx),
            style = Stroke(width = strokeWidth)
        )

    }

}