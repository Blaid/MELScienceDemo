package com.melscience.presentation.main.ui

import com.melscience.core.api.schedulers.impl.TestSchedulers
import com.melscience.model.currency.domain.CurrencyType
import com.melscience.model.currency.domain.calculate.CalculateBanknotesFactory
import com.melscience.model.currency.domain.entity.CurrencyDomain
import com.melscience.model.currency.domain.exception.CalculateBanknotesException
import com.melscience.model.currency.domain.interactor.CurrencyInteractor
import com.melscience.presentation.main.ui.navigator.MainNavigator
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

private const val EUR_CURRENCY_NAME = "EUR"
private const val SMALL_CURRENCY_POSITION = 1
private const val TEST_AMOUNT = 1030
private const val ERROR_MESSAGE_CALCULATE = "error"
private val TEST_SMALL_CURRENCY_TYPE = CurrencyType.SMALL

class MainPresenterTest {
  private lateinit var presenter: MainPresenter
  private lateinit var view: MainView
  private lateinit var navigator: MainNavigator

  private val testCurrencies = listOf(
    CurrencyDomain(
      name = "EUR",
      banknotes = arrayOf()
    )
  )

  private val testEURSmallEndlessResult = listOf(
    Pair(50, 2),
    Pair(20, 1),
    Pair(10, 1)
  )

  @Before
  fun setUp() {
    view = mock()
    navigator = mock()

    presenter = MainPresenter(
      currencyInteractor = CurrencyInteractor(
        currencyRepository = mock { on { getCurrencies() } doReturn Single.just(testCurrencies) },
        calculateBanknotesFactory = CalculateBanknotesFactory(
          descendingCalculateBanknotes = mock {
            on {
              it.calculate(
                amount = TEST_AMOUNT,
                currencyType = TEST_SMALL_CURRENCY_TYPE,
                isEndlessBanknotes = true,
                banknotes = testCurrencies.first()
                  .banknotes
              )
            } doReturn testEURSmallEndlessResult
          },
          ascendingCalculateBanknotes = mock()
        ),
        schedulersProvider = TestSchedulers()
      ),
      errorHandler = mock {
        on { getErrorMessage(CalculateBanknotesException()) } doReturn ERROR_MESSAGE_CALCULATE
      },
      navigator = navigator
    )
  }

  @Test
  fun testFirstLoadData() {
    presenter.attachView(view)
    verify(view).setSelectedCurrency(EUR_CURRENCY_NAME)
  }

  @Test
  fun testCalculateCountBanknotes() {
    with(presenter) {
      attachView(view)
      currencyTypeItemSelectedAction(SMALL_CURRENCY_POSITION)
      checkedEndlessBanknotes(true)
      amountTextChange(TEST_AMOUNT.toString())
      calculateAction()
    }

    verify(view).closeKeyboard()
    verify(view).showOrHideBanknotesDialog(true)
    verify(view).setResultCurrencyTitle(EUR_CURRENCY_NAME)
    verify(view).setBanknotesResult(testEURSmallEndlessResult)
  }

  @Test
  fun testCloseBanknotesBottomSheetAction() {
    with(presenter) {
      attachView(view)
      closeBanknotesBottomSheetAction()
    }

    verify(view).showOrHideBanknotesDialog(false)
  }

  @Test
  fun testOpenCurrency() {
    presenter.currencyAction()

    verify(navigator).openCurrency()
  }
}