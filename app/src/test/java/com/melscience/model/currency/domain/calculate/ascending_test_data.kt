package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.entity.BanknoteDomain

val testAscendingBanknotes = arrayOf(
  BanknoteDomain(
    nominal = 5,
    amount = 10
  ),
  BanknoteDomain(
    nominal = 10,
    amount = 3
  ),
  BanknoteDomain(
    nominal = 20,
    amount = 20
  ),
  BanknoteDomain(
    nominal = 50,
    amount = 11
  ),
  BanknoteDomain(
    nominal = 100,
    amount = 13
  ),
  BanknoteDomain(
    nominal = 200,
    amount = 45
  ),
  BanknoteDomain(
    nominal = 500,
    amount = 4
  )
)

val testAscendingResultBigEndless11000Amount = listOf(
  Pair(200, 55)
)

val testAscendingResultBigNotEndless11000Amount = listOf(
  Pair(200, 45),
  Pair(500, 4)
)

val testAscendingResultSmallEndless480Amount = listOf(
  Pair(5, 96)
)

val testAscendingResultSmallNotEndless480Amount = listOf(
  Pair(5, 10),
  Pair(10, 3),
  Pair(20, 20)
)

val testAscendingResultDifferentEndless13330Amount = listOf(
  Pair(5, 2666)
)

val testAscendingResultDifferentNotEndless13330Amount = listOf(
  Pair(5, 10),
  Pair(10, 3),
  Pair(20, 20),
  Pair(50, 11),
  Pair(100, 13),
  Pair(200, 45),
  Pair(500, 4)
)