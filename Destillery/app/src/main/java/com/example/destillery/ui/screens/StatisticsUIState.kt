package com.example.destillery.ui.screens

sealed class StatisticsUIState {
    class Default: StatisticsUIState()
    class Success(val stats: StatisticsData): StatisticsUIState()
}