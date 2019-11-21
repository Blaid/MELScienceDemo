package com.melscience.core.api.navigator

import android.content.Intent

interface NavigatorHandler {

  //Base

  fun open(obj: Any?, tag: String = "")
  fun openForResult(obj: Any?, requestCode: Int, tag: String = "")

  /**
   * The call will be forwarded to its top.
   * If that controller doesn't handle it, then it will be popped.
   *
   * @return Whether or not a back action was handled by the Router
   */
  fun back()

  fun backWithDeliveryResult(resultCode: Int, data: Intent?)
}