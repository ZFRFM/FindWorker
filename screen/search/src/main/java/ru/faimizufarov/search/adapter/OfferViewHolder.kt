package ru.faimizufarov.search.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.search.databinding.ItemOfferBinding

class OfferViewHolder(
    private val itemBinding: ItemOfferBinding,
    onItemClicked: (Int) -> Unit,
):
    RecyclerView.ViewHolder(itemBinding.root) {

    init {
        with(itemBinding) {
            root.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    fun bind(offer: Offer) {
        itemBinding.offerTitleTextView.text = offer.title
    }

}