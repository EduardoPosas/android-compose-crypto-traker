package com.plcoding.cryptotracker.crypto.presentation.coinDetail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.coin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    title: String,
    formattedPrice: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        )
    ) {
        AnimatedContent(
            targetState = icon,
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
            label = "IconAnimation"
        ) { icon ->
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(75.dp),
                tint = contentColor,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = formattedPrice,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = "FormatedPriceAnimation"
        ) { formattedPrice ->
            Text(
                text = formattedPrice,
                color = contentColor,
                fontSize = 18.sp,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@PreviewLightDark
@Composable
private fun InfoCardPrev() {
    CryptoTrackerTheme {
        InfoCard(
            title = "Market Cap",
            formattedPrice = "$ ${coin.priceUsd.formatted}",
            icon = ImageVector.vectorResource(id = coin.iconRes),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}