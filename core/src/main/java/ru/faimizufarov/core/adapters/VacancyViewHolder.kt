package ru.faimizufarov.core.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.datetime.LocalDate
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
                lookingNumberTextView.text = itemBinding.root.context.resources.getQuantityString(
                    ru.faimizufarov.core.R.plurals.looking_number_count,
                    vacancy.lookingNumber?: 0,
                    vacancy.lookingNumber?: 0
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

            vacancy.publishedDate?.let { publishedDate ->
                publishedDateTextView.text = formatPublishedDateToUserStyle(publishedDate)
            }
        }
    }

    private fun formatPublishedDateToUserStyle(publishedDate: String): String {
        val date = LocalDate.parse(publishedDate)

        val day = date.dayOfMonth
        val month = date.monthNumber

        val monthsGenitive = listOf("",
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
        )

        return "Опубликовано $day ${monthsGenitive[month]}"
    }
}