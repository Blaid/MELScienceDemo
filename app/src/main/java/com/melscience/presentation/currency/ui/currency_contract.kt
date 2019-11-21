package com.melscience.presentation.currency.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.melscience.presentation.currency.ui.model.CurrencyModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrencyView : MvpView {
  fun setCurrencies(currencies: List<CurrencyModel>)
}

interface CurrencyAction {
  fun backAction()
  fun currencyAction(currency: CurrencyModel)
}