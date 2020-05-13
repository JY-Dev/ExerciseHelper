package com.jaeyoungkim.app.exercisehelper.activity

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseKindAdapter
import com.jaeyoungkim.app.exercisehelper.util.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_routine_register01.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar


class RoutineRegister01 : AppCompatActivity() {
    private var exercisePartRbGroup = mutableListOf<RadioGroup>()
    private var clearFlag = mutableListOf<Boolean>()
    private lateinit var multiRadioGroup: MultiRadioGroup

    private lateinit var exerciseListAdapter : ExerciseKindAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register01)
        init()

    }

    fun init() {
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        radioSetting()

        back_btn.setOnClickListener {
            finish()
        }




        finish_btn.setOnClickListener {
            val mIntent = Intent(this,RoutineRegister02::class.java)
            mIntent.putExtra("title",multiRadioGroup.getName())
           startActivity(mIntent)
        }
    }

    fun radioSetting() {
        for (i in 0 until radioGroup_layout.childCount) {
            exercisePartRbGroup.add(radioGroup_layout.getChildAt(i) as RadioGroup)
            clearFlag.add(true)
        }
        multiRadioGroup = MultiRadioGroup(exercisePartRbGroup,clearFlag,this)
    }




}
