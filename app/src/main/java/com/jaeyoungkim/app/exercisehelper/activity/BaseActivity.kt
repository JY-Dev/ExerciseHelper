package com.jaeyoungkim.app.exercisehelper.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutine
import com.jaeyoungkim.app.exercisehelper.util.DataProcess

open class BaseActivity : AppCompatActivity() {
    private var exerRoutine = listOf<ExerRoutine>()
    private lateinit var dataProcess : DataProcess
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }

    fun setExerRoutine(listExerRoutine : List<ExerRoutine>){
        exerRoutine = listExerRoutine
    }

    fun getExerRoutine():List<ExerRoutine>{
        println("test="+exerRoutine.size)
        return exerRoutine
    }
}