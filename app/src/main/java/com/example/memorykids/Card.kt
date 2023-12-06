package com.example.memorykids

data class Card(
    val image: Int,
    var isFlipped: Boolean = false,
    var isMatched: Boolean = false
)