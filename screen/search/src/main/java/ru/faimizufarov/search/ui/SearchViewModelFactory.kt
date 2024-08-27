package ru.faimizufarov.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.domain.usecase.UpdateFavouriteVacancyUseCase
import javax.inject.Inject

class SearchViewModelFactory
@Inject
constructor(
    val getResultUseCase: GetResultUseCase,
    val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SearchViewModel(
            getResultUseCase,
            updateFavouriteVacancyUseCase
        ) as T
}