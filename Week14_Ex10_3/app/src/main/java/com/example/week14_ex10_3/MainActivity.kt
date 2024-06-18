package com.example.week14_ex10_3

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
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
        title="암시적 인텐트 예제"

        var btnDial = findViewById<Button>(R.id.btnDial)
        var btnWeb = findViewById<Button>(R.id.btnWeb)
        var btnGoogle = findViewById<Button>(R.id.btnGoogle)
        var btnSearch = findViewById<Button>(R.id.btnSearch)
        var btnSms = findViewById<Button>(R.id.btnSms)
        var btnPhoto = findViewById<Button>(R.id.btnPhoto)

        btnDial.setOnClickListener {
            var uri = Uri.parse("tel:010-9948-6558")
            var intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        btnWeb.setOnClickListener {
            var uri = Uri.parse("https://www.google.com/")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnGoogle.setOnClickListener {
            var uri = Uri.parse("https://maps.app.goo.gl/LuZwuvUT3pTEzS4u8")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            var intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "던전밥")
            startActivity(intent)
        }

        btnSms.setOnClickListener {
            var intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra("sms_body", "안녕하세요?")
            intent.data = Uri.parse("smsto:" + Uri.encode("010-9948-6558"))
            startActivity(intent)
        }

        btnPhoto.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}