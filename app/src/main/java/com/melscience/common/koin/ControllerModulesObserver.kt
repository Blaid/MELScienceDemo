package com.melscience.common.koin

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import org.koin.core.module.Module

class ControllerModulesObserver(
  private val modules: Array<out Module>
) : Controller.LifecycleListener() {

  init {
    modules.forEach { it.load() }
  }

  override fun onChangeStart(
    controller: Controller,
    changeHandler: ControllerChangeHandler,
    changeType: ControllerChangeType
  ) {
    when (changeType) {
      ControllerChangeType.POP_EXIT -> modules.forEach { it.unLoad() }
      else -> { }
    }
  }
}