package com.melscience.di

import com.melscience.core.api.cache.FileManager
import com.melscience.core.api.cache.impl.AndroidAssetsFileManager
import com.melscience.core.api.converter.Converter
import com.melscience.core.api.converter.impl.GSonConverterImpl
import com.melscience.core.api.error.ErrorHandler
import com.melscience.core.api.resource.ResourcesProvider
import com.melscience.core.api.resource.impl.AndroidResourcesProvider
import com.melscience.core.api.schedulers.SchedulersProvider
import com.melscience.core.api.schedulers.impl.AppSchedulers
import com.melscience.error.MELScienceErrorHandler
import org.koin.dsl.module

val appModule = module {
  single<SchedulersProvider> { AppSchedulers() }
  single<ResourcesProvider> { AndroidResourcesProvider(get()) }
  single<ErrorHandler> { MELScienceErrorHandler(get()) }
  single<FileManager> { AndroidAssetsFileManager(get()) }
  single<Converter> { GSonConverterImpl() }
}