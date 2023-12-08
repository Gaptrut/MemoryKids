package com.example.memorykids

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mediaPlayer = MediaPlayer.create(this, R.raw.splashsong)
        val maxVolume = 100
        mediaPlayer?.setVolume(maxVolume.toFloat(), maxVolume.toFloat())
        mediaPlayer?.start()



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, NewGameActivity::class.java)
            startActivity(intent)
            finish()  // Stäng splash screen aktiviteten
        }, 5000) // Vänta i 3000 millisekunder, dvs 3 sekunder
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

}