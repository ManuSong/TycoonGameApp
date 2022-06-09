package com.risingcamp.manu.tycoongameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.risingcamp.manu.tycoongameapp.databinding.ActivityReslutBinding

class ReslutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReslutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReslutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalScore = intent.getStringExtra("score").toString()

        binding.reslutScore.text = totalScore

        binding.restartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.gotoHomeBtn.setOnClickListener {
            val intent = Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
        }

    }
}