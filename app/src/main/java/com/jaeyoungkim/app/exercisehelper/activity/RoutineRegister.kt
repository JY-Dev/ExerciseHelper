package com.jaeyoungkim.app.exercisehelper.activity

import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseKindAdapter
import com.jaeyoungkim.app.exercisehelper.dialog.ExerciseRegisterListDailog
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.activity_routine_register.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar


class RoutineRegister : AppCompatActivity() {
    private var exercisePartRbGroup = mutableListOf<RadioGroup>()
    private var exerciseKindArray = mutableListOf<ExerciseKind>()
    private lateinit var dayOfWeekCheckBoxList :MutableList<CheckBox>
    private var dayOfWeekisCheckedList = mutableListOf<String>()
    private var routineResult = mutableMapOf<String,MutableList<ExerciseKind>>()
    private lateinit var exerciseListAdapter : ExerciseKindAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register)
        init()

    }

    fun init() {
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        radioSetting()
        radioOnclickSet()
        dayOfWeekCheckBoxList = mutableListOf(mon_btn,tues_btn,wed_btn,thurs_btn,fri_btn,sat_btn,sun_btn)
        back_btn.setOnClickListener {
            finish()
        }
        exerciseListAdapter =
            ExerciseKindAdapter(
                exerciseKindArray,
                this
            )

        exercise_kind_listview.adapter = exerciseListAdapter
        exercise_kind_listview.setOnTouchListener { v, event ->
            exercise_routine_scroll_view.requestDisallowInterceptTouchEvent(true)
            false
        }
        exercise_kind_listview.emptyView = no_exercise_list

        register_btn.setOnClickListener {
            dayOfWeekCheckBoxList.forEach {
                if(it.isChecked) dayOfWeekisCheckedList.add(it.text.toString())
            }
            if(exerciseListAdapter.exerciseKindList.size>0&&dayOfWeekisCheckedList.size>0){
                    dayOfWeekisCheckedList.forEach {
                        routineResult.set(it,exerciseListAdapter.exerciseKindList)
                    }
                //TODO DB 등록
                println("test="+routineResult.get("화"))
            } else {
                Toast.makeText(this,"운동 리스트 생성 및 요일을 선택해주세요.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun radioSetting() {
        for (i in 0 until radioGroup_layout.childCount) {
            exercisePartRbGroup.add(radioGroup_layout.getChildAt(i) as RadioGroup)
        }
    }

    fun radioOnclickSet() {
        exercisePartRbGroup.forEach { rb ->
            for (i in 0 until rb.childCount) {
                rb.getChildAt(i).setOnClickListener {
                    val radioBtn = it as RadioButton
                    ExerciseRegisterListDailog(
                        this,
                        radioBtn.text.toString()
                    ) { title, exerciseKind, exerciseSetNum, exercisePerformNum ->
                        addExerciseList(title, exerciseKind, exerciseSetNum, exercisePerformNum)
                    }
                }
            }

        }
    }

    fun addExerciseList(
        title: String,
        exerciseKind: String,
        exerciseSetNum: Int,
        exercisePerformNum: Int
    ) {
        exerciseListAdapter.exerciseKindList.add(
            ExerciseKind(
                title,
                exerciseKind,
                exerciseSetNum,
                exercisePerformNum
            )
        )
        exerciseListAdapter.notifyDataSetChanged()
        exercise_kind_listview.smoothScrollBy(exercise_kind_listview.maxScrollAmount+200,1000)
    }



    class ExerciseKind(var title: String,var exerciseKind: String,var exerciseSetNum: Int,var exercisePerformNum: Int)
}
