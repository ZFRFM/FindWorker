package ru.faimizufarov.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthSharedViewModel: ViewModel() {
    private val _emailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = _emailLiveData

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
}