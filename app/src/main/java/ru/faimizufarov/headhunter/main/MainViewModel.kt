package ru.faimizufarov.headhunter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.faimizufarov.domain.usecase.SetBadgeCounterValueUseCase

class MainViewModel(
    private val setBadgeCounterValueUseCase: SetBadgeCounterValueUseCase
): ViewModel() {

    private val _isBottomNavViewEnabled = MutableLiveData(false)
    val isBottomNavViewEnabled: LiveData<Boolean> = _isBottomNavViewEnabled

    private val _badgeCounterValue = MutableLiveData<Int>()
    val badgeCounterValue: LiveData<Int> = _badgeCounterValue

    fun setBottomNavViewAvailability(isEnabled: Boolean) {
        _isBottomNavViewEnabled.value = isEnabled
    }

    init {
        viewModelScope.launch {
            _badgeCounterValue.value = setBadgeCounterValueUseCase.execute()
        }
    }
}