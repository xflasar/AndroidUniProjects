package com.example.destillery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen() {
    val view = hiltViewModel<StatisticsScreenViewModel>()
    val state = view.statisticsUIState.collectAsStateWithLifecycle()
    var data by remember { mutableStateOf(StatisticsData()) }

    state.value.let {
        when(it) {
            is StatisticsUIState.Default -> {
                view.loadStatistics()
            }
            is StatisticsUIState.Success -> {
                data = it.stats
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Statistics")
            })
        }
    ) {
        StatisticsScreenContent(it, data = data)
    }
}

@Composable
fun StatisticsScreenContent(paddingValues: PaddingValues, data: StatisticsData) {
    Column(modifier = Modifier.padding(paddingValues).fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Celkovy počet kg: " + data.totalKg)
        Text(text = "Celkový počet záznamů: " + data.totalCount)
    }
}