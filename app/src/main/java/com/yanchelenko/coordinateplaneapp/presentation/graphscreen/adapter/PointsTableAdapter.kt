package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanchelenko.coordinateplaneapp.databinding.ItemTableCellBinding
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.PointUI

class PointsTableAdapter(
    private val points: List<PointUI>
) : RecyclerView.Adapter<TableCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableCellViewHolder {
        val binding = ItemTableCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableCellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableCellViewHolder, position: Int) {
        val isXRow = position % 2 == 0
        val index = position / 2

        val value = if (isXRow) {
            points[index].x.toString()
        } else {
            points[index].y.toString()
        }

        holder.bind(value)
    }

    override fun getItemCount(): Int = points.size * 2

}
