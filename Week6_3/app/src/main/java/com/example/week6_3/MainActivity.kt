package com.example.week6_3

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var text1 : TextView
    lateinit var text2 : TextView
    lateinit var chkAgree : CheckBox
    lateinit var rGroup1 : RadioGroup
    lateinit var rdoSlime : RadioButton
    lateinit var rdoMoss : RadioButton
    lateinit var rdoCloud : RadioButton
    lateinit var rdoDante : RadioButton
    lateinit var rdoGGami : RadioButton
    lateinit var btnOK : Button
    lateinit var imgPet : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.onion_black)

        title = "캐릭터와 동물 사진 보기"

        text1 = findViewById<TextView>(R.id.Text1)
        chkAgree = findViewById<CheckBox>(R.id.ChkAgree)

        text2 = findViewById<TextView>(R.id.Text2)
        rGroup1 = findViewById<RadioGroup>(R.id.Rgroup1)
        rdoSlime = findViewById<RadioButton>(R.id.RdoSlime)
        rdoMoss = findViewById<RadioButton>(R.id.RdoMoss)
        rdoMoss = findViewById<RadioButton>(R.id.RdoCloud)
        rdoDante = findViewById<RadioButton>(R.id.RdoDante)
        rdoGGami = findViewById<RadioButton>(R.id.RdoGGami)

        btnOK = findViewById<Button>(R.id.BtnOK)
        imgPet = findViewById<ImageView>(R.id.ImgPet)

        chkAgree.setOnCheckedChangeListener { compoundButton, b ->
            if (chkAgree.isChecked == true) {
                text2.visibility = android.view.View.VISIBLE
                rGroup1.visibility = android.view.View.VISIBLE
                btnOK.visibility = android.view.View.VISIBLE
                imgPet.visibility = android.view.View.VISIBLE
            } else {
                text2.visibility = android.view.View.INVISIBLE
                rGroup1.visibility = android.view.View.INVISIBLE
                btnOK.visibility = android.view.View.INVISIBLE
                imgPet.visibility = android.view.View.INVISIBLE
            }
        }

        btnOK.setOnClickListener {
            when (rGroup1.checkedRadioButtonId) {
                R.id.RdoSlime -> imgPet.setImageResource(R.drawable.slime)
                R.id.RdoMoss -> imgPet.setImageResource(R.drawable.moss)
                R.id.RdoCloud -> imgPet.setImageResource(R.drawable.cloud)
                R.id.RdoSlime -> imgPet.setImageResource(R.drawable.slime)
                R.id.RdoDante -> imgPet.setImageResource(R.drawable.dante)
                R.id.RdoGGami -> imgPet.setImageResource(R.drawable.ggami)
                else -> Toast.makeText(applicationContext,
                    "먼저 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}