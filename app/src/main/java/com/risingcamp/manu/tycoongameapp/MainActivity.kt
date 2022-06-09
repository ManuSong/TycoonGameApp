package com.risingcamp.manu.tycoongameapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import com.risingcamp.manu.tycoongameapp.databinding.ActivityMainBinding
import java.util.concurrent.DelayQueue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var time = 0
    var framArray = ArrayList<ImageView>()
    var makeArray = ArrayList<ImageView>()
    var finishArray = ArrayList<ImageView>()
    var fireArray = ArrayList<ImageView>()
    var readArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    private var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = 0
        time = 0

        binding.gameScore.text = "Scroe : " + score
        binding.gameTimer.text = "Timer : " + time

        framArray = arrayListOf(
            binding.bungFrame1, binding.bungFrame2, binding.bungFrame3, binding.bungFrame4, binding.bungFrame5, binding.bungFrame6, binding.bungFrame7, binding.bungFrame8, binding.bungFrame9
        )

        makeArray = arrayListOf(
            binding.bungMake1, binding.bungMake2, binding.bungMake3, binding.bungMake4, binding.bungMake5, binding.bungMake6, binding.bungMake7, binding.bungMake8, binding.bungMake9
        )

        finishArray = arrayListOf(
            binding.bungFinish1, binding.bungFinish2, binding.bungFinish3, binding.bungFinish4, binding.bungFinish5, binding.bungFinish6, binding.bungFinish7, binding.bungFinish8, binding.bungFinish9
        )

        fireArray = arrayListOf(
            binding.bungFire1, binding.bungFire2, binding.bungFire3, binding.bungFire4, binding.bungFire5, binding.bungFire6, binding.bungFire7, binding.bungFire8, binding.bungFire9
        )

        readArray = arrayListOf(
            binding.bungReady1, binding.bungReady2, binding.bungReady3, binding.bungReady4, binding.bungReady5, binding.bungReady6, binding.bungReady7, binding.bungReady8, binding.bungReady9
        )

        for ((index, finish) in finishArray.withIndex()) {
            finish.visibility = View.INVISIBLE

            finish.setOnClickListener {
                increaseScroe(binding.gameScore)
                finishArray[index].visibility = View.INVISIBLE
            }
        }

        for (fire in fireArray) {
            fire.visibility = View.INVISIBLE

            fire.setOnClickListener {
                fire.visibility = View.INVISIBLE
                decreaseScore(binding.gameScore)
            }
        }

        for (ready in readArray) {
            ready.visibility = View.INVISIBLE
            ready.setOnClickListener {
                decreaseScore(binding.gameScore)
                ready.visibility = View.INVISIBLE
            }

        }

        for (make in makeArray) {

            make.visibility = View.INVISIBLE

            make.setOnClickListener {
                make.visibility = View.INVISIBLE
                decreaseScore(binding.gameScore)
            }

        }

        object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.gameTimer.text = "Time : " + millisUntilFinished / 1000

            }

            override fun onFinish() {
                binding.gameTimer.text = "Time Over"
                mediaPlayer?.stop()
                for (make in makeArray) {
                    make.visibility = View.INVISIBLE
                }

                for (ready in readArray) {
                    ready.visibility = View.INVISIBLE
                }

                for (fire in fireArray) {
                    fire.visibility = View.INVISIBLE
                }

                for (finish in finishArray) {
                    finish.visibility = View.INVISIBLE
                }

                val intent = Intent(this@MainActivity, ReslutActivity::class.java)
                intent.putExtra("score", binding.gameScore.text)
                intent.putExtra("time", binding.gameTimer.text)
                startActivity(intent)
            }

        }.start()



        Thread(){
            handler.post {




// 클릭을 눌러서 사라져도 계속 진행되는 오류 발생 이걸 어떻게 해결해야될까 고민해보자~! 각각? for 구문에 넣어줘야 될까? 아니면 그냥 If 구문으로 만들 수 있을까 고민해보자
                for ((index, frame) in framArray.withIndex()){

                    frame.setOnClickListener {


                        makeArray[index].visibility = View.VISIBLE



                        handler.postDelayed({
                            if(makeArray[index].visibility == View.VISIBLE){
                            makeArray[index].visibility = View.INVISIBLE
                            readArray[index].visibility = View.VISIBLE
                        }}, 3000)


                        handler.postDelayed({
                            if (readArray[index].visibility == View.VISIBLE){
                            finishArray[index].visibility = View.VISIBLE
                            readArray[index].visibility = View.INVISIBLE
                        }}, 6000)

                        handler.postDelayed({
                            if (finishArray[index].visibility == View.VISIBLE){
                            finishArray[index].visibility = View.INVISIBLE
                            fireArray[index].visibility = View.VISIBLE
                        }}, 10000)




                    } }

            }
            }.start()

        mediaPlayer = MediaPlayer.create(this, R.raw.game_bgm)



        Thread(){
            handler.post {
                binding.pauseBtn.visibility = View.INVISIBLE
                binding.startBtn.setOnClickListener {
                    mediaPlayer?.start()
                    binding.startBtn.visibility = View.INVISIBLE
                    binding.pauseBtn.visibility = View.VISIBLE
                }

                binding.pauseBtn.setOnClickListener {
                    mediaPlayer?.pause()
                    binding.startBtn.visibility = View.VISIBLE
                    binding.pauseBtn.visibility = View.INVISIBLE
                }
            }
        }.start()
        }


    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer = null
    }

    fun increaseScroe(view: View) {
        score++

        binding.gameScore.text = "Score : " + score

    }

    fun decreaseScore(view: View) {
        score--

        binding.gameScore.text = "Score : " + score
    }


    }
