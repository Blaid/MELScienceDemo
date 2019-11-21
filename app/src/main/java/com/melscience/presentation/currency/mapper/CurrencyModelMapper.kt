package com.melscience.presentation.currency.mapper

import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.entity.CurrencyDomain
import com.melscience.presentation.currency.ui.model.BanknoteModel
import com.melscience.presentation.currency.ui.model.CurrencyModel

interface CurrencyModelMapper {
  fun map(domain: CurrencyDomain): CurrencyModel
  fun map(domain: List<CurrencyDomain>): List<CurrencyModel>
  fun map(domain: Array<BanknoteDomain>): Array<BanknoteModel>
  fun map(domain: BanknoteDomain): BanknoteModel
}