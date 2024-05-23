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

        btnRead.setOnClickListener {
            var inputS = resources.openRawResource(R.raw.raw_test)
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