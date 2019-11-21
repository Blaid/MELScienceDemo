package com.melscience.presentation.currency.mapper

import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.entity.CurrencyDomain
import com.melscience.presentation.currency.ui.model.BanknoteModel
import com.melscience.presentation.currency.ui.model.CurrencyModel

class CurrencyModelMapperImpl : CurrencyModelMapper {

  override fun map(domain: CurrencyDomain) = CurrencyModel(
    name = domain.name,
    banknotes = map(domain.banknotes)
  )

  override fun map(domain: List<CurrencyDomain>) = domain.map { map(it) }

  override fun map(domain: Array<BanknoteDomain>) = domain.map { map(it) }
    .toTypedArray()

  override fun map(domain: BanknoteDomain) = BanknoteModel(
    nominal = domain.nominal,
    amount = domain.amount
  )
}