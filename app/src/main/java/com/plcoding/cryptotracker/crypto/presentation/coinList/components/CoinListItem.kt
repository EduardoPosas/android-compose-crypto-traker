package com.plcoding.cryptotracker.crypto.presentation.coinList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClickItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Row(
        modifier = modifier
            .clickable(onClick = onClickItem)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )
        Column(
            modifier = Modifier.weight(1F)
        ) {
            Text(
                text = coinUi.symbol,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                text = coinUi.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "$ ${coinUi.priceUsd.formatted}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            PriceChange(change = coinUi.changePercent24hr)
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPrev() {
    CryptoTrackerTheme {
        CoinListItem(
            coinUi = coin,
            onClickItem = { /*TODO*/ },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

val coin = Coin(
    id = "bitcoin",
    name = "Bitcoin",
    rank = 1,
    symbol = "BTC",
    marketCapUsd = 119150835874.46,
    priceUsd = 6929.82,
    changePercent24hr = -0.81
).toCoinUi()

