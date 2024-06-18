package com.example.week11_ex7_1

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var baseLayout : LinearLayout
    lateinit var button1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)
        title = "배경색 바꾸기"

        baseLayout = findViewById<LinearLayout>(R.id.baseLayout)
        button1 = findViewById<Button>(R.id.button1)

        // 원상복귀 버튼
        button1.setOnClickListener {
            baseLayout.setBackgroundColor(Color.WHITE)
            button1.rotation = 0f
            button1.scaleX = 1f
            button1.scaleY = 1f
        }

        // 액션 바 밑으로 레이아웃을 정렬시켜준다.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.baseLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // 옵션 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
//        var mInflater = menuInflater // 메뉴 인플레이터 생성
//        mInflater.inflate(R.menu.menu1, menu) // 메뉴 인플레이터에 menu1 xml 파일을 등록

        menu!!.add(0, 1, 0, "배경색 (빨강)")
        menu!!.add(0, 2, 0, "배경색 (초록)")
        menu!!.add(0, 3, 0, "배경색 (파랑)")

        var sMenu : SubMenu = menu.addSubMenu("버튼 변경 >>")
        sMenu.add(0, 4, 0, "버튼 45도 회전")
        sMenu.add(0, 5, 0, "버튼 2배 확대")

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                baseLayout.setBackgroundColor(Color.RED)
                return true
            }
            2 -> {
                baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }
            3 -> {
                baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            4 -> {
                button1.rotation += 45f
                return true
            }
            5 -> {
                button1.scaleX *= 2f
                return true
            }
        }

//        when (item.itemId) {
//            R.id.itemRed -> {
//                baseLayout.setBackgroundColor(Color.RED)
//                return true
//            }
//            R.id.itemGreen -> {
//                baseLayout.setBackgroundColor(Color.GREEN)
//                return true
//            }
//            R.id.itemBlue -> {
//                baseLayout.setBackgroundColor(Color.BLUE)
//                return true
//            }
//            R.id.subRotate -> {
//                button1.rotation += 45f
//                return true
//            }
//            R.id.subSize -> {
//                button1.scaleX *= 2f
//                button1.scaleY *= 2f
//                return true
//            }
//        }
        return false
    }

}