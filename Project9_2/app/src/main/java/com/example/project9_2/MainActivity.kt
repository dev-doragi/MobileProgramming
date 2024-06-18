package com.example.project9_2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // companion object는 정적 변수를 선언할 때 사용한다.
    // 스타일에 쓰일 전역변수 선언
    companion object {
        var sX = 1f
        var sY = 1f
        var angle = 0f
        var color = 1f
        var satur = 1f // 채도
    }

    lateinit var ibZoomin : ImageButton
    lateinit var ibZoomout : ImageButton
    lateinit var ibRotate : ImageButton
    lateinit var ibBright : ImageButton
    lateinit var ibDark : ImageButton
    lateinit var ibGray : ImageButton
    lateinit var graphicView : MyGraphicView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_menu_gallery)
        title = "미니 포토샵"

        var pictureLayout = findViewById<LinearLayout>(R.id.pictureLayout)
        graphicView = MyGraphicView(this)
        pictureLayout.addView(graphicView)

        clickIcons()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun clickIcons() {
        ibZoomin = findViewById<ImageButton>(R.id.ibZoomin)
        ibZoomout = findViewById<ImageButton>(R.id.ibZoomout)
        ibRotate = findViewById<ImageButton>(R.id.ibRotate)
        ibBright = findViewById<ImageButton>(R.id.ibBright)
        ibDark = findViewById<ImageButton>(R.id.ibDark)
        ibGray = findViewById<ImageButton>(R.id.ibGray)

        ibZoomin.setOnClickListener {
            sX = sX + 0.2f
            sY = sY + 0.2f
            graphicView.invalidate()
        }

        ibZoomout.setOnClickListener {
            sX = sX - 0.2f
            sY = sY - 0.2f
            graphicView.invalidate()
        }

        ibRotate.setOnClickListener {
            angle = angle + 20
            graphicView.invalidate()
        }

        ibBright.setOnClickListener {
            color = color + 0.2f
            graphicView.invalidate()
        }

        ibDark.setOnClickListener {
            color = color - 0.2f
            graphicView.invalidate()
        }

        ibGray.setOnClickListener {
            if (satur == 0f)
                satur = 1f
            else
                satur = 0f
            graphicView.invalidate()
        }
    }

    class MyGraphicView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            var cenX = this.width / 2f
            var cenY = this.height / 2f

            // 크기
            canvas.scale(sX, sY, cenX, cenY)

            // 회전
            canvas.rotate(angle, cenX, cenY)

            // 밝기 조정
            val paint = Paint()
            val array = floatArrayOf(   color,    0f,    0f,    0f,    0f,
                                           0f, color,    0f,    0f,    0f,
                                           0f,    0f, color,    0f,    0f,
                                           0f,    0f,    0f,    1f,    0f)
            val cm = ColorMatrix(array)
            if(satur == 0f) cm.setSaturation(satur) // 만약 채도가 0이면 ColorMatrix에 스타일 적용
            paint.colorFilter = ColorMatrixColorFilter(cm)

            var picture = BitmapFactory.decodeResource(resources, R.drawable.moss)
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f
            canvas.drawBitmap(picture, picX, picY, paint)

            picture.recycle()
        }
    }
}