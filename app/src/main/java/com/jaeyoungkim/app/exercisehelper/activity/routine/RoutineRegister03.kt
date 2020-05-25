package com.jaeyoungkim.app.exercisehelper.activity.routine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.activity_routine_register03.*

import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar

class RoutineRegister03 : AppCompatActivity() {
    private lateinit var dayOfWeekCheckBoxList :MutableList<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register03)
        init()
    }

    private fun init(){
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_title.text = "운동 요일 선택"
        dayOfWeekCheckBoxList = mutableListOf(mon_btn,tues_btn,wed_btn,thurs_btn,fri_btn,sat_btn,sun_btn)
    }
}
