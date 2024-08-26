package ru.faimizufarov.search.di

import ru.faimizufarov.search.ui.SearchFragment
import javax.inject.Singleton

@Singleton
interface SearchComponent {
    fun injectSearchFragment(searchFragment: SearchFragment)
}