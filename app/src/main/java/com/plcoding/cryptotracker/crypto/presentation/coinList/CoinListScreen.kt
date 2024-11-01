package com.plcoding.cryptotracker.crypto.presentation.coinList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.coin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    state: CoinListState,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onShowDetailScreen: (CoinListAction) -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    val refreshingState = rememberPullToRefreshState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = refreshingState
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items = state.coins, key = { it.id }) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClickItem = { onShowDetailScreen(CoinListAction.CoinItemClicked(coinUi)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}

@PreviewLightDark
@Composable
private fun CoinListScreenPrev() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map {
                    coin.copy(id = it.toString())
                }
            ),
            isRefreshing = false
        )
    }
}