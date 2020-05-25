package com.jaeyoungkim.app.exercisehelper.activity

import android.content.Intent
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.adapter.ExerciseKindAdapter
import com.jaeyoungkim.app.exercisehelper.dialog.ExerciseRegisterListDailog
import kotlinx.android.synthetic.main.activity_routine_register02.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar

class RoutineRegister02 : BaseActivity() {

    private lateinit var exerciseListAdapter : ExerciseKindAdapter
    private var exerciseKindArray = mutableListOf<ExerciseKind>()
    private var title : String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register02)
        init()
        dataProcess.loadData(this,this) {
            exerciseListAdapter.exerciseKindList = exerKindArray
            exerciseListAdapter.notifyDataSetChanged()
        }
    }

    private fun init(){
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_title.text = "운동 리스트"
        if(intent.hasExtra("title")) title = intent.getStringExtra("title")
        if(intent.hasExtra("groupName")) group = intent.extras!!.getString("groupName","")
        exerciseListAdapter =
            ExerciseKindAdapter(
                exerKindArray,
                this
            )
        exercise_kind_listview.adapter = exerciseListAdapter
        exercise_kind_listview.emptyView = no_exercise_list
        exercise_register_btn.setOnClickListener {
            ExerciseRegisterListDailog(
                this,
                title
            ) { title, exerciseKind, exerciseSetNum, exercisePerformNum ->
                addExerciseList(title, exerciseKind, exerciseSetNum, exercisePerformNum)
            }
        }
        finish_btn.setOnClickListener {
            dataProcess.insertData(this,group,exerciseListAdapter.exerciseKindList)
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    fun addExerciseList(
        title: String?,
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
        exercise_kind_listview.smoothScrollBy(exercise_kind_listview.maxScrollAmount+2000,1000)
    }
    data class ExerciseKind(var title: String?,var exerciseKind: String,var exerciseSetNum: Int,var exercisePerformNum: Int)
}
