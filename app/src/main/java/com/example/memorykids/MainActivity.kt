package com.example.memorykids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.apelsin, R.drawable.banan,
            R.drawable.broccoli, R.drawable.citron, R.drawable.gullok,
            R.drawable.kiwi, R.drawable.potatis, R.drawable.lime,
            R.drawable.vitlok, R.drawable.apple
        )

        val difficulty = intent.getStringExtra("difficulty")
        val numPairs = when (difficulty) {
            "Lätt" -> 4
            "Mellan" -> 6
            "Svår" -> 10
            else -> 4 // default till "Lätt" om något går fel
        }
        val numColumns = when (difficulty) {
            "Lätt" -> 2
            "Mellan" -> 3
            "Svår" -> 5
            else -> 2 // default till 2 kolumner om något går fel

        }
        val gameImages = images.take(numPairs).flatMap { listOf(it, it) }.shuffled()

        val myCardList = gameImages.map { image -> Card(image, false, false) }

        //Hitta RecyclerView
        val myRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        myRecyclerView.layoutManager = GridLayoutManager(this, numColumns)


        // Skapa din CardAdapter och koppla den till RecyclerView
        myRecyclerView.adapter = CardAdapter(myCardList, this)
    }
}

