package com.example.week12_ex_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        var btnRead : Button
        var edtRaw : EditText
        btnRead = findViewById<Button>(R.id.btnRead)
        edtRaw = findViewById<EditText>(R.id.edtRaw)

        // 읽기 메서드
        btnRead.setOnClickListener {
            // 현재 패키지의 리소스를 의미하는 resources, /res/raw 리소스 raw_test를 읽기용으로 오픈
            var inputS = resources.openRawResource(R.raw.raw_test) // file과 다르게 openRawResource 메서드이다. InputStream을 반환한다. (File은 openFileInput 메서드, FileInputStream 반환)
            // 입력 스트림에서 읽을 수 있는 바이트 수를 반환한다. txt라는 배열을 만들고, 크기는 inputS(raw_test.txt)의 데이터 크기가 된다.
            var txt = ByteArray(inputS.available())
            inputS.read(txt)
            edtRaw.setText(txt.toString(Charsets.UTF_8))
            inputS.close()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}