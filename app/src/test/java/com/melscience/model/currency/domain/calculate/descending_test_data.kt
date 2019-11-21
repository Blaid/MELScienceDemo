package com.melscience.model.currency.domain.calculate

import com.melscience.model.currency.domain.entity.BanknoteDomain

val testDescendingBanknotes = arrayOf(
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

val testDescendingResultBigEndless3900Amount = listOf(
  Pair(500, 7),
  Pair(200, 2)
)

val testDescendingResultBigNotEndless3900Amount = listOf(
  Pair(500, 4),
  Pair(200, 9),
  Pair(100, 1)
)

val testDescendingResultSmallEndless1030Amount = listOf(
  Pair(50, 20),
  Pair(20, 1),
  Pair(10, 1)
)

val testDescendingResultSmallNotEndless1030Amount = listOf(
  Pair(50, 11),
  Pair(20, 20),
  Pair(10, 3),
  Pair(5, 10)
)

val testDescendingResultDifferentEndless13330Amount = listOf(
  Pair(500, 26),
  Pair(200, 1),
  Pair(100, 1),
  Pair(20, 1),
  Pair(10, 1)
)

val testDescendingResultDifferentNotEndless13330Amount = listOf(
  Pair(500, 4),
  Pair(200, 45),
  Pair(100, 13),
  Pair(50, 11),
  Pair(20, 20),
  Pair(10, 3),
  Pair(5, 10)
)