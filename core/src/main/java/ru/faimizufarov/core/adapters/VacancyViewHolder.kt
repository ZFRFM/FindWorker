package ru.faimizufarov.core.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.datetime.LocalDate
import ru.faimizufarov.core.R
import ru.faimizufarov.core.databinding.ItemVacancyBinding
import ru.faimizufarov.domain.models.Vacancy

class VacancyViewHolder(
    private val itemBinding: ItemVacancyBinding,
    onItemClicked: (Int) -> Unit,
    onHeartClicked: (Int) -> Unit
): RecyclerView.ViewHolder(itemBinding.root) {

    private var fullHeartIsDisplayed: Boolean = false
    //FIXME: Исправить это, если появится идея как

    init {
        with(itemBinding) {
            root.setOnClickListener {
                onItemClicked(adapterPosition)
            }

            vacancyHeartImageView.setOnClickListener {
                onHeartClicked(adapterPosition)

                fullHeartIsDisplayed = !fullHeartIsDisplayed

                if (fullHeartIsDisplayed) {
                    vacancyHeartImageView.setImageResource(
                        R.drawable.action_full_heart
                    )
                } else {
                    vacancyHeartImageView.setImageResource(
                        R.drawable.action_favourites
                    )
                }
            }
        }
    }

    fun bind(vacancy: Vacancy) {
        with(itemBinding) {
            fullHeartIsDisplayed = vacancy.isFavorite

            if (vacancy.lookingNumber != null) {
                lookingNumberTextView.text = itemBinding.root.context.resources.getQuantityString(
                    R.plurals.looking_number_count,
                    vacancy.lookingNumber?: 0,
                    vacancy.lookingNumber?: 0
                )
            } else {
                lookingNumberTextView.visibility = View.GONE
            }

            if (vacancy.isFavorite) {
                vacancyHeartImageView.setImageResource(
                    R.drawable.action_full_heart
                )
            } else {
                vacancyHeartImageView.setImageResource(
                    R.drawable.action_favourites
                )
            }

            vacancyTitleTextView.text = vacancy.title
            addressTownTextView.text = vacancy.address.town
            companyTextView.text = vacancy.company
            experienceTextView.text = vacancy.experience.previewText

            vacancy.publishedDate.let { publishedDate ->
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