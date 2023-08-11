package com.dragic.gamehunter.utils

import android.icu.number.Notation
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.util.Currency
import android.icu.util.MeasureUnit
import android.icu.util.ULocale

fun priceToUsd(price: Double) = NumberFormatter
    .withLocale(ULocale.US)
    .notation(Notation.compactShort())
    .unit(Currency.getInstance("USD"))
    .precision(Precision.maxSignificantDigits(4))
    .format(price)
    .toString()

fun savingsToPercentage(savings: Double) = NumberFormatter
    .withLocale(ULocale.US)
    .unit(MeasureUnit.PERCENT)
    .precision(Precision.fixedFraction(2))
    .format(savings)
    .toString()
