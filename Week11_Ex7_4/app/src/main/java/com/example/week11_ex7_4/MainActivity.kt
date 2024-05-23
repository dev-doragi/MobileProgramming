package com.example.week11_ex7_4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        title = "목록 대화상자"

        var button1 = findViewById<Button>(R.id.button1)

        button1.setOnClickListener {

            // 라디오 버튼 다이얼로그
            var versionArray = arrayOf("펄프픽션", "쇼생크 탈출", "인셉션", "듄")
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("좋아하는 영화는?")
            dlg.setIcon(R.drawable.onion_black)
            dlg.setSingleChoiceItems(versionArray, 0) { dialog, which ->
                button1.text = versionArray[which]
            }
            dlg.setPositiveButton("닫기", null)

            dlg.show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}