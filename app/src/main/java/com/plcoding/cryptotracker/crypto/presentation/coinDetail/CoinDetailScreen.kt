package com.plcoding.cryptotracker.crypto.presentation.coinDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.coinDetail.components.InfoCard
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.coin
import com.plcoding.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val coin = state.selectedCoin

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (coin != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = contentColor
            )
            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoCard(
                    title = stringResource(id = R.string.market_cap),
                    formattedPrice = coin.marketCapUsd.formatted,
                    icon = ImageVector.vectorResource(id = R.drawable.stock)
                )
                InfoCard(
                    title = stringResource(id = R.string.price),
                    formattedPrice = coin.priceUsd.formatted,
                    icon = ImageVector.vectorResource(R.drawable.dollar)
                )
                val absoluteChangeFormatted =
                    (coin.priceUsd.value * (coin.changePercent24hr.value / 100)).toDisplayableNumber()
                val isPositive = absoluteChangeFormatted.value > 0.0
                val cardContentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else MaterialTheme.colorScheme.error

                InfoCard(
                    title = stringResource(id = R.string.change_last_24h),
                    formattedPrice = absoluteChangeFormatted.formatted,
                    icon = if (isPositive) ImageVector.vectorResource(id = R.drawable.trending) else ImageVector.vectorResource(
                        id = R.drawable.trending_down
                    ),
                    contentColor = cardContentColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPrev() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinListState(
                selectedCoin = coin
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}