package com.example.week12_ex_3

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        var btnRead : Button
        var edtSD : EditText

        var btnMkdir : Button
        var btnRmdir : Button

        var btnFilelist : Button
        var edtFilelist : EditText

        btnRead = findViewById<Button>(R.id.btnRead)
        edtSD = findViewById<EditText>(R.id.edtSD)

        btnMkdir = findViewById<Button>(R.id.btnMkdir)
        btnRmdir = findViewById<Button>(R.id.btnRmdir)

        btnFilelist = findViewById<Button>(R.id.btnFilelist)
        edtFilelist = findViewById<EditText>(R.id.edtFilelist)

        var strSDpath = Environment.getExternalStorageDirectory().absolutePath
        var myDir = File("$strSDpath/mydir")

        btnRead.setOnClickListener {
            var inFs = FileInputStream("/sdcard/raw_text.txt")
            var txt = ByteArray(inFs.available())
            inFs.read(txt)
            edtSD.setText(txt.toString(Charsets.UTF_8))
            inFs.close()
        }

        btnMkdir.setOnClickListener {
            myDir.mkdir()
        }

        btnRmdir.setOnClickListener {
            myDir.delete()
        }

        btnFilelist.setOnClickListener {
            var sysDir = Environment.getRootDirectory().absolutePath
            var sysFiles = File(sysDir).listFiles()

            var strFname : String
            for (i in sysFiles.indices) {
                if (sysFiles[i].isDirectory == true)
                    strFname = "<폴더>" + sysFiles[i].toString()
                else
                    strFname = "<파일>" + sysFiles[i].toString()

                edtFilelist.setText(edtFilelist.text.toString() + "\n" + strFname)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}