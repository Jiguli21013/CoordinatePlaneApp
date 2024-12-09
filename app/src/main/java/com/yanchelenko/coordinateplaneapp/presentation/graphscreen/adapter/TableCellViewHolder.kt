package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yanchelenko.coordinateplaneapp.databinding.ItemTableCellBinding

class TableCellViewHolder(private val binding: ItemTableCellBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(value: String) {
        binding.textCellTV.text = value
    }
}
