package com.example.apisampleapp.ui

import com.example.apisampleapp.ui.model.Item

data class InvestUiState(
    val items: List<Item> = emptyList(),
    val loading: Boolean? = false,
    val error: Throwable? = null
)
