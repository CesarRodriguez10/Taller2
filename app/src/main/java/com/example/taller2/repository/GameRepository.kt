package com.example.taller2.repository

import com.example.taller2.firebase.FirebaseService
import com.example.taller2.model.GameState
import kotlin.random.Random

class GameRepository {

    private val firebase = FirebaseService()

    fun performAction(state: GameState, action: String): GameState {

        when (action) {
            "ahorrar" -> {
                state.currentMoney += 200
                state.result = "Ahorraste +200"
            }

            "invertir" -> {
                val result = Random.nextInt(-300, 500)
                state.currentMoney += result
                state.result = "Invertiste: $result"
            }

            "gastar" -> {
                state.currentMoney -= 150
                state.result = "Gastaste -150"
            }
        }

        if (Random.nextBoolean()) {
            val event = Random.nextInt(-200, 300)
            state.currentMoney += event
            state.result += "\nEvento: $event"
        }

        state.currentTurn++

        if (state.currentMoney <= 0) {
            state.isGameOver = true
            state.result += "\nPerdiste :C"
        }

        if (state.currentMoney >= state.goal) {
            state.isGameOver = true
            state.result += "\nGanaste :D"
        }

        state.turnsPlayed++

        if (state.currentMoney > 0) {
            state.currentStreak++
            if (state.currentStreak > state.bestStreak) {
                state.bestStreak = state.currentStreak
            }
        } else {
            state.currentStreak = 0
        }
        firebase.saveGame(state)

        return state
    }
}
