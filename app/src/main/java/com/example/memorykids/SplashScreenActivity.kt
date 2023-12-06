package com.example.memorykids

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, NewGameActivity::class.java)
            startActivity(intent)
            finish()  // Stäng splash screen aktiviteten
        }, 3000) // Vänta i 3000 millisekunder, dvs 3 sekunder
    }

}