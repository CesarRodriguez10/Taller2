package com.example.taller2.data.model

data class GameState(
    var currentMoney: Int = 1000,
    var currentTurn: Int = 1,
    var goal: Int = 5000,
    var result: String = "",
    var isGameOver: Boolean = false
)