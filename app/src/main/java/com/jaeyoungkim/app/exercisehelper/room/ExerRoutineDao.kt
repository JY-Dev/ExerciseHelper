package com.jaeyoungkim.app.exercisehelper.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface ExerRoutineDao {
    @Query("SELECT * FROM exerroutine WHERE `group` =:groupName")
    fun getExerRoutine(groupName: String): Flowable<ExerRoutine>

    @Query("Delete FROM exerroutine WHERE `group` = :groupName")
    fun deleteGroup(groupName:String)

    @Query("Update exerroutine SET `group` = :groupName , exerciseArray =:exerRoutineArray WHERE `group` = :groupName")
    fun updateByGroup(groupName : String, exerRoutineArray: String)

    //이미 저장된 항목이 있을 경우 데이터를 덮어쓴다
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg exerRoutine: ExerRoutine)

    @Query("SELECT * FROM exerroutine")
    fun getAllExerRoutine(): Flowable<List<ExerRoutine>>
}