package com.example.apisampleapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apisampleapp.ui.model.Item

@Composable
fun InvestScreen(investViewModel: InvestViewModel = viewModel()) {
    val investUiState by investViewModel.uiState.collectAsState()

    Column {
        Button(onClick = { investViewModel.load() }) {
            Text(text = "Request")
        }
        Loading(investUiState.loading)
        Error(investUiState.error)
        Content(items = investUiState.items)
    }
}

@Composable
private fun Content(items: List<Item>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            InstrumentCard(item)
        }
    }
}

@Composable
private fun InstrumentCard(item: Item) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Column {
            Text(
                text = item.title,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.subTitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun Loading(loading: Boolean?) {
    if (loading == true) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error(throwable: Throwable?) {
    throwable?.let {
        Text(text = throwable.message ?: "Request error")
    }
}