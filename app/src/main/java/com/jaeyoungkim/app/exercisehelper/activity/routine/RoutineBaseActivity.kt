package com.jaeyoungkim.app.exercisehelper.activity.routine

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutine
import com.jaeyoungkim.app.exercisehelper.util.DataProcess

open class RoutineBaseActivity : AppCompatActivity() {
    lateinit var dataProcess : DataProcess
    private var exerRoutineArray = mutableListOf<ExerRoutine>()
    var group = ""
    var groupArray = mutableListOf<String>()
    var exerKindArray = mutableListOf<RoutineRegister02.ExerciseKind>()

    lateinit var arrayAdapter :ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataProcess = DataProcess()
        dataProcess.loadData(this,this)
        arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item,groupArray)
    }


    fun setExerRoutineArray(list : List<ExerRoutine>){
        exerRoutineArray.clear()
        exerRoutineArray.addAll(list)
        setGroupArray()
        arrayAdapter.notifyDataSetChanged()
        exerKindArray = getExerKindArray(group)
    }

    fun getExerRoutineArray() : MutableList<ExerRoutine>{
        return exerRoutineArray
    }

    fun setGroupArray(){
        groupArray.clear()
        exerRoutineArray.forEach {
            groupArray.add(it.group)
        }
        if(groupArray.size>0) groupArray.add("그룹 추가")
    }

    fun getExerKindArray(groupName:String) : MutableList<RoutineRegister02.ExerciseKind>{
        if(exerRoutineArray.size>0){
            exerRoutineArray.forEach {
                if(it.group==groupName) {
                    return dataProcess.gson.fromJson<MutableList<RoutineRegister02.ExerciseKind>>(it.exerciseArray,dataProcess.listType.type)
                }
            }
        }
        return mutableListOf()

    }
}