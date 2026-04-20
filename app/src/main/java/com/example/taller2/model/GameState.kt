package com.example.taller2.model

data class GameState(
    var currentMoney: Int = 1000,
    var currentTurn: Int = 1,
    var goal: Int = 5000,
    var result: String = "",
    var isGameOver: Boolean = false,
    var turnsPlayed: Int = 0,
    var bestStreak: Int = 0,
    var currentStreak: Int = 0
)