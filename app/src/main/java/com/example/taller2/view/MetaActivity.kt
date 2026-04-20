package com.example.taller2.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityMetaBinding
class MetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMetaBinding
    private var goal = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFacil.setOnClickListener { goal = 3000 }
        binding.btnNormal.setOnClickListener { goal = 5000 }
        binding.btnDificil.setOnClickListener { goal = 10000 }

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("goal", goal)
            startActivity(intent)
        }
    }
}