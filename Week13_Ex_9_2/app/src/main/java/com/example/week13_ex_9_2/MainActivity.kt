package com.example.week13_ex_9_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week13_ex_9_2.MainActivity.Companion.CIRCLE
import com.example.week13_ex_9_2.MainActivity.Companion.LINE
import com.example.week13_ex_9_2.MainActivity.Companion.RECTANGLE
import com.example.week13_ex_9_2.MainActivity.Companion.TRIANGLE
import com.example.week13_ex_9_2.MainActivity.Companion.curShape

class MainActivity : AppCompatActivity() {
    // 정적으로 전역 상수 선언
    companion object {
        const val LINE = 1
        const val CIRCLE = 2
        const val TRIANGLE = 3
        const val RECTANGLE = 4
        var curShape = LINE // curShape를 먼저 LINE으로 초기화
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(MyGraphicView(this))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)
        title = "간단 그림판"
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//        insets
    }

    override fun onCreateOptionsMenu(menu: Menu?) : Boolean {
        super.onCreateOptionsMenu(menu)
        menu!!.add(0, 1, 0, "선 그리기")
        menu!!.add(0, 2, 0, "원 그리기")
        menu!!.add(0, 3, 0, "삼각형 그리기")
        menu!!.add(0, 4, 0, "사각형 그리기")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            1 -> {
                curShape = LINE // 선
                return true
            }
            2 -> {
                curShape = CIRCLE // 원
                return true
            }
            3 -> {
                curShape =  TRIANGLE // 삼각형
                return true
            }
            4 -> {
            curShape =  RECTANGLE // 사각형
            return true
        }
        }
        return super.onOptionsItemSelected(item)
    }
}

private class MyGraphicView(context: Context) : View(context) {
    var startX = -1f
    var startY = -1f
    var stopX = -1f
    var stopY = -1f

    override fun onTouchEvent(event: MotionEvent?) : Boolean { // 터치한 좌표를 받는 이벤트
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> { // 처음 터치했을 때,
                startX = event.x.toFloat()
                startY = event.y.toFloat()
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> { // 터치 중, 터치를 뗐을 때
                stopX = event.x.toFloat()
                stopY = event.y.toFloat()
                this.invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        var radius = 0.0
        paint.isAntiAlias = true
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED

        when (curShape) {
            LINE -> canvas.drawLine(startX, startY, stopX, stopY, paint)
            CIRCLE -> {
                radius = Math.sqrt(Math.pow((stopX - startX).toDouble(), 2.0) + Math.pow((stopY - startY).toDouble(), 2.0))
                canvas.drawCircle(startX, startY, radius.toFloat(), paint)
            }
            TRIANGLE -> {
                // 삼각형 꼭짓점 좌표 계산
                val midX = (startX + stopX) / 2

                // 삼각형 그리기
                canvas.drawLine(midX, startY, startX, stopY, paint) // 왼쪽 변
                canvas.drawLine(midX, startY, stopX, stopY, paint) // 오른쪽 변
                canvas.drawLine(startX, stopY, stopX, stopY, paint) // 밑변
            }
            RECTANGLE -> {
                canvas.drawRect(startX, startY, stopX, stopY, paint)
            }
        }
    }
}


