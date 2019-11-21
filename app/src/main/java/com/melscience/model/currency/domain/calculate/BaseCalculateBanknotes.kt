package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.exception.CalculateBanknotesException
import com.melscience.model.currency.domain.CurrencyType

abstract class BaseCalculateBanknotes :
  CalculateBanknotes {
  abstract val maxSmallBanknotes: Int

  override fun calculate(
    amount: Int,
    currencyType: CurrencyType,
    isEndlessBanknotes: Boolean,
    banknotes: Array<BanknoteDomain>
  ): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    var remainingAmount = amount

    banknotes.forEach {
      val banknoteNominal = it.nominal
      val banknoteAmount = it.amount

      if (remainingAmount >= banknoteNominal && it.checkCurrencyType(currencyType)) {
        val countEndlessBanknotes = remainingAmount / banknoteNominal

        val countBanknotes = if (isEndlessBanknotes || countEndlessBanknotes <= banknoteAmount) {
          countEndlessBanknotes
        } else {
          banknoteAmount
        }

        result.add(Pair(banknoteNominal, countBanknotes))
        remainingAmount -= countBanknotes * banknoteNominal
      }
    }

    if (remainingAmount != 0) {
      throw CalculateBanknotesException()
    }

    return result
  }

  private fun BanknoteDomain.checkCurrencyType(
    currencyType: CurrencyType
  ) = when (currencyType) {
    CurrencyType.SMALL -> this.nominal <= maxSmallBanknotes
    CurrencyType.BIG -> this.nominal > maxSmallBanknotes
    else -> true
  }
}