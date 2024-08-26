package ru.faimizufarov.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.search.databinding.ItemOfferBinding

class OfferAdapter(
    private val onItemClick: (Offer) -> Unit,
) :
ListAdapter<Offer, OfferViewHolder>(ItemCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OfferViewHolder {
        val itemBinding =
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )

        return OfferViewHolder(itemBinding) { index ->
            onItemClick(currentList[index])
        }
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(
        holder: OfferViewHolder,
        position: Int,
    ) {
        val offer = currentList[position]
        holder.bind(offer)
    }

    companion object ItemCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(
            oldItem: Offer,
            newItem: Offer,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Offer,
            newItem: Offer,
        ) = oldItem == newItem
    }
}