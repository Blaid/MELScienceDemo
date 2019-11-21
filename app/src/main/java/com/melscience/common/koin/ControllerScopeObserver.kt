package com.melscience.common.koin

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import org.koin.core.scope.Scope

class ControllerScopeObserver(private val scope: Scope) : Controller.LifecycleListener() {

  override fun onChangeStart(
    controller: Controller,
    changeHandler: ControllerChangeHandler,
    changeType: ControllerChangeType
  ) {
    when (changeType) {
      ControllerChangeType.POP_EXIT -> scope.close()
      else -> { }
    }
  }
}