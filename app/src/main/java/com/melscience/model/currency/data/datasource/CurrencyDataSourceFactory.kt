package com.melscience.model.currency.data.datasource

import com.melscience.core.api.cache.FileManager
import com.melscience.core.api.converter.Converter

class CurrencyDataSourceFactory(
  private val fileManager: FileManager,
  private val converter: Converter
) {
  fun createAssets(): CurrencyDataSource = AssetsCurrencyDataSource(fileManager, converter)
}