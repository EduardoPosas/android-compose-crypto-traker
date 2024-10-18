package com.plcoding.cryptotracker.crypto.presentation.coinList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val contentColor =
        if (change.value < 0.0) MaterialTheme.colorScheme.onErrorContainer else Color.Green
    val backgroundColor =
        if (change.value < 0.0) MaterialTheme.colorScheme.errorContainer else greenBackground

    Row(
        modifier = modifier
            .background(
                backgroundColor,
                shape = RoundedCornerShape(100F)
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = if (change.value < 0.0) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "${change.formatted} %",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePrev() {
    CryptoTrackerTheme {
        PriceChange(
            change = DisplayableNumber(
                value = 0.81,
                formatted = "0.81"
            )
        )
    }
}