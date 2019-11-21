package com.melscience.model.currency.data.entity.mapper

import com.melscience.model.currency.data.entity.BanknoteData
import com.melscience.model.currency.data.entity.CurrencyData
import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.entity.CurrencyDomain

class CurrencyDataMapperImpl : CurrencyDataMapper {

  override fun map(data: CurrencyData) = CurrencyDomain(
    name = data.name,
    banknotes = map(data.banknotes)
  )

  override fun map(data: List<CurrencyData>) = data.map { map(it) }

  override fun map(data: Array<BanknoteData>) = data.map { map(it) }
    .toTypedArray()

  override fun map(data: BanknoteData) = BanknoteDomain(
    nominal = data.nominal,
    amount = data.amount
  )
}