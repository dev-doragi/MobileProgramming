package com.example.week7_project5_1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)

        val params = LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT,
                     LinearLayout.LayoutParams.MATCH_PARENT)

        val baseLayout = LinearLayout(this)
        baseLayout.orientation = LinearLayout.VERTICAL
        baseLayout.setBackgroundColor(Color.rgb(0, 255, 0))
        setContentView(baseLayout, params)

        val btn = Button(this)
        btn.text = "버튼입니다."
        btn.setBackgroundColor(Color.MAGENTA)
        baseLayout.addView(btn)

        btn.setOnClickListener {
            Toast.makeText(applicationContext,
                "코드로 생성한 버튼입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}