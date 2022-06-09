package com.risingcamp.manu.tycoongameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.risingcamp.manu.tycoongameapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.raw.samanco_eat).into(binding.splashImg)

        handler.postDelayed({
            val intent = Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
        }, 2000)


    }
}