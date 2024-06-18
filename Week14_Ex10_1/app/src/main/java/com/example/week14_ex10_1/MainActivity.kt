package com.example.week14_ex10_1

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)

        var rating1 : RatingBar
        var rating2 : RatingBar
        var rating3 : RatingBar
        var btnInc : Button
        var btnDec : Button

        rating1 = findViewById<RatingBar>(R.id.ratingBar1)
        rating2 = findViewById<RatingBar>(R.id.ratingBar2)
        rating3 = findViewById<RatingBar>(R.id.ratingBar3)
        btnInc = findViewById<Button>(R.id.btnInc)
        btnDec = findViewById<Button>(R.id.btnDec)

        // rating은 현재 매겨진 별점, stepSize는 별점이 변경되는 최소 단위
        btnInc.setOnClickListener {
            rating1.rating = rating1.rating + rating1.stepSize
            rating2.rating = rating2.rating + rating2.stepSize
            rating3.rating = rating3.rating + rating3.stepSize
        }

        btnDec.setOnClickListener {
            rating1.rating = rating1.rating - rating1.stepSize
            rating2.rating = rating2.rating - rating2.stepSize
            rating3.rating = rating3.rating - rating3.stepSize
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}