package com.melscience.core.api.schedulers.impl

import com.melscience.core.api.schedulers.SchedulersProvider
import io.reactivex.schedulers.Schedulers

/**
 * Created by Igor Vasilev on 20.11.17.
 */
class TestSchedulers : SchedulersProvider {
  override fun single() = Schedulers.trampoline()
  override fun ui() = Schedulers.trampoline()
  override fun computation() = Schedulers.trampoline()
  override fun trampoline() = Schedulers.trampoline()
  override fun newThread() = Schedulers.trampoline()
  override fun io() = Schedulers.trampoline()
}