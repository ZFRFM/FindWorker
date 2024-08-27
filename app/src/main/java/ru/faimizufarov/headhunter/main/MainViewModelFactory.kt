package ru.faimizufarov.headhunter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.usecase.SetBadgeCounterValueUseCase
import javax.inject.Inject

class MainViewModelFactory
@Inject
constructor(
    val setBadgeCounterValueUseCase: SetBadgeCounterValueUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(
            setBadgeCounterValueUseCase
        ) as T
}