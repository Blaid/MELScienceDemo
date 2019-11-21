package com.melscience.core.android.popup

interface Popup {
  fun show(msg: String, duration: PopupDuration = PopupDuration.SHORT)
}

enum class PopupDuration {
  INDEFINITE,
  SHORT,
  LONG
}