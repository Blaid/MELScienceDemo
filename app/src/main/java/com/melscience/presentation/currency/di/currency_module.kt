package com.melscience.presentation.currency.di

import com.melscience.presentation.currency.mapper.CurrencyModelMapper
import com.melscience.presentation.currency.mapper.CurrencyModelMapperImpl
import com.melscience.presentation.currency.ui.CurrencyController
import com.melscience.presentation.currency.ui.CurrencyPresenter
import com.melscience.presentation.currency.ui.navigator.CurrencyNavigator
import com.melscience.presentation.currency.ui.navigator.CurrencyNavigatorImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val currencyModule = module {
  scope(named<CurrencyController>()) {
    scoped { (params: CurrencyPresenter.Params) -> CurrencyPresenter(params, get(), get(), get()) }
    scoped<CurrencyNavigator> { CurrencyNavigatorImpl(get()) }
    scoped<CurrencyModelMapper> { CurrencyModelMapperImpl() }
  }
}