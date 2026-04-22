package com.example.taller2.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnDoRegister.setOnClickListener {
            val email = binding.etRegEmail.text.toString()
            val password = binding.etRegPass.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (password.length >= 6) {
                    registerUser(email, password)
                } else {
                    Toast.makeText(this, "Mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvBackToLogin.setOnClickListener {
            finish()
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "¡Bienvenido socio!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MetaActivity::class.java))
                    finishAffinity() // Cierra todas las pantallas anteriores
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
