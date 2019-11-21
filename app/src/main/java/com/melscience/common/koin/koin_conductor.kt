package com.melscience.common.koin

import com.bluelinelabs.conductor.Controller
import org.koin.core.context.GlobalContext
import org.koin.core.module.Module

val Controller.koin
  get() = GlobalContext.get().koin

fun Controller.loadModules(vararg modules: Module) {
  addLifecycleListener(ControllerModulesObserver(modules))
}