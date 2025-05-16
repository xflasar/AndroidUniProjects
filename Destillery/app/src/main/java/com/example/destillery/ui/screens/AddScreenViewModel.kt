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
class AddScreenViewModel @Inject constructor(private val repository: IDestilleryLocalRepository) : ViewModel(), AddScreenActions {
    private val _addScreenUIState: MutableStateFlow<AddScreenUIState> = MutableStateFlow(value = AddScreenUIState.Default())

    val addScreenUIState = _addScreenUIState.asStateFlow()

    private val data = AddScreenData()

    override fun onTextChangedFruitName(text: String) {
        data.item.name = text
        _addScreenUIState.update {
            AddScreenUIState.DataChanged(data)
        }
    }

    override fun onTextChangedAmount(text: String) {
        data.item.volume = text
        _addScreenUIState.update {
            AddScreenUIState.DataChanged(data)
        }
    }

    override fun saveItem() {
        viewModelScope.launch {
            repository.insert(data.item)
            _addScreenUIState.update {
                AddScreenUIState.ItemSaved()
            }
        }
    }

}