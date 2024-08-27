package ru.faimizufarov.favourite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.domain.usecase.GetFavouritesUseCase
import ru.faimizufarov.domain.usecase.UpdateFavouriteVacancyUseCase

class FavouriteViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
): ViewModel() {

    private val _favouriteVacanciesLiveData = MutableLiveData<List<Vacancy>>()
    val favouriteVacanciesLiveData: LiveData<List<Vacancy>> = _favouriteVacanciesLiveData

    init {
        viewModelScope.launch {
            val favouriteVacancies = getFavouritesUseCase.execute()
            _favouriteVacanciesLiveData.value = favouriteVacancies
        }
    }

    fun updateFavouriteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            updateFavouriteVacancyUseCase.execute(vacancy)
            _favouriteVacanciesLiveData.value = getFavouritesUseCase.execute()
        }
    }
}