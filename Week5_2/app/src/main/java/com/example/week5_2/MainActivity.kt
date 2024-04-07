package com.example.week5_2

import android.graphics.Color
import android.graphics.Color.*
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import android.widget.TextView as TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv1 : TextView
        var tv2 : TextView
        var tv3 : TextView

        tv1 = findViewById<TextView>(R.id.textView1)
        tv2 = findViewById<TextView>(R.id.textView2)
        tv3 = findViewById<TextView>(R.id.textView3)

        tv1.setText("안녕하세요?")
//        tv1.setTextColor(Color.RED)
        tv1.setTextColor(ContextCompat.getColor(this, R.color.BLUE))
        val textColor = ContextCompat.getColor(this, R.color.BLUE)
        Log.d("MainActivity", "TextColor: $tv1.getColor") // 로그로 색상 값 확인
        tv1.setTextColor(textColor)

        tv1.setTextSize(45.0f)

        tv2.setTextSize(45.0f)
        tv2.setTypeface(android.graphics.Typeface.SERIF,
                        android.graphics.Typeface.BOLD_ITALIC)
        tv3.setText("가나다라마바사아자차카타파하가나다라마바사아자차카타파하")
        tv3.setTextSize(20.0f)
        tv3.setSingleLine()
    }
}