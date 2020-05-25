package com.jaeyoungkim.app.exercisehelper.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ExerRoutine::class),version = 1)
abstract class ExerRoutineDataBase : RoomDatabase() {
    abstract fun getExerRoutineDao(): ExerRoutineDao

    companion object {

        private var INSTANCE: ExerRoutineDataBase? = null

        fun getInstance(context: Context): ExerRoutineDataBase? {

            if(INSTANCE == null) {
                synchronized(ExerRoutineDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, ExerRoutineDataBase::class.java, "exerroutine.db").build()
                }
            }

            return INSTANCE
        }

    }

}