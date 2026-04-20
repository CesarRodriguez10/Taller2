package com.example.taller2.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val money = intent.getIntExtra("money", 0)
        val goal = intent.getIntExtra("goal", 0)

        binding.txtFinal.text = "Dinero final: $money / Meta: $goal"

        binding.btnReplay.setOnClickListener {
            startActivity(Intent(this, MetaActivity::class.java))
        }
    }
}