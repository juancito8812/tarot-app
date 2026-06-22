package com.tarot.app.ui.deck
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.tarot.app.data.model.TarotCard
import com.tarot.app.ui.theme.CardBorderGold
import com.tarot.app.ui.theme.ClassicBurgundy
import com.tarot.app.ui.theme.ClassicGold
import com.tarot.app.ui.theme.DarkText
import com.tarot.app.ui.theme.DividerGold
import com.tarot.app.ui.theme.Parchment

@Composable
fun CardDetailScreen(card: TarotCard, onDismiss: () -> Unit) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Parchment,
        titleContentColor = ClassicBurgundy,
        textContentColor = DarkText,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar", color = ClassicGold, fontFamily = FontFamily.Serif)
            }
        },
        title = {
            Text(
                card.name,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Serif,
                color = ClassicBurgundy
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val resId = context.resources
                    .getIdentifier(card.image, "drawable", context.packageName)
                if (resId != 0) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, CardBorderGold, RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(thickness = 1.dp, color = DividerGold)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Derecha: ${card.upright}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Invertida: ${card.reversed}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))
                if (card.element != null) {
                    Text(
                        "Elemento: ${card.element}",
                        style = MaterialTheme.typography.labelLarge,
                        color = ClassicGold
                    )
                }
                Text(
                    "Palabras clave: ${card.keywords.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}