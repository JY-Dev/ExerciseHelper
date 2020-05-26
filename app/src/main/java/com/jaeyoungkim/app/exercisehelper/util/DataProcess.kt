package com.jaeyoungkim.app.exercisehelper.util

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jaeyoungkim.app.exercisehelper.activity.BaseActivity
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineBaseActivity
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineRegister02
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

    fun updateData(context: Context, groupName: String, exerRoutineArray: MutableList<RoutineRegister02.ExerciseKind>, callBack: () -> Unit){
        Observable.just(ExerRoutineDataBase.getInstance(context))
            .subscribeOn(Schedulers.io())
            .subscribe {

                it?.getExerRoutineDao()?.updateByGroup(groupName,gson.toJson(exerRoutineArray,listType.type))
                callBack()
            }

    }

    //Room data load
    fun loadData(context: Context, mainActivityRoutine: RoutineBaseActivity){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getAllExerRoutine()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainActivityRoutine.setExerRoutineArray(it)
            }, {
                Log.e("MyTag", it.message)

            })
    }

    //Room data load
    fun loadData(context: Context,baseActivity: BaseActivity, Callback:()->Unit){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getAllExerRoutine()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                baseActivity.setExerRoutineList(it)
                Callback()
            }, {
                Log.e("MyTag", it.message)

            })
    }

    //Room data load
    fun loadData(context: Context, mainActivityRoutine: RoutineBaseActivity, Callback:()->Unit){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getAllExerRoutine()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainActivityRoutine.setExerRoutineArray(it)
                Callback()
            }, {
                Log.e("MyTag", it.message)

            })
    }

    fun loadOneData(context: Context,baseActivity: BaseActivity,groupName: String,Callback:()->Unit){
        ExerRoutineDataBase
            .getInstance(context)!!
            .getExerRoutineDao()
            .getExerRoutine(groupName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                baseActivity.setExerRoutine(it)
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

    fun jsonToArray(jsonArray : String) : MutableList<RoutineRegister02.ExerciseKind>{
        return gson.fromJson(jsonArray,listType.type)
    }
}