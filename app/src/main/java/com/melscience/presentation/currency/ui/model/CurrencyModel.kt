package com.melscience.presentation.currency.ui.model

data class CurrencyModel(
  val name: String,
  val banknotes: Array<BanknoteModel>,
  var checked: Boolean = false
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }

    if (javaClass != other?.javaClass) {
      return false
    }

    other as CurrencyModel

    if (name != other.name) {
      return false
    }

    if (!banknotes.contentEquals(other.banknotes)) {
      return false
    }

    if (checked != other.checked) {
      return false
    }

    return true
  }
  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + banknotes.contentHashCode()
    result = 31 * result + checked.hashCode()
    return result
  }
}