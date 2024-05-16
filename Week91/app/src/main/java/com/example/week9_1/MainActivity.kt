package com.example.week9_1

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.RadioButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
    lateinit var btnStart : Button
    lateinit var btnEnd : Button
    lateinit var rdoCal : RadioButton
    lateinit var rdoTime : RadioButton
    lateinit var calView : CalendarView
    lateinit var tPicker : TimePicker
    lateinit var tvYear : TextView
    lateinit var tvMonth : TextView
    lateinit var tvDay : TextView
    lateinit var tvHour : TextView
    lateinit var tvMinute : TextView
    var selectYear : Int = 0
    var selectMonth : Int = 0
    var selectDay : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_white)
        title = "시간예약"

        btnStart = findViewById<Button>(R.id.btnStart) // 예약시작
        btnEnd = findViewById<Button>(R.id.btnEnd) // 예약완료

        chrono = findViewById<Chronometer>(R.id.chronometer1) // 예약하는데 걸린 시간을 나타내는 크로노미터

        rdoCal = findViewById<RadioButton>(R.id.rdoCal) // 달력을 보여주는 라디오 버튼
        rdoTime = findViewById<RadioButton>(R.id.rdoTime) // 시간을 보여주는 라디오 버튼

        tPicker = findViewById<TimePicker>(R.id.timePicker1) // 시간 설정
        calView = findViewById<CalendarView>(R.id.calendarView1) // 날짜 설정

        tvYear = findViewById<TextView>(R.id.tvYear)
        tvMonth = findViewById<TextView>(R.id.tvMonth)
        tvDay = findViewById<TextView>(R.id.tvDay)
        tvHour = findViewById<TextView>(R.id.tvHour)
        tvMinute = findViewById<TextView>(R.id.tvMinute)

        tPicker.visibility = View.INVISIBLE // 일단 두 뷰 모두 INVISIBLE로 설정하여 화면에서 보이지 않음
        calView.visibility = View.INVISIBLE

        rdoCal.setOnClickListener {
            tPicker.visibility = View.INVISIBLE
            calView.visibility = View.VISIBLE
        }

        rdoTime.setOnClickListener {
            tPicker.visibility = View.VISIBLE
            calView.visibility = View.INVISIBLE
        }

        btnStart.setOnClickListener { // 예약시작, 타이머 설정
            chrono.base = SystemClock.elapsedRealtime() // 예약시작을 누를 때마다 초기화한다.
            chrono.start() // 시간이 흐르기 시작
            chrono.setTextColor(Color.RED) // 빨간색으로 출력
        }

        // 버튼을 누르면 날짜, 시간 값을 가져온다.
        btnEnd.setOnClickListener {
            chrono.stop()
            chrono.setTextColor(Color.BLUE) // 파란색으로 출력

            tvYear.text = Integer.toString(selectYear)
            tvMonth.text = Integer.toString(selectMonth)
            tvDay.text = Integer.toString(selectDay)

            tvHour.text = Integer.toString(tPicker.getHour())
            tvMinute.text = Integer.toString(tPicker.getMinute())
        }

        calView.setOnDateChangeListener{ view, year, month, dayOfMonth -> // 캘린더의 날짜가 바뀔 때 실행되는 함수
            selectYear = year
            selectMonth = month + 1 // 0월부터 시작하기 때문에 1을 더한다.
            selectDay = dayOfMonth
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}