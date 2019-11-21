package com.melscience.presentation.currency.ui

import com.melscience.core.api.schedulers.impl.TestSchedulers
import com.melscience.model.currency.domain.calculate.CalculateBanknotesFactory
import com.melscience.model.currency.domain.entity.CurrencyDomain
import com.melscience.model.currency.domain.interactor.CurrencyInteractor
import com.melscience.presentation.currency.ui.model.CurrencyModel
import com.melscience.presentation.currency.ui.navigator.CurrencyNavigator
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

private const val BACK_WITHOUT_RESULT = ""

class CurrencyPresenterTest {
  private lateinit var presenter: CurrencyPresenter
  private lateinit var view: CurrencyView
  private lateinit var navigator: CurrencyNavigator

  private val testDomainCurrencies = listOf(
    CurrencyDomain(
      name = "EUR",
      banknotes = arrayOf()
    ),
    CurrencyDomain(
      name = "RUB",
      banknotes = arrayOf()
    )
  )

  private val testModelCurrencies = listOf(
    CurrencyModel(
      name = "EUR",
      banknotes = arrayOf()
    ),
    CurrencyModel(
      name = "RUB",
      banknotes = arrayOf()
    )
  )

  private val testCurrencyModel = testModelCurrencies.first()

  @Before
  fun setUp() {
    view = mock()
    navigator = mock()

    presenter = CurrencyPresenter(
      currencyInteractor = CurrencyInteractor(
        currencyRepository = mock {
          on { getCurrencies() } doReturn Single.just(testDomainCurrencies)
        },
        calculateBanknotesFactory = CalculateBanknotesFactory(
          descendingCalculateBanknotes = mock(),
          ascendingCalculateBanknotes = mock()
        ),
        schedulersProvider = TestSchedulers()
      ),
      currencyModelMapper = mock { on { map(testDomainCurrencies) } doReturn testModelCurrencies },
      navigator = navigator
    )
  }

  @Test
  fun testFirstLoadData() {
    presenter.attachView(view)

    verify(view).setCurrencies(testModelCurrencies)
  }

  @Test
  fun testBack() {
    presenter.backAction()

    verify(navigator).back(BACK_WITHOUT_RESULT)
  }

  @Test
  fun testSelectedCurrency() {
    presenter.currencyAction(testCurrencyModel)

    verify(navigator).back(testCurrencyModel.name)
  }
}