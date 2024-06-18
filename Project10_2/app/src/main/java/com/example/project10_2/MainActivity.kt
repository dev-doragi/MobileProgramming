package com.example.project10_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
        title="던전밥 일러스트 선호도 투표"

        var voteCount = IntArray(9) // 사진별 투표 숫자를 저장할 배열
        for (i in 0..8)
            voteCount[i] = 0
        var img = arrayOfNulls<ImageView>(9)
        var imgID = arrayOf(R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9)

        var imgName = arrayOf("늦잠잔 라이오스", "구경하는 칠첵", "장보는 마르실", "산책하는 파린", "즐거운 저녁시간", "그린 드래곤 토벌, 센시", "지각한 이유1", "지각한 이유2", "모험 시작!")

        for(i in imgID.indices) {
            img[i] = findViewById<ImageView>(imgID[i])
            // 이미지 뷰를 클릭할 때마다 해당 이미지 순번의 이미지 이름과 현재 투표수를 토스트로 띄운다.
            img[i]!!.setOnClickListener {
                if (voteCount[i] < 5)
                    voteCount[i]++
                else
                    voteCount[i] = 5
                Toast.makeText(applicationContext, imgName[i] + ": 총 " + voteCount[i] + " 표", Toast.LENGTH_SHORT).show()
            }
        }

        // 결과 출력 버튼, intent를 통해 ResultActivity에 투표 수와 이미지 이름을 전달한다.
        var btnResult = findViewById<Button>(R.id.btnResult)
        btnResult.setOnClickListener {
            var intent = Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("VoteCount", voteCount)
            intent.putExtra("ImageName", imgName)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}