package com.example.taller2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityMainBinding
import com.example.taller2.view.MetaActivity
import com.example.taller2.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val goal = intent.getIntExtra("goal", 5000)

        viewModel.gameState.observe(this) { state ->
            binding.txtMoney.text = "Dinero: ${state.currentMoney}"
            binding.txtTurn.text = "Turno: ${state.currentTurn}"
            binding.txtResult.text = state.result
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MetaActivity::class.java)
            startActivity(intent)
            finish() // Cierra el juego para no dejarlo en segundo plano
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
