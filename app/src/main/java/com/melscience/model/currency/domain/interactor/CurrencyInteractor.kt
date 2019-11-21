package com.melscience.model.currency.domain.interactor

import com.melscience.core.api.schedulers.SchedulersProvider
import com.melscience.model.currency.domain.calculate.CalculateBanknotesFactory
import com.melscience.model.currency.domain.repository.CurrencyRepository
import com.melscience.model.currency.domain.CurrencyType

open class CurrencyInteractor(
  private val currencyRepository: CurrencyRepository,
  private val calculateBanknotesFactory: CalculateBanknotesFactory,
  private val schedulersProvider: SchedulersProvider
) {

  fun getCurrencies() = currencyRepository.getCurrencies()
    .subscribeOn(schedulersProvider.io())
    .observeOn(schedulersProvider.ui())

  fun calculate(
    amount: Int,
    currency: String,
    currencyType: CurrencyType,
    isEndlessBanknotes: Boolean
  ) = getCurrencies()
    .map { currencies ->
      currencies.first { it.name == currency }
        .banknotes
    }
    .map {
      calculateBanknotesFactory.getCalculateBanknotes(currencyType)
        .calculate(amount, currencyType, isEndlessBanknotes, it)
    }
    .subscribeOn(schedulersProvider.io())
    .observeOn(schedulersProvider.ui())
}