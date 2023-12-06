package com.example.memorykids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class NewGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)





        val easyButton = findViewById<Button>(R.id.buttonEasy)
        easyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Lätt")
            startActivity(intent)
        }

        val mediumButton = findViewById<Button>(R.id.buttonMedium)
        mediumButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Mellan")
            startActivity(intent)
        }

        val hardButton = findViewById<Button>(R.id.buttonHard)
        hardButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("difficulty", "Svår")
            startActivity(intent)
        }

        val exitButton: ImageButton = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            Toast.makeText(this, "Hejdå! 👋😊", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}