package com.plcoding.cryptotracker.crypto.data.mappers

import com.plcoding.cryptotracker.crypto.data.dto.CoinDto
import com.plcoding.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24hr = changePercent24hr
)