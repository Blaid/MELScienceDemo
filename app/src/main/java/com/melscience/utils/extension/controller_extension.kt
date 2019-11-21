package com.melscience.utils.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.bluelinelabs.conductor.Controller

fun Controller.hideKeyboard() {
  val view = activity?.currentFocus
  try {
    if (view != null && view.windowToken != null) {
      val inputMethodService = activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
      val inputMethod = inputMethodService as? InputMethodManager
      inputMethod?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  } catch (t: Throwable) {
    t.printStackTrace()
  }
}