package com.jaeyoungkim.app.exercisehelper.activity

import android.content.Intent
import android.os.Bundle
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.dialog.GroupCreateDialog
import com.jaeyoungkim.app.exercisehelper.util.DataProcess
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        register_routine_btn.setOnClickListener {
            dataProcess.loadData(this,this)
            if(getExerRoutineArray().size>0){
                startActivity(Intent(this,
                    RoutineRegister01::class.java))
            } else {
                GroupCreateDialog(this) { groupName ->
                    dataProcess.insertData(this,groupName, mutableListOf())
                    startActivity(Intent(this,
                        RoutineRegister01::class.java))
                }
            }

        }
    }


}
