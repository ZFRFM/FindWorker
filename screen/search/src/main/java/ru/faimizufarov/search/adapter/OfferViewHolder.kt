package ru.faimizufarov.search.adapter

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.search.R
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
        when(offer.id) {
            "near_vacancies" -> {
                itemBinding.offerImageView.setBackgroundDrawable(
                    itemBinding.root.context.getDrawable(R.drawable.offer_dark_blue_background)
                )
            }

            "level_up_resume" -> {
                itemBinding.offerImageView.setBackgroundDrawable(
                    itemBinding.root.context.getDrawable(R.drawable.offer_dark_green_background)
                )
                itemBinding.offerImageView.setImageResource(R.drawable.offer_second_icon)
            }

            "temporary_job" -> {
                itemBinding.offerImageView.setBackgroundDrawable(
                    itemBinding.root.context.getDrawable(R.drawable.offer_dark_green_background)
                )
                itemBinding.offerImageView.setImageResource(R.drawable.offer_third_icon)
            }
        }

        with(itemBinding.offerTitleTextView) {
            text = offer.title
            maxLines = if (offer.button != null) 2 else 3
            ellipsize = TextUtils.TruncateAt.MIDDLE
        }

        with(itemBinding.offerButton) {
            if (offer.button != null) {
                text = offer.button?.text
            } else {
                visibility = View.GONE
            }
        }
    }

}