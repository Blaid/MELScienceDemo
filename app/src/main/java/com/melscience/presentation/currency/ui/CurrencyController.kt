package com.melscience.presentation.currency.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.melscience.R
import com.melscience.common.koin.currentScope
import com.melscience.common.koin.loadModules
import com.melscience.core.controller.BaseMvpController
import com.melscience.di.currencyModelModule
import com.melscience.presentation.CURRENCY_NAME_KEY
import com.melscience.presentation.currency.di.currencyModule
import com.melscience.presentation.currency.ui.model.CurrencyModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.currency_controller.*
import org.koin.core.parameter.parametersOf

class CurrencyController @JvmOverloads constructor(
  args: Bundle? = null
) : BaseMvpController(args), CurrencyView {
  override val layoutId = R.layout.currency_controller

  @InjectPresenter lateinit var presenter: CurrencyPresenter

  @ProvidePresenter
  fun providePresenter() = currentScope?.get<CurrencyPresenter> { parametersOf(paramsOf()) }

  private lateinit var currencyAdapter: CurrencyAdapter

  override fun onViewBound(view: View?) {
    super.onViewBound(view)

    initToolbar()
    initRecycler()
  }


  // region CurrencyView

  override fun setCurrencies(currencies: List<CurrencyModel>) {
    with(currencyAdapter) {
      addCurrencies(currencies)
      notifyDataSetChanged()
    }
  }

  // endregion


  override fun initializeInjector() {
    loadModules(currencyModule, currencyModelModule<CurrencyController>())
  }

  override fun onDestroyView(view: View) {
    super.onDestroyView(view)

    clearFindViewByIdCache()
  }

  private fun initToolbar() {
    toolbar?.setNavigationOnClickListener { presenter.backAction() }
  }

  private fun initRecycler() {
    currencyAdapter = CurrencyAdapter { presenter.currencyAction(it) }

    with(rvCurrencies) {
      setHasFixedSize(false)
      layoutManager = LinearLayoutManager(activity)
      adapter = currencyAdapter
    }
  }

  private fun paramsOf(): CurrencyPresenter.Params {
    val currencyName = args.getString(CURRENCY_NAME_KEY)
      .orEmpty()

    return CurrencyPresenter.Params(currencyName)
  }
}