package com.example.taller2.firebase

import com.google.firebase.database.FirebaseDatabase
import com.example.taller2.model.GameState

class FirebaseService {

    private val db = FirebaseDatabase.getInstance().getReference("game")

    fun saveGame(state: GameState) {
        db.setValue(state)
    }

    fun getGame(callback: (GameState?) -> Unit) {
        db.get().addOnSuccessListener {
            val state = it.getValue(GameState::class.java)
            callback(state)
        }
    }
}