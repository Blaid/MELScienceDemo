package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.CurrencyType
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class DescendingCalculateBanknotesTest {
  private lateinit var calculateBanknotes: DescendingCalculateBanknotes

  @Before
  fun setUp() {
    calculateBanknotes = DescendingCalculateBanknotes()
  }

  /**
   * Amount: 3900
   * Currency type: BIG
   * Is endless banknotes: true
   */
  @Test
  fun testBigEndless3900Amount() {
    val result = calculateBanknotes.calculate(
      amount = 3900,
      currencyType = CurrencyType.BIG,
      isEndlessBanknotes = true,
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultBigEndless3900Amount))
  }

  /**
   * Amount: 3900
   * Currency type: BIG
   * Is endless banknotes: false
   */
  @Test
  fun testBigNotEndless3900Amount() {
    val result = calculateBanknotes.calculate(
      amount = 3900,
      currencyType = CurrencyType.BIG,
      isEndlessBanknotes = false,
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultBigNotEndless3900Amount))
  }

  /**
   * Amount: 1030
   * Currency type: SMALL
   * Is endless banknotes: true
   */
  @Test
  fun testSmallEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 1030,
      currencyType = CurrencyType.SMALL,
      isEndlessBanknotes = true,
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultSmallEndless1030Amount))
  }

  /**
   * Amount: 1030
   * Currency type: SMALL
   * Is endless banknotes: false
   */
  @Test
  fun testSmallNotEndless1300Amount() {
    val result = calculateBanknotes.calculate(
      amount = 1030,
      currencyType = CurrencyType.SMALL,
      isEndlessBanknotes = false,
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultSmallNotEndless1030Amount))
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
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultDifferentEndless13330Amount))
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
      banknotes = testDescendingBanknotes
    )

    assertThat(result, `is`(testDescendingResultDifferentNotEndless13330Amount))
  }
}