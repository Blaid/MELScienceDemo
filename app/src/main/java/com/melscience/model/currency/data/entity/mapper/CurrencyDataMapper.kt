package com.melscience.model.currency.data.entity.mapper

import com.melscience.model.currency.data.entity.BanknoteData
import com.melscience.model.currency.data.entity.CurrencyData
import com.melscience.model.currency.domain.entity.BanknoteDomain
import com.melscience.model.currency.domain.entity.CurrencyDomain

interface CurrencyDataMapper {
  fun map(data: CurrencyData): CurrencyDomain
  fun map(data: List<CurrencyData>): List<CurrencyDomain>
  fun map(data: Array<BanknoteData>): Array<BanknoteDomain>
  fun map(data: BanknoteData): BanknoteDomain
}