package com.example.project10_2

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)
        title="투표 결과"
        var intent = intent
        var voteResult = intent.getIntArrayExtra("VoteCount")

        var imgName = intent.getStringArrayExtra("ImageName")
        // imgName을 명확한 스트링으로 지정하도록 제네릭 사용
        // var imgName: Array<String> = intent.getStringArrayExtra("ImageName")?: emptyArray() //

        // 9개의 이미지 배열
        var tv = arrayOfNulls<TextView>(imgName!!.size)

        // 9개의 평점 배열
        var rbar = arrayOfNulls<RatingBar>(imgName.size)

        // 각 ID를 저장한 배열
        var tvID = arrayOf(R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9)
        var rbarID = arrayOf(R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9)

        // 위젯 배열에 각 위젯들을 id별로 할당한다
        for (i in voteResult!!.indices) {
            tv[i] = findViewById<TextView>(tvID[i])
            rbar[i] = findViewById<RatingBar>(rbarID[i])
        }

        // 각 위젯을 설정한다.
        for (i in voteResult!!.indices) {
            tv[i]!!.setText(imgName[i])
            rbar[i]!!.setRating(voteResult[i].toFloat())
        }

        // 돌아가기 버튼을 누르면 finish()를 통해 Main으로 돌아간다.
        var btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
    }
}