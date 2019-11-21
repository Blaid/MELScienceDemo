package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.CurrencyType

private const val MAX_SMALL_BANKNOTES = 100

class AscendingCalculateBanknotes : BaseCalculateBanknotes() {
  override val maxSmallBanknotes = MAX_SMALL_BANKNOTES

  override fun calculate(
    amount: Int,
    currencyType: CurrencyType,
    isEndlessBanknotes: Boolean,
    banknotes: Array<BanknoteDomain>
  ) = super.calculate(
    amount,
    currencyType,
    isEndlessBanknotes,
    banknotes.apply { sortBy { it.nominal } }
  )
}