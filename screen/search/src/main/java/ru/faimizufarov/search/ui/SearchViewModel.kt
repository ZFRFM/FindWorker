package ru.faimizufarov.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.domain.usecase.UpdateFavouriteVacancyUseCase

class SearchViewModel(
    private val getResultUseCase: GetResultUseCase,
    private val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
): ViewModel() {

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result

    init {
        viewModelScope.launch {
            val result = getResultUseCase.execute()
            _result.value = result
        }
    }

    fun updateFavouriteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            updateFavouriteVacancyUseCase.execute(vacancy)
        }
    }
}