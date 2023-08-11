package com.example.testwearosfelps

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper
import com.bumptech.glide.Glide
import com.example.testwearosfelps.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var resetClick = 0
    private val listAliens = listOf(
        R.drawable.image_removebg_preview_1_,
        R.drawable.image_removebg_preview_2_,
        R.drawable.image_removebg_preview_3_,
        R.drawable.image_removebg_preview_4_,
        R.drawable.image_removebg_preview_5_,
        R.drawable.image_removebg_preview_6_,
        R.drawable.image_removebg_preview_7_,
        R.drawable.image_removebg_preview_8_,
        R.drawable.image_removebg_preview_9_,
        R.drawable.image_removebg_preview_10_
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initViews()
        initPlaySound()

        setContentView(binding.root)
    }

    private fun initViews() {
        clickNextAlien()
    }


    private fun clickNextAlien() {
        binding.apply {
            imageMain.setOnClickListener {
                resetClick++
                if (resetClick >= 11) {
                    noBatterySound()
                    imageMain.setImageResource(R.drawable.image_photoroom_png_photoroom)
                    resetClick = 0
                } else {
                    initGif()
                    android.os.Handler(Looper.getMainLooper()).postDelayed({
                        imageMain.setImageResource(randomAlien())
                    }, 1000)
                }
                mediaPlayer.start()
            }
        }
    }

    private fun initGif() {
        Glide.with(applicationContext)
            .asGif()
            .load(R.drawable.omnitrix_strange)
            .into(binding.imageMain)
        selectAlienSound()
    }

    private fun initPlaySound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.som_do_omnitrix)
        mediaPlayer.start()
    }

    private fun selectAlienSound() {
        mediaPlayer.release()
        mediaPlayer = MediaPlayer.create(this, R.raw.omnitrix_select_alien)
    }

    private fun noBatterySound() {
        mediaPlayer.release()
        mediaPlayer = MediaPlayer.create(this, R.raw.omnitrix_no_battery)
    }

    private fun randomAlien(): Int {
        val random = Random.nextInt(listAliens.size)
        return listAliens[random]
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}