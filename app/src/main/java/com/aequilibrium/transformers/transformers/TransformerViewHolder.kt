package com.aequilibrium.transformers.transformers

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.utils.TwoColorTextBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.transformer_view_holder.view.*

class TransformerViewHolder(
    override val containerView: View,
    private val onTransformerClicked: (region: TransformerViewModel) -> Unit,
    private val onDeleteTransformerClicked: (region: TransformerViewModel) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var twoColorTextBuilder = TwoColorTextBuilder(
        itemView.context.resources.getColor(R.color.grey_100),
        itemView.context.resources.getColor(R.color.grey_80)
    )

    init {
        itemView.container.setOnClickListener { onTransformerClicked(viewModel) }
        itemView.deleteButton.setOnClickListener { onDeleteTransformerClicked(viewModel) }
    }

    private lateinit var viewModel: TransformerViewModel

    fun bind(viewModel: TransformerViewModel) {
        this.viewModel = viewModel
        itemView.name.text = viewModel.name.text
        itemView.team.text = twoColorTextBuilder.getColoredText(viewModel.team)
        itemView.strength.text = twoColorTextBuilder.getColoredText(viewModel.strength)
        itemView.intelligence.text = twoColorTextBuilder.getColoredText(viewModel.intelligence)
        itemView.speed.text = twoColorTextBuilder.getColoredText(viewModel.speed)
        itemView.endurance.text = twoColorTextBuilder.getColoredText(viewModel.endurance)
        itemView.rank.text = twoColorTextBuilder.getColoredText(viewModel.rank)
        itemView.courage.text = twoColorTextBuilder.getColoredText(viewModel.courage)
        itemView.firepower.text = twoColorTextBuilder.getColoredText(viewModel.firepower)
        itemView.skill.text = twoColorTextBuilder.getColoredText(viewModel.skill)
        itemView.overallRating.text = twoColorTextBuilder.getColoredText(viewModel.overallRating)
        Picasso.with(itemView.context).load(Uri.parse(viewModel.teamIcon)).into(itemView.teamIcon)
    }
}