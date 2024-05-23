package com.example.project7_3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var tvName : TextView
    lateinit var tvEmail : TextView
    lateinit var button1 : Button
    lateinit var dlgEdtName : EditText
    lateinit var dlgEdtEmail : EditText
    lateinit var toastText : TextView
    lateinit var dialogView : View
    lateinit var toastView1 : View
    lateinit var toastView2 : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)
        title = "사용자 정보 입력"

        tvName = findViewById<TextView>(R.id.tvName)
        tvEmail = findViewById<TextView>(R.id.tvEmail)
        button1 = findViewById<Button>(R.id.button1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button1.setOnClickListener {
            dialogView = View.inflate(this@MainActivity, R.layout.dialog1, null)
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("사용자 정보 입력")
            dlg.setIcon(R.drawable.onion_black)
            dlg.setView(dialogView)
            dlg.setPositiveButton("확인") {dialog, which ->
                dlgEdtName = dialogView.findViewById<EditText>(R.id.dlgEdt1)
                dlgEdtEmail = dialogView.findViewById<EditText>(R.id.dlgEdt2)
                tvName.text = dlgEdtName.text.toString()
                tvEmail.text = dlgEdtEmail.text.toString()
                var toast = Toast(this@MainActivity)
                toastView1 = View.inflate(this@MainActivity, R.layout.toast1, null)
                toastText = toastView1 .findViewById<TextView>(R.id.toastText1)
                toastText.text = "모스!"
                toast.view = toastView1
                toast.show()
            }

            dlg.setNegativeButton("취소") {dialog, which ->
                var toast = Toast(this@MainActivity)
                toastView2 = View.inflate(this@MainActivity, R.layout.toast2, null)
                toastText = toastView2 .findViewById<TextView>(R.id.toastText2)
                toastText.text = "취소했습니다"
                toast.view = toastView2
                toast.show()
            }

            dlg.show()
        }
    }
}