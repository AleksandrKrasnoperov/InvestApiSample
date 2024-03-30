package com.example.apisampleapp.ui.theme

data class InvestUiState(
    val query: String = "",
    val instruments: List<Instrument> = emptyList(),
    val isLoading: Boolean = false
)

data class Instrument(
    val title: String,
    val subtitle: String
)
