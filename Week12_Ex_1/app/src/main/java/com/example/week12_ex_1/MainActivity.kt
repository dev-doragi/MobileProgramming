package com.example.week12_ex_1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)
        title = "파일 읽기 실습"

        var btnRead: Button = findViewById<Button>(R.id.btnRead)
        var btnWrite: Button = findViewById<Button>(R.id.btnWirte)

        // 파일 Write
        btnWrite.setOnClickListener {
            // outFs 파일 아웃풋 스트림 -> 파일 쓰기
            var outFs : FileOutputStream =
                openFileOutput("file.txt", Context.MODE_PRIVATE) // Context.MODE_PRIVATE -> 다른 앱은 접근 불가
            var str = "파일 읽기 실습"
            outFs.write(str.toByteArray()) // str을 바이트 배열로 변환 후 outFs에 쓰기
            outFs.close() // 파일 스트림 닫기
            Toast.makeText(applicationContext, "file.txt가 생성됨", Toast.LENGTH_SHORT).show()
        }

        // 파일을 Read할 때, Read할 파일이 없을 경우 예외처리를 통해 파일 없음을 출력한다.
        btnRead.setOnClickListener {
            try {
                var inFs : FileInputStream = openFileInput("file.txt")
                var txt = ByteArray(30) // file.txt의 바이트 배열을 저장할 변수 txt
                inFs.read(txt) // inFs에서 txt의 크기만큼 데이터를 읽는다.
                var str = txt.toString(Charsets.UTF_8) // UTF-8로 txt를 문자열로 변환하여 str에 저장
                Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
                inFs.close()
            } catch (e : IOException) {
                Toast.makeText(applicationContext, "파일 없음", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}