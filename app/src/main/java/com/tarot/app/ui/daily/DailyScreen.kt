package com.tarot.app.ui.daily
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tarot.app.ui.components.CardBack

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Today's Card",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    if (resId != 0) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = card.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(card.name, style = MaterialTheme.typography.titleLarge)
                        Text(cardReading.meaning, style = MaterialTheme.typography.bodyMedium)
                        if (cardReading.isReversed) {
                            Text("(Reversed)", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Tap the card to reveal your daily guidance",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
