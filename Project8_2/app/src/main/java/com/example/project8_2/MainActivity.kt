package com.example.project8_2

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var btnPrev : Button
    lateinit var btnNext : Button
    lateinit var myPicture : myPictureView
    var curNum : Int = 0
    var imageFiles : Array<File>? = null
    lateinit var imageFname : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)
        title = "간단 이미지 뷰어"

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        myPicture = findViewById<myPictureView>(R.id.myPictureView1)

        imageFiles = File(Environment.getExternalStorageDirectory().absolutePath + "/Pictures").listFiles()
        // imageFname = imageFiles!![0]은 /Pictures 디렉토리 내에 있는 .thumbnails 디렉토리이다.
        imageFname = imageFiles!![1].toString() // imageFiles배열의 1번째 사진의 이름을 추출하고 문자열로 변환하여 imageFname에 저장
        myPicture.imagePath = imageFname

        btnPrev.setOnClickListener {
            if (curNum <= 1) {
                Toast.makeText(applicationContext, "첫번째 그림입니다.", Toast.LENGTH_SHORT).show()
            } else {
                curNum--
                imageFname = imageFiles!![curNum].toString() // 현재 curNum을 인덱스로 imageFiles
                myPicture.imagePath = imageFname
                myPicture.invalidate()
            }
        }

        btnNext.setOnClickListener {
            // if (curNum >= 9) { //index 값을 그림의 수 만큼 걸어서, 한번 더 누르면 종료!
            if (curNum >= imageFiles!!.size -1) { // imageFiles 배열의 크기 - 1 = 배열의 마지막 인덱스
                Toast.makeText(applicationContext, "마지막 그림입니다.", Toast.LENGTH_SHORT).show()
            } else {
                curNum++
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath = imageFname
                myPicture.invalidate()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}