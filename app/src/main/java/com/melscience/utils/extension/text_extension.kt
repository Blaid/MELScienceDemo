package com.melscience.utils.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.addTextWatcher(
  beforeTextChangedFun: ((text: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
  onTextChangedFun: ((text: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null,
  afterTextChangedFun: ((text: Editable?) -> Unit)? = null
) = object : TextWatcher {
  override fun afterTextChanged(s: Editable?) {
    afterTextChangedFun?.invoke(s)
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    beforeTextChangedFun?.invoke(s, start, count, after)
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    onTextChangedFun?.invoke(s, start, before, count)
  }
}.apply { addTextChangedListener(this) }