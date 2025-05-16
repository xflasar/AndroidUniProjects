package com.example.destillery.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destillery.database.IDestilleryLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: IDestilleryLocalRepository) : ViewModel() {
    private val _mainScreenUIState: MutableStateFlow<MainScreenUIState> = MutableStateFlow(value = MainScreenUIState.Default())

    val mainScreenUIState = _mainScreenUIState.asStateFlow()

    fun loadItems() {
        viewModelScope.launch {
            repository.getAll().collect {
                items -> _mainScreenUIState.update {
                    MainScreenUIState.Success(items)
            }
            }
        }
    }
}