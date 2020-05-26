package com.jaeyoungkim.app.exercisehelper.activity

import android.content.Intent
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.activity.dailyexercise.DailyExercise
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineBaseActivity
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineRegister01
import com.jaeyoungkim.app.exercisehelper.dialog.GroupCreateDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RoutineBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        daily_exercise_btn.setOnClickListener {
            startActivity(Intent(this,
                DailyExercise::class.java))
        }
        register_routine_btn.setOnClickListener {
            dataProcess.loadData(this,this)
            if(getExerRoutineArray().size>0){
                startActivity(Intent(this,
                    RoutineRegister01::class.java))
            } else {
                GroupCreateDialog(this) { groupName ->
                    dataProcess.insertData(this,groupName, mutableListOf())
                    startActivity(Intent(this,
                        RoutineRegister01::class.java))
                }
            }

        }
    }


}
