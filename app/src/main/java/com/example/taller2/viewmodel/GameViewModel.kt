package com.example.taller2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taller2.model.GameState
import com.example.taller2.repository.GameRepository

class GameViewModel : ViewModel() {

    private val repository = GameRepository()

    private val _gameState = MutableLiveData(GameState())
    val gameState: LiveData<GameState> = _gameState

    fun action(action: String) {
        val current = _gameState.value ?: return
        val updated = repository.performAction(current, action)
        _gameState.value = updated
    }

    fun resetGame() {
        _gameState.value = GameState()
    }
}