package com.jaeyoungkim.app.exercisehelper.util

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jaeyoungkim.app.exercisehelper.activity.BaseActivity
import com.jaeyoungkim.app.exercisehelper.activity.RoutineRegister02
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutine
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutineDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataProcess {
    val listType : TypeToken<MutableList<RoutineRegister02.ExerciseKind>> = object : TypeToken<MutableList<RoutineRegister02.ExerciseKind>>() {}
    val gson = GsonBuilder().create()
    //Room data insert
    fun insertData(context: Context, group: String,exerRoutineArray : MutableList<RoutineRegister02.ExerciseKind>) {
        val array = gson.toJson(exerRoutineArray,listType.type)
        println("testcheck2="+array)
        val exerRoutine = ExerRoutine(group,array)
        Observable.just(exerRoutine)
            .subscribeOn(Schedulers.io())
            .subscribe({
                ExerRoutineDataBase.getInstance(context)
                    ?.getExerRoutineDao()
                    ?.insert(exerRoutine)
            },
                {
                })
    }

    fun updateData(context: Context,groupName: String,exerRoutineArray: MutableList<RoutineRegister02.ExerciseKind>,callBack: () -> Unit){
        Observable.just(ExerRoutineDataBase.getInstance(context))
            .subscribeOn(Schedulers.io())
            .subscribe {

                it?.getExerRoutineDao()?.updateByGroup(groupName,gson.toJson(exerRoutineArray,listType.type))
                callBack()
            }

    }

    //Room data load
    fun loadData(context: Context,mainActivity: BaseActivity){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getAllExerRoutine()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainActivity.setExerRoutineArray(it)
            }, {
                Log.e("MyTag", it.message)

            })
    }

    //Room data load
    fun loadData(context: Context,mainActivity: BaseActivity,Callback:()->Unit){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getAllExerRoutine()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainActivity.setExerRoutineArray(it)
                Callback()
            }, {
                Log.e("MyTag", it.message)

            })
    }

    fun deleteData(mContext: Context, groupName: String, callBack: () -> Unit) {
        Observable.just(ExerRoutineDataBase.getInstance(mContext))
            .subscribeOn(Schedulers.io())
            .subscribe {
                it?.getExerRoutineDao()?.deleteGroup(groupName)
                callBack()
            }
    }
}