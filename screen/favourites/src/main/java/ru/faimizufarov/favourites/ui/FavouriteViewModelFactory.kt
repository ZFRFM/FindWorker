package ru.faimizufarov.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.GetFavouritesUseCase
import javax.inject.Inject

class FavouriteViewModelFactory
@Inject
constructor(
    val getFavouritesUseCase: GetFavouritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        FavouriteViewModel(
            getFavouritesUseCase
        ) as T
}