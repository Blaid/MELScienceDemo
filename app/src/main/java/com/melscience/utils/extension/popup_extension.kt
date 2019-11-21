package com.melscience.utils.extension

import android.content.Context
import com.bluelinelabs.conductor.Controller
import com.melscience.core.android.popup.Popup
import com.melscience.core.android.popup.SnackBarPopup

fun Controller.showPopup(msg: String) {
  activity?.getPopup()
    ?.show(msg)
}

private fun Context.getPopup(): Popup = SnackBarPopup(this)