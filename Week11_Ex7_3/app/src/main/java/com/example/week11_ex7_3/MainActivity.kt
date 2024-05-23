package com.example.week11_ex7_3

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
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
        title = "토스트 연습"

        var button1 = findViewById<Button>(R.id.button1)

        // 토스트는 화면에 잠깐 생겼다가 사라지는 알림이다.
        button1.setOnClickListener {
            var tMsg = Toast.makeText(applicationContext, "이건 제 잔상입니다만?", Toast.LENGTH_SHORT)

            var display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            var xOffset = (Math.random() * display.width).toInt()
            var yOffset = (Math.random() * display.height).toInt()

            tMsg.setGravity(Gravity.TOP or Gravity.LEFT, xOffset, yOffset)
            tMsg.show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
