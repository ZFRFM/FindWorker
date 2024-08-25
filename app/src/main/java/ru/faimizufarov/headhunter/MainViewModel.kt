package ru.faimizufarov.headhunter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _isBottomNavViewEnabled = MutableLiveData(false)
    val isBottomNavViewEnabled: LiveData<Boolean> = _isBottomNavViewEnabled

    fun setBottomNavViewAvailability(isEnabled: Boolean) {
        _isBottomNavViewEnabled.value = isEnabled
    }
}