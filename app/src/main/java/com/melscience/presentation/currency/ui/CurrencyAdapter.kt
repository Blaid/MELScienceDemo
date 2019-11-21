package com.melscience.presentation.currency.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.melscience.R
import com.melscience.presentation.currency.ui.model.CurrencyModel
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyAdapter(
  private val currencyCallback: (CurrencyModel) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
  private val items = mutableListOf<CurrencyModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.currency_item, parent, false),
    currencyCallback = currencyCallback
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currency = getItem(position)

    with(holder) {
      currencyModel = currency

      tvCurrencyText?.text = currency.name
      ivCurrencyCheckedIcon?.isVisible = currency.checked
    }
  }

  override fun getItemCount() = items.size

  fun addCurrencies(currencies: List<CurrencyModel>) {
    items.addAll(currencies)
  }

  private fun getItem(position: Int) = items[position]

  class ViewHolder(
    itemView: View,
    currencyCallback: (CurrencyModel) -> Unit
  ) : RecyclerView.ViewHolder(itemView) {
    val tvCurrencyText: TextView? = itemView.tvCurrencyText
    val ivCurrencyCheckedIcon: ImageView? = itemView.ivCurrencyCheckedIcon

    var currencyModel: CurrencyModel? = null

    init {
      itemView.setOnClickListener { currencyModel?.also(currencyCallback) }
    }
  }
}