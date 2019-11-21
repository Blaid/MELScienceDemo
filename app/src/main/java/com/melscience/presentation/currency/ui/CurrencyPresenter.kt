package com.melscience.presentation.currency.ui

import com.arellomobile.mvp.InjectViewState
import com.melscience.core.controller.BasePresenter
import com.melscience.model.currency.domain.interactor.CurrencyInteractor
import com.melscience.presentation.currency.mapper.CurrencyModelMapper
import com.melscience.presentation.currency.ui.model.CurrencyModel
import com.melscience.presentation.currency.ui.navigator.CurrencyNavigator

@InjectViewState
class CurrencyPresenter(
  private val params: Params,
  private val currencyInteractor: CurrencyInteractor,
  private val currencyModelMapper: CurrencyModelMapper,
  private val navigator: CurrencyNavigator
) : BasePresenter<CurrencyView>(), CurrencyAction {

  override fun onFirstViewAttach() {
    loadCurrencies()
  }


  // region CurrencyAction

  override fun backAction() {
    navigator.back()
  }

  override fun currencyAction(currency: CurrencyModel) {
    navigator.back(currency.name)
  }

  // endregion

  private fun loadCurrencies() {
    subscriptions add currencyInteractor.getCurrencies()
      .map { currencyModelMapper.map(it) }
      .map { currencies ->
        currencies.apply {
          firstOrNull { it.name == params.selectedCurrency }?.checked = true
        }
      }
      .subscribe { currencies: List<CurrencyModel> -> viewState?.setCurrencies(currencies) }
  }

  class Params(val selectedCurrency: String)
}