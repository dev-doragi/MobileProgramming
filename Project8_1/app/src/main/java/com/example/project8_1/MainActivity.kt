package com.example.project8_1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var dp : DatePicker
    lateinit var edtDiary : EditText
    lateinit var btnWrite : Button
    lateinit var fileName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)
        title = "간단 일기장"

        dp = findViewById<DatePicker>(R.id.datePicker1)
        edtDiary = findViewById<EditText>(R.id.edtDiary)
        btnWrite = findViewById<Button>(R.id.btnWrite)

        var cal = Calendar.getInstance() // 현재 시간 정보를 저장한 Calendar 객체를 반환
        var cYear = cal.get(Calendar.YEAR) // int 형태로 날짜를 변수에 저장
        var cMonth = cal.get(Calendar.MONTH)
        var cDay = cal.get(Calendar.DAY_OF_MONTH)

        dp.init(cYear, cMonth, cDay) { view, year, monthOfYear, dayOfMonth ->
            fileName = (Integer.toString(year) + "_"
                    + Integer.toString(monthOfYear + 1) + "_"
                    + Integer.toString(dayOfMonth) + ".txt")
            edtDiary.setText(readDiary(fileName)) // init 이후 바로 파일 스트림 생성
            btnWrite.isEnabled = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 일기 쓰기
        btnWrite.setOnClickListener {
            var outFs = openFileOutput(fileName, Context.MODE_PRIVATE)
            var str = edtDiary.text.toString() // 입력한 텍스트를 String으로 변환 후 str에 저장, EditText는 'editible!'이라는 타입이다.
            outFs.write(str.toByteArray()) // 문자열을 바이트 배열로 다시 변환하여 아웃풋 스트림(파일)에 쓰기
            outFs.close()
            Toast.makeText(applicationContext, "$fileName 이 저장됨.",
                Toast.LENGTH_SHORT).show()
        }
    }

    // 일기 읽기, 일기가 없으면 일기 없음을 출력하고 버튼이 새로 저장으로 바뀜 바이트 배열을 읽어 문자열로 반환하는 함수
    fun readDiary(fName: String) : String? {
        var diaryStr : String? = null // 일기에 적힌 문자를 저장할 변수
        var inFs : FileInputStream // 인풋 스트림

        try {
            inFs = openFileInput(fName) // fName으로 파일 읽기
            var txt = ByteArray(inFs.available()) // 바이트 배열을 저장할 txt
            inFs.read(txt) // 바이트 배열 값 read
            inFs.close()
            // 읽어온 txt를 문자열로 변경 후 trim()으로 앞뒤의 공백을 제거한다.
            diaryStr = txt.toString(Charsets.UTF_8).trim()
            btnWrite.text = "수정하기"
        } catch (e : IOException) {
            edtDiary.hint = "일기 없음"
            btnWrite.text = "새로 저장"
        }
        return diaryStr // 읽은 파일 내용을 저장한 diaryStr을 반환
    }
}