package ru.faimizufarov.vacancy_page.di

import ru.faimizufarov.vacancy_page.ui.VacancyPageFragment

interface VacancyPageComponent {
    fun injectVacancyPageFragment(vacancyPageFragment: VacancyPageFragment)
}