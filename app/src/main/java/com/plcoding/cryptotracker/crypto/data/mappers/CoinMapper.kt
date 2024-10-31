package com.plcoding.cryptotracker.crypto.data.mappers

import com.plcoding.cryptotracker.crypto.data.dto.CoinDto
import com.plcoding.cryptotracker.crypto.data.dto.CoinPriceDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24hr = changePercent24hr
)

fun CoinPriceDto.toCoinPrice() = CoinPrice(
    priceUsd = priceUsd,
    dateTime = Instant
        .ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
)