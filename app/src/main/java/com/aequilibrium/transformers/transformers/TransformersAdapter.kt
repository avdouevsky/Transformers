package com.aequilibrium.transformers.transformers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.R

class TransformersAdapter(
    private val onTransformerClicked: (region: TransformerViewModel) -> Unit,
    private val onDeleteTransformerClicked: (region: TransformerViewModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewItems: List<TransformerViewModel> = emptyList()
        set(value) {
            val oldItems = field
            val newItems = value
            field = value

            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return oldItems.size
                }

                override fun getNewListSize(): Int {
                    return newItems.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldItems[oldItemPosition]
                    val newItem = newItems[newItemPosition]

                    if (oldItem is TransformerViewModel && newItem is TransformerViewModel) {
                        return oldItem.id == newItem.id
                    }

                    return false
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return oldItems[oldItemPosition] == newItems[newItemPosition]
                }
            }).dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int): Int {
        return viewItems[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.id.transformer ->
                TransformerViewHolder(
                    layoutInflater.inflate(
                        R.layout.transformer_view_holder,
                        parent, false
                    ), onTransformerClicked, onDeleteTransformerClicked
                )
            else -> error("Unknown view type")
        }
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is TransformerViewHolder) {
            viewHolder.bind(viewItems[position])
        }
    }

}