package com.melscience.core.controller

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {
  protected open val subscriptions = CompositeDisposable()

  override fun destroyView(view: V) {
    super.destroyView(view)

    subscriptions.clear()
  }

  fun viewsState(viewStateFun: V.() -> Unit) {
    viewStateFun.invoke(viewState)
  }

  infix fun CompositeDisposable.add(disposable: Disposable) {
    add(disposable)
  }
}