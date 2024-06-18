package com.example.project10_3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
        title="액티비티 테스트 예제"
        android.util.Log.i("액티비티 테스트", "onCreate()")

        var btnDial = findViewById<Button>(R.id.btnDial)
        btnDial.setOnClickListener {
            var uri = Uri.parse("tel:010-9948-6558")
            var intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        var btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        android.util.Log.i("액티비티 테스트", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        android.util.Log.i("액티비티 테스트", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        android.util.Log.i("액티비티 테스트", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        android.util.Log.i("액티비티 테스트", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.i("액티비티 테스트", "onDestroy()")
    }
}