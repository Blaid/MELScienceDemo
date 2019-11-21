package com.melscience.presentation.main.di

import com.melscience.presentation.main.ui.MainController
import com.melscience.presentation.main.ui.MainPresenter
import com.melscience.presentation.main.ui.navigator.MainNavigator
import com.melscience.presentation.main.ui.navigator.MainNavigatorImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
  scope(named<MainController>()) {
    scoped { MainPresenter(get(), get(), get()) }
    scoped<MainNavigator> { MainNavigatorImpl(get()) }
  }
}