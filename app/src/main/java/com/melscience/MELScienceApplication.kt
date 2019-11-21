package com.melscience

import android.app.Application
import com.melscience.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MELScienceApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    initKoin()
  }

  private fun initKoin() {
    startKoin {
      androidContext(this@MELScienceApplication)
      loadKoinModules(appModule)
    }
  }
}