package com.melscience.presentation.main.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
  fun setSelectedCurrency(currencyName: String)
  fun showOrHideBanknotesDialog(show: Boolean)
  fun setResultCurrencyTitle(currency: String)
  fun setBanknotesResult(banknotes: List<Pair<Int, Int>>)
  @StateStrategyType(SkipStrategy::class) fun showMessagePopup(msg: String)
  fun closeKeyboard()
}

interface MainAction {
  fun currencyAction()
  fun calculateAction()
  fun selectedCurrencyAction(currencyName: String)
  fun checkedEndlessBanknotes(checked: Boolean)
  fun amountTextChange(amount: String)
  fun closeBanknotesBottomSheetAction()
  fun currencyTypeItemSelectedAction(position: Int)
}