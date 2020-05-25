package com.jaeyoungkim.app.exercisehelper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.app_tool_bar.*

class DailyExercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_exercise)
        toolbar_title.text = "운동 하기"
    }
}
