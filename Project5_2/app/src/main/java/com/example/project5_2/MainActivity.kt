package com.example.project5_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // lateinit은 지연초기화를 지원하여, lateinit으로 선언한 변수는 선언 시점이 아닌, 나중에 초기화될 수 있다.
    // internal 접근 제한자는 JAVA의 default와 비슷한 개념으로, 같은 모듈 내에서만 접근이 가능하게 제한한다.
    // var(가변변수, 값 할당 후 변경 가능), val(불변변수, 값 할당 후 변경 불가능)
    lateinit internal var edit1 : EditText
    lateinit internal var edit2 : EditText
    lateinit internal var btnAdd : Button
    lateinit internal var btnSub : Button
    lateinit internal var btnMul : Button
    lateinit internal var btnDiv : Button
    lateinit internal var textResult : TextView
    lateinit internal var num1 : String
    lateinit internal var num2 : String
    internal var result : Int? = null // null로 선언하기 위해서는 ?를 붙인다.
    internal var numButtons = ArrayList<Button>(10)
    internal var numBtnIDs = arrayOf(R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4, R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9)
    internal var I : Int = 0 // 증가값 용도
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)

        title = "테이블레이아웃 계산기"

        edit1 = findViewById<EditText>(R.id.Edit1)
        edit2 = findViewById<EditText>(R.id.Edit2)
        btnAdd = findViewById<Button>(R.id.BtnAdd)
        btnSub = findViewById<Button>(R.id.BtnSub)
        btnMul = findViewById<Button>(R.id.BtnMul)
        btnDiv = findViewById<Button>(R.id.BtnDiv)
        textResult = findViewById<TextView>(R.id.TextResult)

        btnAdd.setOnTouchListener { view, motionEvent ->
            // 사용자가 입력한 내용은 text 속성에 저장,
            // eidt1.text는 Editable 타입의 객체를 반환하고, 이를 문자열로 변환하여 num1에 저장한다.
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            result = Integer.parseInt(num1) + Integer.parseInt(num2)
            textResult.text = "계산 결과: " + result.toString()
            false
        }

        btnSub.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            result = Integer.parseInt(num1) - Integer.parseInt(num2)
            textResult.text = "계산 결과: " + result.toString()
            false
        }

        btnMul.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            result = Integer.parseInt(num1) * Integer.parseInt(num2)
            textResult.text = "계산 결과: " + result.toString()
            false
        }

        btnDiv.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            result = Integer.parseInt(num1) / Integer.parseInt(num2)
            textResult.text = "계산 결과: " + result.toString()
            false
        }

        // numBtnIDs는 숫자 버튼들의 리소스 ID 배열. [R.id.BtnNum0, R.id.BtnNum1, ..., R.id.BtnNum9]와 같이 숫자 버튼들의 ID가 배열로 저장된다.
        for (i in 0..9 step 1) {
            numButtons.add(findViewById<Button>(numBtnIDs[i]))
        }


        for (i in 0..numBtnIDs.size-1 step 1) {
            numButtons[i].setOnClickListener {
                if (edit1.isFocused == true) { // 숫자 입력1에 포커스 될 때,
                    num1 = edit1.text.toString() + numButtons[i].getText().toString()
                    edit1.setText(num1)
                } else if (edit2.isFocused == true) { // 숫자 입력2에 포커스 될 때,
                    num2 = edit2.text.toString() + numButtons[i].getText().toString()
                    edit2.setText(num2)
                } else { // 아무 곳에도 포커스를 안하고 있을 때,
                    Toast.makeText(applicationContext, "먼저 에디트 텍스트를 선택하세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}