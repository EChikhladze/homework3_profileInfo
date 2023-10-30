package com.example.homework3_profileinfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3_profileinfo.databinding.ActivitySavedBinding

class SavedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_saved) // Set the layout for this activity

        binding.tvEmail.text = intent.getStringExtra("email")
        binding.tvUsername.text = intent.getStringExtra("username")
        binding.tvFirstName.text = intent.getStringExtra("firstName")
        binding.tvLastName.text = intent.getStringExtra("lastName")
        binding.tvAge.text = intent.getStringExtra("age")

        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
