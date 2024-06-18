package com.example.week13_ex_9_4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.EmbossMaskFilter
import android.graphics.Paint
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
            var picture = BitmapFactory.decodeResource(resources, R.drawable.moss)

            var cenX = this.width / 2f
            var cenY = this.height / 2f
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f
//
//            // 회전
//            canvas.rotate(45f, cenX, cenY)
//            canvas.drawBitmap(picture, picX, picY, null)
//            // 평행 이동
//            canvas.translate(-150f, 200f)
//            canvas.drawBitmap(picture, picX, picY, null)
//
//            // 크기 변경
//            canvas.scale(2f, 2f, cenX, cenY)
//            canvas.drawBitmap(picture, picX, picY, null)
//
//            // 기울이기
//            canvas.skew(0.3f, 0.3f)
//            canvas.drawBitmap(picture, picX, picY, null)
//
//            // 사진에 블러 효과 추가하기
//            var paint = Paint()
//            var bMask : BlurMaskFilter
//
//            canvas.scale(0.5f, 0.5f, cenX, cenY)
//            bMask = BlurMaskFilter(150f, BlurMaskFilter.Blur.OUTER) // INNER, OUTER, SOLID 스타일
//            paint.maskFilter = bMask
//            canvas.drawBitmap(picture, picX, picY, paint)
//
//            picture.recycle() // 비트맵 리소스 해제
//
//            picture.recycle() // 비트맵 리소스 해제
//
//            // 엠보싱효과 추가하기
//            var paint = Paint()
//            paint.color = Color.GRAY
//            var eMask : EmbossMaskFilter
//
//            // EmbossMaskFilter(빛의 xyz 방향 1차 배열, 빛의 밝기, 반사 계수, 블러링 크기)
//            eMask = EmbossMaskFilter(floatArrayOf(3f, 3f, 3f), 0.5f, 5f, 10f)
//            paint.maskFilter = eMask
//            canvas.drawCircle(cenX, cenY, 150f, paint)

            var paint = Paint()
            // 4x5배열, 위에서부터 Red, Green, Blue, Alpha이며, 오른쪽 값은 밝기이다.
            // RGB 색상 대비를 2배로 변경
            var array = floatArrayOf( 2f, 0f, 0f, 0f, -25f,
                                      0f, 2f, 0f, 0f, -25f,
                                      0f, 0f, 2f, 0f, -25f,
                                      0f, 0f, 0f, 1f,   0f )
            // 해당 배열 요소는 ColorMatrix의 파라미터가 된다.
            var cm = ColorMatrix(array) // 색상 변환 행렬을 리턴하여 cm에 저장.
            paint.colorFilter = ColorMatrixColorFilter(cm)  // 색상 변환 행렬을 ColorMatrixColorFilter로 변환하고 paint의 colorFilter에 적용
            canvas.drawBitmap(picture, picX, picY, paint)
            picture.recycle() // 비트맵 리소스 해제
        }
    }
}
