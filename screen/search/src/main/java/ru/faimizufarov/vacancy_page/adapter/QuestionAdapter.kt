package ru.faimizufarov.vacancy_page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.faimizufarov.domain.models.Question
import ru.faimizufarov.search.databinding.ItemQuestionBinding

class QuestionAdapter(
    private val onItemClick: (Question) -> Unit,
) :
    ListAdapter<Question, QuestionViewHolder>(ItemCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuestionViewHolder {
        val itemBinding =
            ItemQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )

        return QuestionViewHolder(itemBinding) { index ->
            onItemClick(currentList[index])
        }
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(
        holder: QuestionViewHolder,
        position: Int,
    ) {
        val question = currentList[position]
        holder.bind(question)
    }

    companion object ItemCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(
            oldItem: Question,
            newItem: Question,
        ) = oldItem.questionText == newItem.questionText

        override fun areContentsTheSame(
            oldItem: Question,
            newItem: Question,
        ) = oldItem == newItem
    }
}