package com.example.week10_2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

@Suppress("deprecation")
class MainActivity : AppCompatActivity(), ActionBar.TabListener{

    lateinit var tabSong : ActionBar.Tab
    lateinit var tabArtist : ActionBar.Tab
    lateinit var tabAlbum : ActionBar.Tab
    var myFrags = arrayOfNulls<MyTabFragment>(3) // 프래그먼트 개수만큼 배열 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bar = this.supportActionBar // 상단에 표시할 액션바를 담을 변수 bar
        // deprecated된 코드 NAVAGATION_MODE_TABS, 내비게이션 모드의 탭으로 표시, null이 아니기 때문에 !!를 붙인다.
        bar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        // bar에 추가할 탭들 생성
        tabSong = bar.newTab()
        tabSong.text = "음악별"
        tabSong.setTabListener(this)
        bar.addTab(tabSong)

        tabArtist = bar.newTab()
        tabArtist.text = "가수별"
        tabArtist.setTabListener(this)
        bar.addTab(tabArtist)

        tabAlbum = bar.newTab()
        tabAlbum.text = "앨범별"
        tabAlbum.setTabListener(this)
        bar.addTab(tabAlbum)

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)
        title="액션바 & 프래그먼트"
    }

    // 오버라이드하는 메소드들
    // 탭을 선택했을 때 동작되는 코드
    override fun onTabSelected(tab:ActionBar.Tab, ft: FragmentTransaction) {
        // MyTabFragment 클래스의 객체를 담은 myTabFrag
        var myTabFrag : MyTabFragment? = null // null을 넣을 땐 타입에 ?를 붙인다!!

        // 만약 myFrags 배열의 해당 위치가 비어있다면, 프래그먼트를 생성하여 넣는다.
        if (myFrags[tab.position] == null) {
            myTabFrag = MyTabFragment()
            // 안드로이드에서는 액티비티간에 데이터를 주고받을 때, Bundle 클래스를 활용한다.
            // 키-값 쌍의 데이터를 담을 수 있는 객체
            val data = Bundle()
            // data 번들에 tabName(해당 탭의 문자열 값)에 해당하는 key 값을 저장
            data.putString("tabName", tab.text.toString())
            // 프래그먼트에 해당 번들을 전달
            myTabFrag.arguments = data
            myFrags[tab.position] = myTabFrag
        } else
        // 기존에 해당 탭을 선택한 적이 있다면 프래그먼트 배열에 이미 해당 프래그먼트가 생성되어 있으므로 재사용한다.
            myTabFrag = myFrags[tab.position]

        ft.replace(android.R.id.content, myTabFrag!!) // 프래그먼트를 액티비티의 컨텐츠 영역에 추가 또는 교체
    }
    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction) {}
    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction) {}

    // MyTabFragment 클래스, 사용자가 bar 탭에서 메뉴를 누르면, 해당 메뉴의 이름을 통해 어떤 탭을 띄울지 결정한다.
    class MyTabFragment : androidx.fragment.app.Fragment() {
        var tabName : String? = null
        override fun onCreate(savedInstanceState:Bundle?) {
            super.onCreate(savedInstanceState)
            var data = arguments
            // data 번들의 "tabName"에 해당하는 키 값을 불러와서 tabName변수에 저장한다.
            tabName = data!!.getString("tabName")
        }

        // 동적 View 생성
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
            // 리니어 레이아웃의 파라미터들
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            var baseLayout = LinearLayout(super.getActivity())
            baseLayout.orientation = LinearLayout.VERTICAL
            baseLayout.layoutParams = params

            if (tabName === "음악별") baseLayout.setBackgroundColor(Color.RED)
            if (tabName === "가수별") baseLayout.setBackgroundColor(Color.GREEN)
            if (tabName === "앨범별") baseLayout.setBackgroundColor(Color.BLUE)

            return baseLayout
        }
    }

}