package com.melscience.core.api.navigator

import android.content.Intent

interface ResultProvider {
  var requestCode: Int?

  fun setResult(resultCode: Int, data: Intent?)
}