package com.melscience.presentation.main.ui.navigator

import androidx.core.os.bundleOf
import com.melscience.core.api.navigator.NavigatorHandler
import com.melscience.presentation.CURRENCY_NAME_KEY
import com.melscience.presentation.CURRENCY_REQUEST_CODE
import com.melscience.presentation.currency.ui.CurrencyController

class MainNavigatorImpl(private val handler: NavigatorHandler) : MainNavigator {

  override fun openCurrency(selectedCurrency: String) {
    handler.openForResult(
      CurrencyController(
        args = bundleOf(CURRENCY_NAME_KEY to selectedCurrency)
      ),
      CURRENCY_REQUEST_CODE
    )
  }
}