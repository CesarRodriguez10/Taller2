package com.example.taller2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityMainBinding
import com.example.taller2.viewmodel.GameViewModel
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val goal = intent.getIntExtra("goal", 5000)
        viewModel.gameState.value?.goal = goal

        viewModel.gameState.observe(this) { state ->
            binding.txtMoney.text = "Money: ${state.currentMoney}"
            binding.txtTurn.text = "Turn: ${state.currentTurn}"
            binding.txtResult.text = state.result

            if (state.isGameOver) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("money", state.currentMoney)
                intent.putExtra("goal", state.goal)
                intent.putExtra("turns", state.turnsPlayed)
                intent.putExtra("streak", state.bestStreak)
                startActivity(intent)
                finish()
            }
        }

        binding.btnAhorrar.setOnClickListener {
            viewModel.action("ahorrar") // ⚠️ NO cambiar (rompe lógica si lo haces)
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

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MetaActivity::class.java))
            finish()
        }
    }
}