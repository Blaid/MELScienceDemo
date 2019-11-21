package com.melscience.model.currency.data.datasource

import com.melscience.model.currency.data.entity.CurrencyData
import io.reactivex.Single

interface CurrencyDataSource {
  fun getCurrencies(): Single<List<CurrencyData>>
}