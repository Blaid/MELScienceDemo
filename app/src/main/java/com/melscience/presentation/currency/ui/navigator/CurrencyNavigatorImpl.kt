package com.melscience.presentation.currency.ui.navigator

import android.app.Activity
import android.content.Intent
import com.melscience.core.api.navigator.NavigatorHandler
import com.melscience.presentation.CURRENCY_NAME_KEY

class CurrencyNavigatorImpl(private val handler: NavigatorHandler) : CurrencyNavigator {

  override fun back(currencyName: String) {
    if (currencyName.isEmpty()) {
      handler.back()
    } else {
      handler.backWithDeliveryResult(
        resultCode = Activity.RESULT_OK,
        data = Intent().apply { putExtra(CURRENCY_NAME_KEY, currencyName) }
      )
    }
  }
}