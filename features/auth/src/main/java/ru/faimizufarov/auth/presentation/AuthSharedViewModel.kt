package ru.faimizufarov.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthSharedViewModel: ViewModel() {
    private val _emailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = _emailLiveData

    private val _isPasswordEnabledLiveData = MutableLiveData<Boolean>()
    val isPasswordEnabledLiveData: LiveData<Boolean> = _isPasswordEnabledLiveData

    val isAuthEnabledLiveData: LiveData<Boolean> =
        MediatorLiveData<Boolean>()
            .apply {
                addSource(emailLiveData) { emailPhone ->
                    value = emailPhone.isNotEmpty()
                }
            }

    fun setEmailText(emailText: String) {
        _emailLiveData.value = emailText
    }

    fun setPasswordAvailability(isEnabled: Boolean) {
        _isPasswordEnabledLiveData.value = isEnabled
    }
}