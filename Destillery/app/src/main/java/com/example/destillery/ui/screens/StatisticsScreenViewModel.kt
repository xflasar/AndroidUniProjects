package com.example.destillery.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destillery.database.IDestilleryLocalRepository
import com.example.destillery.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsScreenViewModel @Inject constructor(private val repository: IDestilleryLocalRepository) : ViewModel() {
    private val _statisticsScreenUIState: MutableStateFlow<StatisticsUIState> = MutableStateFlow(value = StatisticsUIState.Default())
    val statisticsUIState = _statisticsScreenUIState.asStateFlow()
    private val data = StatisticsData()

    fun loadStatistics() {
        viewModelScope.launch {
            val stats = repository.stats()
            data.totalKg = stats.first
            data.totalCount = stats.second
            _statisticsScreenUIState.update {
                StatisticsUIState.Success(data)
            }
        }
    }
}