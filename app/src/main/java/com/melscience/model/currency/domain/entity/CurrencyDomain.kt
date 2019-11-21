package com.melscience.model.currency.domain.entity

data class CurrencyDomain(val name: String, val banknotes: Array<BanknoteDomain>) {

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }

    if (javaClass != other?.javaClass) {
      return false
    }

    other as CurrencyDomain

    if (name != other.name) {
      return false
    }

    if (!banknotes.contentEquals(other.banknotes)) {
      return false
    }

    return true
  }
  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + banknotes.contentHashCode()
    return result
  }
}