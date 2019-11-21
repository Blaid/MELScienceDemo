package com.melscience.model.currency.data.datasource

import com.google.gson.reflect.TypeToken
import com.melscience.core.api.cache.FileManager
import com.melscience.core.api.converter.Converter
import com.melscience.model.currency.data.entity.CurrencyData
import io.reactivex.Single

private const val CURRENCIES_JSON_PATH = "currencies.json"

class AssetsCurrencyDataSource(
  private val fileManager: FileManager,
  private val converter: Converter
) : CurrencyDataSource {

  override fun getCurrencies() = Single.fromCallable {
    converter.from<List<CurrencyData>>(
      json = fileManager.string(CURRENCIES_JSON_PATH),
      type = object : TypeToken<List<CurrencyData>>() {}.type
    )
  }
}