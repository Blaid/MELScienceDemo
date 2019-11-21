package com.melscience.core.android.popup

import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar

class SnackBarPopup(private val context: Context) : Popup {
  private val PopupDuration.snackbarDuration
    get() = when (this) {
      PopupDuration.INDEFINITE -> Snackbar.LENGTH_INDEFINITE
      PopupDuration.SHORT -> Snackbar.LENGTH_SHORT
      else -> Snackbar.LENGTH_LONG
    }

  override fun show(msg: String, duration: PopupDuration) {
    val anchor = (context as Activity).window
      .decorView

    Snackbar.make(anchor, msg, duration.snackbarDuration)
      .show()
  }
}