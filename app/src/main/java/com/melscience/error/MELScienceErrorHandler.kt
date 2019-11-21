package com.melscience.error

import com.melscience.R
import com.melscience.core.api.error.ErrorHandler
import com.melscience.core.api.resource.ResourcesProvider
import com.melscience.model.currency.domain.exception.CalculateBanknotesException

class MELScienceErrorHandler(private val resourcesProvider: ResourcesProvider) : ErrorHandler {

  override fun getErrorMessage(throwable: Throwable) = when (throwable) {
    is CalculateBanknotesException -> resourcesProvider.getString(R.string.errorCalculateBanknotesText)
    else -> resourcesProvider.getString(R.string.unknownErrorText)
  }
}