package com.example.week13_ex_9_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.io.path.Path

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        // 컨텐츠를 MyGraphicView로 설정
        setContentView(MyGraphicView(this))
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}

private class MyGraphicView(context: Context) : View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint() // 스타일을 저장할 변수 paint

        // 초록색 선
        paint.isAntiAlias = true // 끝을 자연스럽게 처리
        paint.color = Color.GREEN
        paint.strokeWidth = 5f // 외곽선 두께
        canvas.drawLine(10f, 10f, 300f, 10f, paint)

        // 파란색 선
        paint.color = Color.BLUE
        paint.strokeWidth = 5f
        canvas.drawLine(10f, 30f, 300f, 30f, paint)

        // 빨간색으로 채워진 사각형
        paint.color = Color.RED
        paint.strokeWidth = 0f
        paint.style = Paint.Style.FILL // default는 FILL, 변경할 때까지 유지
        // 사각형을 그릴 4개의 좌표 입력
        val rect1 = Rect(10, 50, 10 + 100, 50 + 100)
        canvas.drawRect(rect1, paint)

        paint.style = Paint.Style.STROKE
        val rect2 = Rect(130, 50, 130 + 100, 50 + 100)
        canvas.drawRect(rect2, paint)

        // RectF는 파라미터로 Float형을 받는다.
        var rect3 = RectF(250f, 50f, 250f + 100f, 50f + 100f)
        canvas.drawRoundRect(rect3, 20f, 20f, paint)

        canvas.drawCircle(60f, 220f, 50f, paint)

        paint.strokeWidth = 5f
        paint.color= Color.YELLOW
        val path1 = android.graphics.Path() // Path()는 연결된 여러 점을 가진 클래스이다.
        path1.moveTo(10f, 290f) // 해당 좌표로 이동
        path1.lineTo(10f +50f, 290f + 50f) // 좌표를 이동하면서 점을 추가한다.
        path1.lineTo(10f + 100f, 290f)
        path1.lineTo(10f + 150f, 290f + 50f)
        path1.lineTo(10f + 200f, 290f)
        canvas.drawPath(path1, paint)

        paint.color = Color.BLACK
        paint.textSize = 30f
        canvas.drawText("안드로이드", 10f, 390f, paint)
    }
}