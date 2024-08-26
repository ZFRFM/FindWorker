package ru.faimizufarov.search.adapter

import android.view.View
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.search.databinding.ItemVacancyBinding

class VacancyViewHolder(
    private val itemBinding: ItemVacancyBinding,
    onItemClicked: (Int) -> Unit,
): RecyclerView.ViewHolder(itemBinding.root) {

    init {
        with(itemBinding) {
            root.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    fun bind(vacancy: Vacancy) {
        with(itemBinding) {
            vacancyFullHeartImageView.setOnClickListener {
                if (vacancy.isFavorite == true) {
                    vacancyFullHeartImageView.setImageResource(
                        ru.faimizufarov.core.R.drawable.action_favourites
                    )
                } else {
                    vacancyFullHeartImageView.setImageResource(
                        ru.faimizufarov.core.R.drawable.action_full_heart
                    )
                }
            }

            if (vacancy.lookingNumber != null) {
                lookingNumberTextView.text = itemBinding.root.context.getString(
                    ru.faimizufarov.core.R.string.looking_person_number,
                    vacancy.lookingNumber
                )
            } else {
                lookingNumberTextView.visibility = View.GONE
            }

            if (vacancy.isFavorite == true) {
                vacancyFullHeartImageView.setImageResource(
                    ru.faimizufarov.core.R.drawable.action_full_heart
                )
            } else {
                vacancyFullHeartImageView.setImageResource(
                    ru.faimizufarov.core.R.drawable.action_favourites
                )
            }

            vacancyTitleTextView.text = vacancy.title
            addressTownTextView.text = vacancy.address?.town
            companyTextView.text = vacancy.company
            experienceTextView.text = vacancy.experience?.previewText

            publishedDateTextView.text = vacancy.publishedDate

        }
    }
}