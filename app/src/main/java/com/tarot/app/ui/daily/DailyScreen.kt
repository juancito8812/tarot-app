package com.tarot.app.ui.daily
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tarot.app.ui.components.CardBack
import com.tarot.app.ui.theme.BackgroundGradient
import com.tarot.app.ui.theme.CardBorderGold
import com.tarot.app.ui.theme.ClassicGold
import com.tarot.app.ui.theme.DividerGold
import com.tarot.app.ui.theme.Parchment

@Composable
fun DailyScreen(viewModel: DailyViewModel = hiltViewModel()) {
    val cardReading by viewModel.cardReading.collectAsState()
    val isFlipped by viewModel.isFlipped.collectAsState()
    val context = LocalContext.current
    val density = LocalDensity.current.density

    val rotation = animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "cardFlip"
    )

    val glowAlpha by rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))

            Text(
                text = "✦ Carta del Día ✦",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Serif,
                color = ClassicGold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(thickness = 1.dp, color = DividerGold)

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 280.dp)
                    .graphicsLayer(
                        rotationY = rotation.value,
                        cameraDistance = 8 * density
                    )
                    .clickable { viewModel.flipCard() },
                contentAlignment = Alignment.Center
            ) {
                if (rotation.value < 90f) {
                    CardBack(modifier = Modifier.fillMaxSize())
                } else {
                    val card = cardReading.card
                    val resId = context.resources
                        .getIdentifier(card.image, "drawable", context.packageName)
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(rotationY = 180f)
                            .border(2.dp, CardBorderGold, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Parchment),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column {
                            if (resId != 0) {
                                Image(
                                    painter = painterResource(id = resId),
                                    contentDescription = card.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                    contentScale = ContentScale.Fit
                                )
                            }
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    card.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontFamily = FontFamily.Serif
                                )
                                Text(
                                    cardReading.meaning,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                if (cardReading.isReversed) {
                                    Text(
                                        "(Invertida)",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Toca la carta para revelar tu guía diaria",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                color = ClassicGold.copy(alpha = if (isFlipped) 0.4f else glowAlpha)
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}