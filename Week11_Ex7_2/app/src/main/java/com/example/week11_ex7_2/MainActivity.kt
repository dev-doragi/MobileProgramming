package com.example.week11_ex7_2

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var baseLayout : LinearLayout
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)
        title = "배경색 바꾸기(컨텍스트 메뉴)"

        baseLayout = findViewById<LinearLayout>(R.id.baseLayout) as LinearLayout
        button1 = findViewById<Button>(R.id.button1) as Button
        registerForContextMenu(button1)
        button2 = findViewById<Button>(R.id.button2) as Button
        registerForContextMenu(button2)
        button3 = findViewById<Button>(R.id.button3) as Button
        registerForContextMenu(button3)
        button4 = findViewById<Button>(R.id.button4) as Button

        button4.setOnClickListener {
            baseLayout.setBackgroundColor(Color.WHITE)
            button1.setBackgroundColor(Color.parseColor("#FFD5D6D6"))
            button2.setBackgroundColor(Color.parseColor("#FFD5D6D6"))
            button3.setBackgroundColor(Color.parseColor("#FFD5D6D6"))
            button4.setBackgroundColor(Color.parseColor("#FFD5D6D6"))
            button1.rotation = 0f
            button1.scaleX = 1f
            button2.rotation = 0f
            button2.scaleX = 1f
            button3.rotation = 0f
            button3.scaleX = 1f
            button4.rotation = 0f
            button4.scaleX = 1f
            false
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.baseLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v === button1) {
            menu!!.add(0,1,0, "버튼색 (빨강)")
            menu!!.add(0,2,0, "버튼색 (초록)")
            menu!!.add(0,3,0, "버튼색 (파랑)")
        }

        if (v === button2) {
            menu!!.add(0,4,0, "배경색 (빨강)")
            menu!!.add(0,5,0, "배경색 (초록)")
            menu!!.add(0,6,0, "배경색 (파랑)")
        }

        if (v === button3) {
            menu!!.add(0,7,0, "버튼 45도 회전")
            menu!!.add(0,8,0, "버튼 2배 확대")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                button1.setBackgroundColor(Color.RED)
                button2.setBackgroundColor(Color.RED)
                button3.setBackgroundColor(Color.RED)
                button4.setBackgroundColor(Color.RED)
                return true
            }
            2 -> {
                button1.setBackgroundColor(Color.GREEN)
                button2.setBackgroundColor(Color.GREEN)
                button3.setBackgroundColor(Color.GREEN)
                button4.setBackgroundColor(Color.GREEN)
                return true
            }
            3 -> {
                button1.setBackgroundColor(Color.BLUE)
                button2.setBackgroundColor(Color.BLUE)
                button3.setBackgroundColor(Color.BLUE)
                button4.setBackgroundColor(Color.BLUE)
                return true
            }
            4 -> {
                baseLayout.setBackgroundColor(Color.RED)
                return true
            }
            5 -> {
                baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }
            6 -> {
                baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            7 -> {
                button1.rotation += 45f
                button2.rotation += 45f
                button3.rotation += 45f
                button4.rotation += 45f
                return true
            }
            8 -> {
                button1.scaleX *= 2f
                button2.scaleX *= 2f
                button3.scaleX *= 2f
                button4.scaleX *= 2f
                return true
            }
        }
        return false
    }
}