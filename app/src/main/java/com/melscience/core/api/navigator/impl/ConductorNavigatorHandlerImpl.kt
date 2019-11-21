package com.melscience.core.api.navigator.impl

import android.content.Intent
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.melscience.core.api.navigator.NavigatorHandler
import com.melscience.core.api.navigator.ResultProvider

class ConductorNavigatorHandlerImpl(private val router: Router) : NavigatorHandler {
  private var pushAnimationHandler: ControllerChangeHandler = FadeChangeHandler()
  private var popAnimationHandler: ControllerChangeHandler? = FadeChangeHandler()


  // region NavigatorHandler

  override fun open(obj: Any?, tag: String) {
    if (obj is Controller) {
      pushController(obj, tag)
    }
  }

  override fun openForResult(obj: Any?, requestCode: Int, tag: String) {
    if (obj is Controller) {
      router.backstack
        .lastOrNull()
        ?.controller()
        ?.apply {
          (obj as? ResultProvider)?.requestCode = requestCode
          obj.targetController = this
        }

      pushController(obj, tag)
    }
  }

  override fun back() {
    router.handleBack()
  }

  override fun backWithDeliveryResult(resultCode: Int, data: Intent?) {
    deliveryResult(resultCode, data)
    back()
  }

  // endregion


  // region Private

  private fun deliveryResult(resultCode: Int, data: Intent?) {
    val backStack = router.backstack
    if (backStack.isNotEmpty()) {
      val current = backStack.lastOrNull()
        ?.controller()

      val resultProvider = current as? ResultProvider

      resultProvider?.setResult(resultCode, data)
    }
  }

  private fun pushController(controller: Controller, tag: String) {
    router.pushController(createRouterTransaction(controller, tag))
  }

  private fun createRouterTransaction(
    controller: Controller,
    tag: String? = null
  ) = RouterTransaction.with(controller)
    .pushChangeHandler(pushAnimationHandler)
    .popChangeHandler(popAnimationHandler)
    .tag(tag)

  // endregion
}