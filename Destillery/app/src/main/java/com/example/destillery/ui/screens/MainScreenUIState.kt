package com.example.destillery.ui.screens

import com.example.destillery.database.Item

sealed class MainScreenUIState {
    class Default : MainScreenUIState()
    class Success(val items: List<Item>) : MainScreenUIState()
}