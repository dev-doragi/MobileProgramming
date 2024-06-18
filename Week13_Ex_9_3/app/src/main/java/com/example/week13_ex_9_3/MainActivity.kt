package com.example.week13_ex_9_3

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
    }

    private class MyGraphicView(context: Context) : View(context) {

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            // 리소스의 이미지는 BitmapFactory.decodeResource() 메소드를 이용한다.
            // SD 카드의 이미지는 BitmapFactory.decodeFile() 메소드를 이용한다.
            var picture = BitmapFactory.decodeResource(resources, R.drawable.moss)
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f
            canvas.drawBitmap(picture, picX, picY, null) // 스타일을 따로 적용하지 않아 paint는 null 값을 넣는다.
            picture.recycle()
        }
    }
}