package com.melscience.presentation.main.ui

import com.arellomobile.mvp.InjectViewState
import com.melscience.core.api.error.ErrorHandler
import com.melscience.core.controller.BasePresenter
import com.melscience.model.currency.domain.interactor.CurrencyInteractor
import com.melscience.model.currency.domain.CurrencyType
import com.melscience.presentation.main.ui.navigator.MainNavigator

private const val DEFAULT_ENDLESS_BANKNOTES = true
private val DEFAULT_CURRENCY_TYPE = CurrencyType.DIFFERENT

@InjectViewState
class MainPresenter(
  private val currencyInteractor: CurrencyInteractor,
  private val errorHandler: ErrorHandler,
  private val navigator: MainNavigator
) : BasePresenter<MainView>(), MainAction {
  private var currencyName = ""
  private var currencyType = DEFAULT_CURRENCY_TYPE
  private var isEndlessBanknotes = DEFAULT_ENDLESS_BANKNOTES
  private var amount = 0

  override fun onFirstViewAttach() {
    loadCurrencies()
  }


  // region MainAction

  override fun currencyAction() {
    navigator.openCurrency(currencyName)
  }

  override fun calculateAction() {
    viewState?.closeKeyboard()

    subscriptions add currencyInteractor.calculate(
      amount,
      currencyName,
      currencyType,
      isEndlessBanknotes
    ).subscribe(
      { result: List<Pair<Int, Int>> ->
        viewsState {
          showOrHideBanknotesDialog(true)
          setResultCurrencyTitle(currencyName)
          setBanknotesResult(result)
        }
      },
      {
        val errorMsg = errorHandler.getErrorMessage(it)
        viewState?.showMessagePopup(errorMsg)
      }
    )
  }

  override fun selectedCurrencyAction(currencyName: String) {
    viewState?.setSelectedCurrency(currencyName)
    this.currencyName = currencyName
  }

  override fun checkedEndlessBanknotes(checked: Boolean) {
    isEndlessBanknotes = checked
  }

  override fun amountTextChange(amount: String) {
    this.amount = if (amount.isEmpty()) 0 else amount.toInt()
  }

  override fun closeBanknotesBottomSheetAction() {
    viewState?.showOrHideBanknotesDialog(false)
  }

  override fun currencyTypeItemSelectedAction(position: Int) {
    currencyType = CurrencyType.find(position)
  }

  // endregion


  private fun loadCurrencies() {
    subscriptions add currencyInteractor.getCurrencies()
      .map { it.first() }
      .subscribe { currency ->
        currencyName = currency.name
        viewState?.setSelectedCurrency(currencyName)
      }
  }
}