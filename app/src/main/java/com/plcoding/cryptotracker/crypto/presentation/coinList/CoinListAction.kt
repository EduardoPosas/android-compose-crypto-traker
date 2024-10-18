package com.plcoding.cryptotracker.crypto.presentation.coinList

import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class CoinItemClicked(val coinUi: CoinUi) : CoinListAction
}