package ru.faimizufarov.vacancy_page.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.faimizufarov.domain.models.Question
import ru.faimizufarov.search.databinding.ItemQuestionBinding

class QuestionViewHolder (
    private val itemBinding: ItemQuestionBinding,
    onItemClicked: (Int) -> Unit,
): RecyclerView.ViewHolder(itemBinding.root) {

    init {
        with(itemBinding) {
            root.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    fun bind(question: Question) {
        itemBinding.questionTextView.text = question.questionText
    }
}