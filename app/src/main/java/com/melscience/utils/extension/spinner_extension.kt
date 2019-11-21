package com.melscience.utils.extension

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun Spinner.itemSelectedListener(selectedItemFun: (Int) -> Unit) {
  onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) { }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      selectedItemFun.invoke(position)
    }
  }
}