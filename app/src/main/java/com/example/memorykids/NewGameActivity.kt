package com.example.memorykids

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class NewGameActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        mediaPlayer = MediaPlayer.create(this, R.raw.baaaa)
        val maxVolume = 100  //Procentandel. 100 = 100%, 50 = 50%, osv.
        mediaPlayer?.setVolume(maxVolume.toFloat(), maxVolume.toFloat())
        mediaPlayer?.start()


        val easyButton = findViewById<Button>(R.id.buttonEasy)
        easyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Lätt")
            startActivity(intent)
        }

        val mediumButton = findViewById<Button>(R.id.buttonMedium)
        mediumButton.setOnClickListener {
            mediaPlayer?.stop()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Mellan")
            startActivity(intent)
        }

        val hardButton = findViewById<Button>(R.id.buttonHard)
        hardButton.setOnClickListener {
            mediaPlayer?.stop()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Svår")
            startActivity(intent)
        }

        val exitButton: ImageButton = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            mediaPlayer?.stop()
            Toast.makeText(this, "Hejdå! 👋😊",
                Toast.LENGTH_SHORT).show()
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            startActivity(homeIntent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Släpp mediaPlayer när aktiviteten förstörs
        mediaPlayer?.release()
    }
}