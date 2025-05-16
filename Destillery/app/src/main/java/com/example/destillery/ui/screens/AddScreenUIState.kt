package com.example.destillery.ui.screens

sealed class AddScreenUIState {
    class Default : AddScreenUIState()
    class DataChanged(val data: AddScreenData) : AddScreenUIState()
    class ItemSaved : AddScreenUIState()
}