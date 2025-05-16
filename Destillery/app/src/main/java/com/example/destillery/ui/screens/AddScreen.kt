package com.example.destillery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.destillery.navigation.INavigationRouter
import com.example.destillery.ui.theme.basicMargin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navigation: INavigationRouter) {
    val viewModel = hiltViewModel<AddScreenViewModel>()
    val state = viewModel.addScreenUIState.collectAsStateWithLifecycle()
    var data by remember { mutableStateOf(AddScreenData()) }

    state.value.let {
        when(it) {
            is AddScreenUIState.Default -> {

            }
            is AddScreenUIState.DataChanged -> {
                data = it.data
            }
            is AddScreenUIState.ItemSaved -> {
                navigation.returnBack()
            }
        }
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Add Item")
        }, navigationIcon = {
            IconButton(onClick = {
                navigation.returnBack()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        })
    }) {
        AddScreenContent(paddingValues = it, data = data, actions = viewModel)
    }
}

@Composable
fun AddScreenContent(paddingValues: PaddingValues, data: AddScreenData, actions: AddScreenActions) {
    var textFruitName by rememberSaveable { mutableStateOf(data.item.name) }
    var textAmount by rememberSaveable { mutableStateOf(data.item.volume) }

    Column(modifier = Modifier.padding(paddingValues).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.CenterHorizontally),
            value = textFruitName,
            onValueChange = {
                textFruitName = it
                actions.onTextChangedFruitName(it)
            },
            placeholder = { Text("Fruit name", textAlign = TextAlign.Center, fontSize = TextUnit(
                10F, TextUnitType.Sp))}
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.CenterHorizontally),
            value = textAmount,
            onValueChange = { if (it.isDigitsOnly() && it.isNotEmpty()) {
                textAmount = it
                actions.onTextChangedAmount(it)
            }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Amount (in kg)", textAlign = TextAlign.Center, fontSize = TextUnit(
                10F, TextUnitType.Sp))}
        )

        Button(modifier = Modifier.padding(start = basicMargin),onClick = {
            actions.saveItem()
        }) { Text("Save") }
    }
}