package com.melscience.core.controller

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.arellomobile.mvp.MvpDelegate
import com.arellomobile.mvp.MvpView

abstract class BaseMvpController @JvmOverloads constructor(
  args: Bundle? = null
) : BaseController(args), MvpView {
  private val mvpDelegate: MvpDelegate<out BaseMvpController> by lazy {
    MvpDelegate<BaseMvpController>(this)
  }

  @CallSuper
  override fun onViewBound(view: View?) {
    mvpDelegate.onCreate(args)
  }

  @CallSuper
  override fun onAttach(view: View) {
    super.onAttach(view)

    mvpDelegate.onAttach()
  }

  @CallSuper
  override fun onDetach(view: View) {
    super.onDetach(view)

    mvpDelegate.onDetach()
  }

  @CallSuper
  override fun onDestroy() {
    super.onDestroy()

    mvpDelegate.onDestroy()
  }

  @CallSuper
  override fun onDestroyView(view: View) {
    super.onDestroyView(view)

    mvpDelegate.onDestroyView()
  }

  @CallSuper
  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)

    mvpDelegate.onSaveInstanceState(outState)
  }
}