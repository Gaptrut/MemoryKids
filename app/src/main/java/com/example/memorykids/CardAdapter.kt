package com.example.memorykids

import android.annotation.SuppressLint

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class CardAdapter(private var cardList: List<Card>, private val context: Context) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var matchedPairsCount = 0
    private var firstCard: Card? = null
    private var secondCard: Card? = null


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage: ImageView =
            itemView.findViewById(R.id.imageCardView)


        // Här kan vi definiera vad ViewHolder ska hålla i
        val imageView: ImageView = itemView.findViewById(R.id.imageCardView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_item,
            parent, false
        )
        return CardViewHolder(view)
    }
    // Här kommer vi att implementera onBindViewHolder()
    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.imageView.setImageResource(card.image)
        if (card.isFlipped) {
            holder.cardImage.setImageResource(card.image)
        } else {
            holder.cardImage.setImageResource(R.drawable.bak)
        }
        // Här kan du sätta bild och andra vyer i din holder baserat på din card
        fun resetGame(context: Context) {
        }
        holder.imageView.setOnClickListener {
            // Här kan du hantera klick på kortet

            if (firstCard != null && secondCard != null) {
                // Om de två korten inte matchar, vänd dem tillbaka
                if (firstCard?.image != secondCard?.image) {
                    firstCard?.isFlipped = false
                    secondCard?.isFlipped = false
                }
                // Nollställ de uppvända korten
                firstCard = null
                secondCard = null
            }

            // Vänd upp det valda kortet
            card.isFlipped = true

            // Kontrollera om detta är det första eller andra kortet som användaren har valt
            if (firstCard == null) {
                firstCard = card
            } else {
                secondCard = card

                val totalPairs = cardList.size / 2

                if (firstCard?.image == secondCard?.image) {
                    // Om de matchar, öka räknaren
                    matchedPairsCount++

                    firstCard?.isMatched = true
                    secondCard?.isMatched = true


                    if (matchedPairsCount == totalPairs) {
                        val mediaPlayer =
                            MediaPlayer.create(holder.cardImage.context, R.raw.yipee)
                        mediaPlayer.start()
                        // Visa en toast-notis
                        Toast.makeText(
                            holder.cardImage.context,
                            "Seger! 🎉En gång till??\uD83D\uDE0A",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Starta NewGameActivity
                        val intent = Intent(context, NewGameActivity::class.java)
                        context.startActivity(intent)

                    }
                }
            }

            fun checkIfGameIsFinished(context: Context) {
                if (cardList.all { it.isMatched }) {
                    // Om alla par har hittats, återställ spelet
                    GlobalScope.launch {
                        delay(1000) // vänta i 1 sekund
                        withContext(Dispatchers.Main) {
                            resetGame(context)
                        }
                    }
                }
            }


            // Uppdatera vyn
            notifyDataSetChanged()
        }
    }



}




