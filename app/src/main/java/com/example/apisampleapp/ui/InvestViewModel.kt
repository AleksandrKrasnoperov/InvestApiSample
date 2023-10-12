package com.example.apisampleapp.ui

import androidx.lifecycle.ViewModel
import com.example.apisampleapp.data.api.RetrofitBuilder
import com.example.apisampleapp.domain.InvestRepository
import com.example.apisampleapp.ui.model.Item
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InvestViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InvestUiState())
    val uiState: StateFlow<InvestUiState> = _uiState.asStateFlow()


    private val remoteDataSource = InvestRepository()

    @OptIn(DelicateCoroutinesApi::class)
    fun load() {
        loading()
        GlobalScope.launch {
            try {
                val result = remoteDataSource.currencies()

                result.errorBody()?.let { error(it) }
                result.body()?.let { response ->
                    content(response.instruments.map { Item(it.name, it.ticker) })
                }
            } catch (e: Exception) {
                error(e)
            }
        }
    }

    private fun loading() {
        _uiState.update { currentState ->
            currentState.copy(loading = true, error = null)
        }
    }

    private fun content(items: List<Item>) {
        _uiState.update { currentState ->
            currentState.copy(items = items, loading = false, error = null)
        }
    }

    private fun error(throwable: Throwable) {
        _uiState.update { currentState ->
            currentState.copy(loading = false, error = throwable)
        }
    }
}