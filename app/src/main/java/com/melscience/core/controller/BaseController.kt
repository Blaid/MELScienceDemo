package com.melscience.core.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.bluelinelabs.conductor.Controller
import com.melscience.core.api.navigator.BackListener
import com.melscience.core.api.navigator.ResultProvider
import kotlinx.android.extensions.LayoutContainer

abstract class BaseController @JvmOverloads constructor(
  args: Bundle? = null
) : Controller(args), LayoutContainer, BackListener, ResultProvider {
  override val containerView: View?
    get() = view

  abstract val layoutId: Int

  override var requestCode: Int? = null

  init {
    addLifecycleListener(
      object : LifecycleListener() {

        override fun postCreateView(controller: Controller, view: View) {
          onViewBound(view)
        }
      }
    )

    this.initializeInjector()
  }

  @CallSuper
  override fun onAttach(view: View) {
    retainViewMode = RetainViewMode.RETAIN_DETACH
  }

  override fun onCreateView(
    inflater: LayoutInflater, root: ViewGroup
  ): View = inflater.inflate(layoutId, root, false)

  override fun back() = false
  protected open fun onViewBound(view: View?) { }
  protected open fun initializeInjector() { }

  override fun setResult(resultCode: Int, data: Intent?) {
    requestCode?.also { targetController?.onActivityResult(it, resultCode, data) }
  }
}