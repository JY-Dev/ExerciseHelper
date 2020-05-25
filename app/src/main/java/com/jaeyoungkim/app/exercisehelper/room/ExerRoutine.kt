package com.jaeyoungkim.app.exercisehelper.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerRoutine(@PrimaryKey val group : String,val exerciseArray : String)
