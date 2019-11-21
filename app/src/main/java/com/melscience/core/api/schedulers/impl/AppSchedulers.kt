package com.melscience.core.api.schedulers.impl

import com.melscience.core.api.schedulers.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Igor Vasilev on 20.11.17.
 */
class AppSchedulers : SchedulersProvider {
  override fun ui() = AndroidSchedulers.mainThread()
  override fun computation() = Schedulers.computation()
  override fun trampoline() = Schedulers.trampoline()
  override fun newThread() = Schedulers.newThread()
  override fun io() = Schedulers.io()
  override fun single() = Schedulers.single()
}