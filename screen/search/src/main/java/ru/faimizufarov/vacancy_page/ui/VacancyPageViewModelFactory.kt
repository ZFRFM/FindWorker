package ru.faimizufarov.vacancy_page.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.GetVacancyUseCase
import javax.inject.Inject

class VacancyPageViewModelFactory
@Inject
constructor(
    val getVacancyUseCase: GetVacancyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        VacancyPageViewModel(
            getVacancyUseCase
        ) as T
}