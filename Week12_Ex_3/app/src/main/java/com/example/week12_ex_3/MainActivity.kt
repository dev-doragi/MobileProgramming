package com.example.week12_ex_3

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.onion_black)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        var btnRead : Button
        var btnWrite: Button
        var edtSD : EditText

        var btnMkdir : Button
        var btnRmdir : Button

        var btnFilelist : Button
        var edtFilelist : EditText

        btnRead = findViewById<Button>(R.id.btnRead)
        btnWrite = findViewById<Button>(R.id.btnWrite)
        edtSD = findViewById<EditText>(R.id.edtSD)

        btnMkdir = findViewById<Button>(R.id.btnMkdir)
        btnRmdir = findViewById<Button>(R.id.btnRmdir)

        btnFilelist = findViewById<Button>(R.id.btnFilelist)
        edtFilelist = findViewById<EditText>(R.id.edtFilelist)

        var strSDpath = Environment.getExternalStorageDirectory().absolutePath
        var myDir = File("$strSDpath/mydir")

//        btnWrite.setOnClickListener {
//            var outFs = openFileOutput("raw_text.txt", Context.MODE_PRIVATE)
//            var str = edtSD.text.toString() // edtSD 값을 문자열로 변환
//            outFs.write(str.toByteArray())
//            outFs.close()
//            Toast.makeText(applicationContext, "raw_text.txt 이 저장됨.", Toast.LENGTH_SHORT).show()
//        }

        btnWrite.setOnClickListener {
            if (!myDir.exists()) {
                Toast.makeText(applicationContext, "mydir이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val file = File(myDir, "raw_text.txt")
                try {
                    val outFs = FileOutputStream(file)
                    val str = edtSD.text.toString()
                    outFs.write(str.toByteArray())
                    outFs.close()
                    Toast.makeText(applicationContext, "raw_text.txt 이 저장됨.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Error saving file: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRead.setOnClickListener {
            if (!myDir.exists()) {
                Toast.makeText(applicationContext, "mydir이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                var inFs = FileInputStream("sdcard/mydir/raw_text.txt")
                // txt는 inFs(/sdcard/mydir/raw_text.txt)의 가용 크기만큼 할당 받는다.
                var txt = ByteArray(inFs.available())
                inFs.read(txt) // inFs를 읽은 내용을 txt(바이트 배열 형태)에 저장
                edtSD.setText(txt.toString(Charsets.UTF_8)) // Charsets.UTF_8로 해야 한글이 제대로 보인다.
                inFs.close()
            }
        }

        btnMkdir.setOnClickListener {
            myDir.mkdir()
        }

        btnRmdir.setOnClickListener {
            if (myDir.exists()) {
                val success = deleteDirectory(myDir)
                if (success) {
                    Toast.makeText(applicationContext, "디렉토리가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "디렉토리를 삭제하는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "디렉토리가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
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

    private fun deleteDirectory(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.listFiles()
            if (children != null) {
                for (child in children) {
                    val success = deleteDirectory(child)
                    if (!success) {
                        return false
                    }
                }
            }
        }
        return dir.delete()
    }
}