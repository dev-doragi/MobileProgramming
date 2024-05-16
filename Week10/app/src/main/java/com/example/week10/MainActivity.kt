package com.example.week10

import android.app.TabActivity
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("deprecation")
class MainActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tabHost = this.tabHost // 탭 호스트 생성

        var tabSpecDog = tabHost.newTabSpec("강아지").setIndicator("강아지") // 음악 탭 스펙 생성
        tabSpecDog.setContent(R.id.tabDog) // tabSong 리니어 레이아웃이 해당 탭이 된다.
        tabHost.addTab(tabSpecDog)

        var tabSpecCat = tabHost.newTabSpec("고양이").setIndicator("고양이")
        tabSpecCat.setContent(R.id.tabCat)
        tabHost.addTab(tabSpecCat)

        var tabSpecMoss = tabHost.newTabSpec("모스").setIndicator("모스")
        tabSpecMoss.setContent(R.id.tabMoss)
        tabHost.addTab(tabSpecMoss)

        tabHost.currentTab = 0

        tabHost.setOnTabChangedListener {
            // 탭이 변경될 때 실행할 코드
            val tabWidget = tabHost.tabWidget
            for (i in 0 until tabWidget.childCount) {
                tabWidget.getChildAt(i).setBackgroundColor(Color.WHITE)
            }
            tabWidget.getChildAt(tabHost.currentTab).setBackgroundColor(Color.CYAN)
        }
    }

}