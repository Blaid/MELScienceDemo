package com.melscience.model.currency.domain

enum class CurrencyType {
  BIG,
  SMALL,
  DIFFERENT;

  companion object {
    fun find(ordinal: Int) = when (ordinal) {
      BIG.ordinal -> BIG
      SMALL.ordinal -> SMALL
      else -> DIFFERENT
    }
  }
}