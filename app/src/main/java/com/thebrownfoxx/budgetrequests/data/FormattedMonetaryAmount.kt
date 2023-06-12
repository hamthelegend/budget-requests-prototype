package com.thebrownfoxx.budgetrequests.data

import java.text.NumberFormat

val Double.formattedMonetaryAmount: String
    get() = NumberFormat.getCurrencyInstance().format(this)