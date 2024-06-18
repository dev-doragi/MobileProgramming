package com.example.week14_ex10_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        title="Second 액티비티"

        var inIntent = intent
        // MainActivity에서 보낸 두가지 정수 값을 getExtra로 받아서 더하기
        var hapValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0)

        // 결과 값 반환 메소드
        var btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            // 전송할 outIntent 초기화
            var outIntent = Intent(applicationContext, MainActivity::class.java)
            // 합 결과를 outIntent에 putExtra
            outIntent.putExtra("Hap", hapValue)
            // ResultCode를 RESULT_OK로 지정, outIntent를 담아 전송
            setResult(Activity.RESULT_OK, outIntent)
            finish() // finish를 해야 돌아감!
        }
    }
}