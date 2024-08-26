package ru.faimizufarov.vacancy_page.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.domain.usecase.GetVacancyUseCase

class VacancyPageViewModel(
    private val getVacancyUseCase: GetVacancyUseCase
): ViewModel() {
    private val _vacancy = MutableLiveData<Vacancy>()
    val vacancy: LiveData<Vacancy> = _vacancy

    fun filterResult(id: String) {
        viewModelScope.launch {
            _vacancy.value = getVacancyUseCase.execute(id)
        }
    }
}