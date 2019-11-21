package com.melscience.presentation.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melscience.R
import kotlinx.android.synthetic.main.banknotes_botom_sheet_item.view.*

class BanknotesBottomSheetAdapter : RecyclerView.Adapter<BanknotesBottomSheetAdapter.ViewHolder>() {
  private val items = mutableListOf<Pair<Int, Int>>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.banknotes_botom_sheet_item, parent, false)
  )

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = get(position)

    with(holder) {
      tvBanknoteValue?.text = item.first
        .toString()

      tvBanknoteCount?.text = item.second
        .toString()
    }
  }

  private fun get(position: Int) = items[position]

  fun clear() {
    items.clear()
  }

  fun addBanknotes(banknotes: List<Pair<Int, Int>>) {
    items.addAll(banknotes)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvBanknoteValue: TextView? = itemView.tvBanknoteValue
    val tvBanknoteCount: TextView? = itemView.tvBanknoteCount
  }
}