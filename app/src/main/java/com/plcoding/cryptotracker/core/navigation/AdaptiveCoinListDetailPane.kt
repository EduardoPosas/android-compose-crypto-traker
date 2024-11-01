package com.plcoding.cryptotracker.core.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.core.presentation.util.ObserveAsEvents
import com.plcoding.cryptotracker.crypto.presentation.coinDetail.CoinDetailScreen
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListAction
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListEvent
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    coinListViewModel: CoinListViewModel = koinViewModel()
) {
    val state by coinListViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = coinListViewModel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            CoinListScreen(
                state = state,
                isRefreshing = state.isRefreshing,
                onShowDetailScreen = { action ->
                    coinListViewModel.onAction(action)
                    when (action) {
                        is CoinListAction.CoinItemClicked -> {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail
                            )
                        }

                        CoinListAction.CoinListRefreshing -> {}
                    }
                },
                onRefresh = {
                    coinListViewModel.onAction(CoinListAction.CoinListRefreshing)
                }
            )
        },
        detailPane = {
            if (state.selectedCoin == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Please, Select a coin to get the detail")
                }
            }
            CoinDetailScreen(state = state)
        },
        modifier = modifier
    )
}