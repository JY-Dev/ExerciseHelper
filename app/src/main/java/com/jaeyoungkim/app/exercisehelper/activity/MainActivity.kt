package com.jaeyoungkim.app.exercisehelper.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_routine_btn.setOnClickListener {
            startActivity(Intent(this,
                RoutineRegister01::class.java))
        }
    }
}
