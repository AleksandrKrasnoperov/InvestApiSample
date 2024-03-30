package com.example.apisampleapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apisampleapp.domain.InvestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InvestViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InvestUiState())
    val uiState = _uiState.asStateFlow()

    private val repository = InvestRepository()

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onSearchButtonClick() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val response = repository.findInstrument(_uiState.value.query).body()
            _uiState.update { it.copy(isLoading = false) }
            if (response != null) {
                _uiState.update {
                    it.copy(instruments = response.instruments.map { instrument ->
                        Instrument(instrument.name, instrument.ticker)
                    })
                }
            }
        }
    }
}