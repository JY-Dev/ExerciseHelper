package com.jaeyoungkim.app.exercisehelper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseGroupAdapter
import com.jaeyoungkim.app.exercisehelper.util.DataProcess
import kotlinx.android.synthetic.main.activity_daily_exercise.*
import kotlinx.android.synthetic.main.app_tool_bar.*

class DailyExercise : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_exercise)
        toolbar_title.text = "운동 하기"
        DataProcess().loadData(this,this) {
            val adapter = ExerciseGroupAdapter(this, getExerRoutine())
            list_exercise_group.adapter = adapter
        }


    }
}
