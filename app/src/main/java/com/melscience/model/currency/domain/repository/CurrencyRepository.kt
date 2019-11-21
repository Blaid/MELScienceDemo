package com.melscience.model.currency.domain.repository

import com.melscience.model.currency.domain.entity.CurrencyDomain
import io.reactivex.Single

interface CurrencyRepository {
  fun getCurrencies(): Single<List<CurrencyDomain>>
}