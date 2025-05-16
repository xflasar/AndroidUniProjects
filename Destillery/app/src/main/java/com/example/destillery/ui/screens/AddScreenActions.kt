package com.example.destillery.ui.screens

interface AddScreenActions {
    fun onTextChangedFruitName(text: String)
    fun onTextChangedAmount(text: String)
    fun saveItem()
}