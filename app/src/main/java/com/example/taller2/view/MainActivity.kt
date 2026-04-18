package com.example.taller2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityMainBinding
import com.example.taller2.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.gameState.observe(this) { state ->
            binding.txtMoney.text = "Dinero: ${state.currentMoney}"
            binding.txtTurn.text = "Turno: ${state.currentTurn}"
            binding.txtResult.text = state.result
        }
        binding.btnAhorrar.setOnClickListener {
            viewModel.action("ahorrar")
        }

        binding.btnInvertir.setOnClickListener {
            viewModel.action("invertir")
        }

        binding.btnGastar.setOnClickListener {
            viewModel.action("gastar")
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetGame()
        }
    }
}