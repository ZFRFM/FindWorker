package ru.faimizufarov.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.usecase.GetResultUseCase

class SearchViewModel(
    private val getResultUseCase: GetResultUseCase
): ViewModel() {

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result

    init {
        viewModelScope.launch {
            val result = getResultUseCase.execute()
            _result.value = result
        }
    }
}