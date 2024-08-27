package ru.faimizufarov.favourite.di

import ru.faimizufarov.favourite.ui.FavouriteFragment
import javax.inject.Singleton

@Singleton
interface FavouriteComponent {
    fun injectFavouriteFragment(favouriteFragment: FavouriteFragment)
}