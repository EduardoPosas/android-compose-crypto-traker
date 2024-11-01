package com.plcoding.cryptotracker.crypto.presentation.coinList

import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
    val isRefreshing: Boolean = false
)
