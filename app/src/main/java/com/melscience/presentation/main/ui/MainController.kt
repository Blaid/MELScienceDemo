package com.melscience.presentation.main.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.melscience.R
import com.melscience.common.koin.currentScope
import com.melscience.common.koin.loadModules
import com.melscience.core.controller.BaseMvpController
import com.melscience.presentation.CURRENCY_NAME_KEY
import com.melscience.presentation.CURRENCY_REQUEST_CODE
import com.melscience.presentation.main.di.mainModule
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.main_layout.*
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.melscience.di.currencyModelModule
import com.melscience.presentation.main.ui.adapter.BanknotesBottomSheetAdapter
import com.melscience.utils.extension.addTextWatcher
import com.melscience.utils.extension.hideKeyboard
import com.melscience.utils.extension.itemSelectedListener
import com.melscience.utils.extension.showPopup
import kotlinx.android.synthetic.main.banknotes_bottom_sheet.*

class MainController @JvmOverloads constructor(
  args: Bundle? = null
) : BaseMvpController(args), MainView, View.OnClickListener {
  override val layoutId = R.layout.main_layout

  @InjectPresenter lateinit var presenter: MainPresenter
  @ProvidePresenter fun providePresenter() = currentScope?.get<MainPresenter>()

  private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
  private lateinit var banknotesBottomSheetAdapter: BanknotesBottomSheetAdapter

  override fun onViewBound(view: View?) {
    super.onViewBound(view)

    initListeners()
    initSpinner()
    initBanknotesBottomSheet()
  }


  // region MainView

  override fun setSelectedCurrency(currencyName: String) {
    btnCurrency?.setSelectedText(currencyName)
  }

  override fun showOrHideBanknotesDialog(show: Boolean) {
    bottomSheetBehavior?.state = if (show) {
      BottomSheetBehavior.STATE_EXPANDED
    } else {
      BottomSheetBehavior.STATE_COLLAPSED
    }
  }

  override fun setResultCurrencyTitle(currency: String) {
    tvCurrencyTitleText?.text = currency
  }

  override fun setBanknotesResult(banknotes: List<Pair<Int, Int>>) {
    with(banknotesBottomSheetAdapter) {
      clear()
      addBanknotes(banknotes)
      notifyDataSetChanged()
    }
  }

  override fun showMessagePopup(msg: String) {
    showPopup(msg)
  }

  override fun closeKeyboard() {
    hideKeyboard()
  }

  // endregion


  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.btnCurrency -> presenter.currencyAction()
      R.id.btnCalculate -> presenter.calculateAction()
      R.id.btnCloseBanknotesBottomSheet -> presenter.closeBanknotesBottomSheetAction()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK) {
      when (requestCode) {
        CURRENCY_REQUEST_CODE -> {
          val currencyName = data?.getStringExtra(CURRENCY_NAME_KEY)
            .orEmpty()

          presenter.selectedCurrencyAction(currencyName)
        }
      }
    }
  }

  override fun initializeInjector() {
    loadModules(mainModule, currencyModelModule<MainController>())
  }

  override fun onDestroyView(view: View) {
    super.onDestroyView(view)

    clearFindViewByIdCache()
  }

  private fun initListeners() {
    btnCurrency?.setOnClickListener(this)
    btnCalculate?.setOnClickListener(this)
    btnCloseBanknotesBottomSheet?.setOnClickListener(this)

    switchEndlessBanknotes?.setOnCheckedChangeListener { _, isChecked ->
      presenter.checkedEndlessBanknotes(isChecked)
    }

    etAmount?.addTextWatcher { presenter.amountTextChange(it.toString()) }
  }

  private fun initSpinner() {
    activity?.also { activity ->
      val currencyTypeValues = resources?.getStringArray(R.array.currencyTypes)
        .orEmpty()

      spinnerCurrencyType?.adapter = ArrayAdapter<String>(
        activity,
        android.R.layout.simple_spinner_item,
        currencyTypeValues
      ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
      }

      spinnerCurrencyType?.setSelection(currencyTypeValues.size - 1)
      spinnerCurrencyType?.itemSelectedListener { presenter.currencyTypeItemSelectedAction(it) }
    }
  }

  private fun initBanknotesBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(banknotesBottomSheet)
    banknotesBottomSheetAdapter = BanknotesBottomSheetAdapter()
    with(rvBanknotesResult) {
      setHasFixedSize(false)
      layoutManager = LinearLayoutManager(activity)
      adapter = banknotesBottomSheetAdapter
    }
  }
}