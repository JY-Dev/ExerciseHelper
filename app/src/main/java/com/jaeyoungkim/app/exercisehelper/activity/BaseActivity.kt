package com.jaeyoungkim.app.exercisehelper.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutine
import com.jaeyoungkim.app.exercisehelper.util.DataProcess

open class BaseActivity : AppCompatActivity() {
    private var mExerRoutineList = listOf<ExerRoutine>()
    private lateinit var mExerRoutine : ExerRoutine
    private lateinit var dataProcess : DataProcess
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    fun setExerRoutineList(listExerRoutine : List<ExerRoutine>){
        mExerRoutineList = listExerRoutine
    }

    fun getExerRoutineList():List<ExerRoutine>{
        return mExerRoutineList
    }

    fun setExerRoutine(exerRoutine: ExerRoutine){
        mExerRoutine = exerRoutine
    }

    fun getExerRoutine():ExerRoutine{
        return mExerRoutine
    }

}