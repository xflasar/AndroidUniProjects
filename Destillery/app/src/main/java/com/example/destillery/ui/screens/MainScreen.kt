package com.example.destillery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.destillery.database.Item
import com.example.destillery.navigation.INavigationRouter
import com.example.destillery.ui.theme.basicMargin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigation: INavigationRouter) {
    val viewModel = hiltViewModel<MainScreenViewModel>()

    val state = viewModel.mainScreenUIState.collectAsStateWithLifecycle()

    val items = remember { mutableStateListOf<Item>() }

    state.value.let {
        when(it) {
            is MainScreenUIState.Default -> {
                viewModel.loadItems()
            }
            is MainScreenUIState.Success -> {
                items.clear()
                items.addAll(it.items)
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Destillery")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navigation.navigateToAddScreen()
        }) { }
    }
    ) {
        MainScreenContent(it, items = items, navigation = navigation)
    }
}

@Composable
fun MainScreenContent (paddingValues: PaddingValues, items: List<Item>, navigation: INavigationRouter) {
    Column(modifier = Modifier.padding(paddingValues).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally) ,onClick = { navigation.navigateToStatisticsScreen()}) {
            Text("Show statistics")
        }
        LazyColumn(modifier = Modifier.padding(start = basicMargin), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items.forEach {
                item {
                    ItemRow(item = it)
                }
            }
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Column {
        Text(text = item.name)
        Text(text = item.volume + " kg")
    }
}