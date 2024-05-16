package com.example.week9_3

import android.os.Bundle
import android.widget.Button
import android.widget.ViewFlipper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)

        title = "뷰 플리퍼 예제"

        var btnPrev : Button
        var btnNext : Button
        var vFlipper : ViewFlipper

        btnPrev = findViewById<Button>(R.id.btnPrev) // 이전 버튼
        btnNext = findViewById<Button>(R.id.btnNext) // 다음 버튼
        vFlipper = findViewById<ViewFlipper>(R.id.viewFlipper1) // 뷰 플리퍼

        btnPrev.setOnClickListener {
            vFlipper.showPrevious()
        }

        btnNext.setOnClickListener {
            vFlipper.showNext()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}