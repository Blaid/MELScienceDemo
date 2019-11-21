package com.melscience.common.koin

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.ScopeSet
import org.koin.dsl.module

fun Module.load() {
  loadKoinModules(this)
}

fun Module.unLoad() {
  unloadKoinModules(this)
}

inline fun <reified T> scopeModule(noinline scopeSet: ScopeSet.() -> Unit) = module {
  scope(named<T>(), scopeSet)
}