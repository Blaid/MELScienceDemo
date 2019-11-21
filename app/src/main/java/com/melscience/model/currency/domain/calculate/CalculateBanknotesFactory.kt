package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.CurrencyType
import kotlin.random.Random

class CalculateBanknotesFactory(
  private val descendingCalculateBanknotes: CalculateBanknotes,
  private val ascendingCalculateBanknotes: CalculateBanknotes
) {

  fun getCalculateBanknotes(
    currencyType: CurrencyType
  ) = if (currencyType != CurrencyType.DIFFERENT || Random.nextBoolean()) {
    descendingCalculateBanknotes
  } else {
    ascendingCalculateBanknotes
  }
}