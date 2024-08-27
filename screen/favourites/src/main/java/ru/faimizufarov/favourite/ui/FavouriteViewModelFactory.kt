package ru.faimizufarov.favourite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.GetFavouritesUseCase
import ru.faimizufarov.domain.usecase.UpdateFavouriteVacancyUseCase
import javax.inject.Inject

class FavouriteViewModelFactory
@Inject
constructor(
    val getFavouritesUseCase: GetFavouritesUseCase,
    val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        FavouriteViewModel(
            getFavouritesUseCase,
            updateFavouriteVacancyUseCase
        ) as T
}