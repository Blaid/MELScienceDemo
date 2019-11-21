package com.melscience.di

import com.melscience.model.currency.data.datasource.CurrencyDataSourceFactory
import com.melscience.model.currency.data.entity.mapper.CurrencyDataMapper
import com.melscience.model.currency.data.entity.mapper.CurrencyDataMapperImpl
import com.melscience.model.currency.data.repository.CurrencyDataRepository
import com.melscience.model.currency.domain.calculate.CalculateBanknotes
import com.melscience.model.currency.domain.calculate.CalculateBanknotesFactory
import com.melscience.model.currency.domain.calculate.DescendingCalculateBanknotes
import com.melscience.model.currency.domain.calculate.AscendingCalculateBanknotes
import com.melscience.model.currency.domain.interactor.*
import com.melscience.model.currency.domain.repository.CurrencyRepository
import com.melscience.common.koin.scopeModule
import org.koin.core.qualifier.named

const val ASCENDING_CALCULATE_BANKNOTES_NAMED = "ascending_calculate_banknotes_named"
const val DESCENDING_CALCULATE_BANKNOTES_NAMED = "descending_calculate_banknotes_named"

inline fun <reified T> currencyModelModule() = scopeModule<T> {
  scoped { CurrencyInteractor(get(), get(), get()) }
  scoped {
    CalculateBanknotesFactory(
      descendingCalculateBanknotes = get(named(DESCENDING_CALCULATE_BANKNOTES_NAMED)),
      ascendingCalculateBanknotes = get(named(ASCENDING_CALCULATE_BANKNOTES_NAMED))
    )
  }

  scoped<CalculateBanknotes>(named(ASCENDING_CALCULATE_BANKNOTES_NAMED)) { AscendingCalculateBanknotes() }
  scoped<CalculateBanknotes>(named(DESCENDING_CALCULATE_BANKNOTES_NAMED)) { DescendingCalculateBanknotes() }
  scoped<CurrencyRepository> { CurrencyDataRepository(get(), get()) }
  scoped<CurrencyDataMapper> { CurrencyDataMapperImpl() }
  scoped { CurrencyDataSourceFactory(get(), get()) }
}