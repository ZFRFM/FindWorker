package ru.faimizufarov.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.search.databinding.ItemVacancyBinding

class VacancyAdapter(
    private val onItemClick: (Vacancy) -> Unit,
): ListAdapter<Vacancy, VacancyViewHolder>(ItemCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VacancyViewHolder {
        val itemBinding =
            ItemVacancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )

        return VacancyViewHolder(itemBinding) { index ->
            onItemClick(currentList[index])
        }
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(
        holder: VacancyViewHolder,
        position: Int,
    ) {
        val vacancy = currentList[position]
        holder.bind(vacancy)
    }

    companion object ItemCallback : DiffUtil.ItemCallback<Vacancy>() {
        override fun areItemsTheSame(
            oldItem: Vacancy,
            newItem: Vacancy,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Vacancy,
            newItem: Vacancy,
        ) = oldItem == newItem
    }
}