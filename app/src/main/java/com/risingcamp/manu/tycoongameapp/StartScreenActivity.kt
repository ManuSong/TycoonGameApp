package com.risingcamp.manu.tycoongameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.risingcamp.manu.tycoongameapp.databinding.ActivityStartScreenBinding

class StartScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainStartBtn.apply {
            setOnClickListener {
                val intent = Intent(this@StartScreenActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}