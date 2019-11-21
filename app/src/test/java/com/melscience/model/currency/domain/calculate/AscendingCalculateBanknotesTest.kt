package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.CurrencyType
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AscendingCalculateBanknotesTest {
  private lateinit var calculateBanknotes: AscendingCalculateBanknotes

  @Before
  fun setUp() {
    calculateBanknotes = AscendingCalculateBanknotes()
  }

  /**
   * Amount: 11000
   * Currency type: BIG
   * Is endless banknotes: true
   */
  @Test
  fun testBigEndless3900Amount() {
    val result = calculateBanknotes.calculate(
      amount = 11000,
      currencyType = CurrencyType.BIG,
      isEndlessBanknotes = true,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultBigEndless11000Amount))
  }

  /**
   * Amount: 11000
   * Currency type: BIG
   * Is endless banknotes: false
   */
  @Test
  fun testBigNotEndless3900Amount() {
    val result = calculateBanknotes.calculate(
      amount = 11000,
      currencyType = CurrencyType.BIG,
      isEndlessBanknotes = false,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultBigNotEndless11000Amount))
  }

  /**
   * Amount: 480
   * Currency type: SMALL
   * Is endless banknotes: true
   */
  @Test
  fun testSmallEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 480,
      currencyType = CurrencyType.SMALL,
      isEndlessBanknotes = true,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultSmallEndless480Amount))
  }

  /**
   * Amount: 480
   * Currency type: SMALL
   * Is endless banknotes: false
   */
  @Test
  fun testSmallNotEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 480,
      currencyType = CurrencyType.SMALL,
      isEndlessBanknotes = false,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultSmallNotEndless480Amount))
  }

  /**
   * Amount: 13330
   * Currency type: DIFFERENT
   * Is endless banknotes: true
   */
  @Test
  fun testDifferentEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 13330,
      currencyType = CurrencyType.DIFFERENT,
      isEndlessBanknotes = true,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultDifferentEndless13330Amount))
  }

  /**
   * Amount: 13330
   * Currency type: DIFFERENT
   * Is endless banknotes: false
   */
  @Test
  fun tesDifferentNotEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 13330,
      currencyType = CurrencyType.DIFFERENT,
      isEndlessBanknotes = false,
      banknotes = testAscendingBanknotes
    )

    assertThat(result, `is`(testAscendingResultDifferentNotEndless13330Amount))
  }
}