package com.dragic.gamehunter.utils

import android.icu.number.Notation
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.util.Currency
import android.icu.util.MeasureUnit
import android.icu.util.ULocale
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.R)
fun priceToUsd(price: Double) = NumberFormatter
    .withLocale(ULocale.US)
    .notation(Notation.compactShort())
    .unit(Currency.getInstance("USD"))
    .precision(Precision.maxSignificantDigits(4))
    .format(price)
    .toString()

@RequiresApi(Build.VERSION_CODES.R)
fun savingsToPercentage(savings: Double) = NumberFormatter
    .withLocale(ULocale.US)
    .unit(MeasureUnit.PERCENT)
    .precision(Precision.fixedFraction(2))
    .format(savings)
    .toString()
