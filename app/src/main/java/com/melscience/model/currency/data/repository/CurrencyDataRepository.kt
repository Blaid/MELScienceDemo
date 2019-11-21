package com.melscience.model.currency.data.repository

import com.melscience.model.currency.data.datasource.CurrencyDataSourceFactory
import com.melscience.model.currency.data.entity.mapper.CurrencyDataMapper
import com.melscience.model.currency.domain.repository.CurrencyRepository

class CurrencyDataRepository(
  factory: CurrencyDataSourceFactory,
  private val currencyDataMapper: CurrencyDataMapper
) : CurrencyRepository {
  private val assets = factory.createAssets()

  override fun getCurrencies() = assets.getCurrencies()
    .map { currencyDataMapper.map(it) }
}