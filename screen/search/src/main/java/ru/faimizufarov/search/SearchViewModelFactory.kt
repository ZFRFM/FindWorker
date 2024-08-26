package ru.faimizufarov.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.GetResultUseCase
import javax.inject.Inject

class SearchViewModelFactory
@Inject
constructor(
    val getResultUseCase: GetResultUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SearchViewModel(
            getResultUseCase
        ) as T
}