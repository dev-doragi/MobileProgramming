package com.example.week9_2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // 레이아웃을 화면에 표시
        getSupportActionBar()?.setDisplayShowHomeEnabled(true) // 앱 바에 홈 아이콘을 표시하도록 설정
        getSupportActionBar()?.setIcon(R.drawable.onion_black) // 앱 바에 사용할 아이콘을 설정

        title = "아이템 찾기"

        // 자동완성에 쓰일 items 배열
        var items = arrayOf("MHW:World", "MHW:Iceborne", "MHR:RIse", "MHR:Sunbreak", "Love", "Loss", "Lost")

        // ArrayAdapter: 배열과 어댑터 뷰를 연결하는 클래스, 배열은 어댑터 뷰가 화면으로 뿌릴 자료들의 집합, 배열에 문자열이 들어 있으면 어댑터 뷰는 텍스트뷰를 써서 이것들을 보여준다.
        var adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        // ArrayAdapter(context: 위치, 디자인-안드로이드 양식을 그대로 쓰겠다, 데이터(배열))

        // 자동완성 뷰
        var auto = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView1)
        auto.setAdapter(adapter) // 어댑터뷰(auto)와 어댑터(adapter)를 연결하는 setAdapter 메소드

        // 다중 자동완성 뷰
        var multi = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView1)
        var token = MultiAutoCompleteTextView.CommaTokenizer() // 토크나이저 생성
        multi.setTokenizer(token) // MultiAutoCompleteTextView에서 쉼표를 기준으로 문자열을 분리하여 토큰으로 취급하게 설정
        multi.setAdapter(adapter) // 다중 자동 완성 기능 활성화

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}