package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.CurrencyType

interface CalculateBanknotes {
  fun calculate(
    amount: Int,
    currencyType: CurrencyType,
    isEndlessBanknotes: Boolean,
    banknotes: Array<BanknoteDomain>
  ): List<Pair<Int, Int>>
}