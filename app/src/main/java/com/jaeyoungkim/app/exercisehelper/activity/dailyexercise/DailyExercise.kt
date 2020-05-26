package com.jaeyoungkim.app.exercisehelper.activity.dailyexercise

import android.content.Intent
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.activity.BaseActivity
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseGroupAdapter
import com.jaeyoungkim.app.exercisehelper.util.DataProcess
import kotlinx.android.synthetic.main.activity_daily_exercise.*
import kotlinx.android.synthetic.main.app_tool_bar.*

class DailyExercise : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_exercise)
        toolbar_title.text = "오늘의 운동"
        DataProcess().loadData(this,this) {
            val adapter = ExerciseGroupAdapter(this, getExerRoutineList())
            list_exercise_group.adapter = adapter
        }

        list_exercise_group.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,DailyExerciseDetail::class.java)
            intent.putExtra("groupName",getExerRoutineList()[position].group)
            startActivity(intent)
        }

    }
}
