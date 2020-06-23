package com.iloadti.testnexaas.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatForBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun BigDecimal.formatForEUACurrency(): String {
    val euaFormat = DecimalFormat("'$'0.00")
    return euaFormat.format(this)
}