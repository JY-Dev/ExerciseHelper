package com.jaeyoungkim.app.exercisehelper.activity.dailyexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.activity.BaseActivity
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineRegister02
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseKindAdapter
import com.jaeyoungkim.app.exercisehelper.util.DataProcess
import kotlinx.android.synthetic.main.activity_daily_exercise_detail.*

class DailyExerciseDetail : BaseActivity() {
    private var groupName = ""
    private var exerciseKindList = mutableListOf<RoutineRegister02.ExerciseKind>()
    private lateinit var dataProcess : DataProcess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_exercise_detail)
        init()
    }

    private fun init(){
        if(intent.hasExtra("groupName")) groupName = intent.extras!!.getString("groupName","")
        val adapter = ExerciseKindAdapter(exerciseKindList,this)
        list_exercise_kind.adapter = adapter
        dataProcess = DataProcess()
        dataProcess.loadOneData(this,this,groupName) {
            exerciseKindList = dataProcess.jsonToArray(getExerRoutine().exerciseArray)
            adapter.setArray(exerciseKindList)
            adapter.notifyDataSetChanged()
        }
    }
}
