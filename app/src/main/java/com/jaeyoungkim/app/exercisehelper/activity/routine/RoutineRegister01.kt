package com.jaeyoungkim.app.exercisehelper.activity.routine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.dialog.GroupCreateDialog
import com.jaeyoungkim.app.exercisehelper.util.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_routine_register01.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar


class RoutineRegister01 : RoutineBaseActivity() {
    private var exercisePartRbGroup = mutableListOf<RadioGroup>()
    private lateinit var multiRadioGroup: MultiRadioGroup
    private var groupName = ""
    private var mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register01)
        init()
    }

    fun init() {
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        radioSetting()
        setGroupArray()
        back_btn.setOnClickListener {
            finish()
        }

        group_spinner.adapter = arrayAdapter
        group_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
              if(groupArray[position] == "그룹 추가"){
                  GroupCreateDialog(mContext) { group ->
                      dataProcess.insertData(mContext,group, mutableListOf())
                      groupName = group
                      setGroupArray()
                  }
              }else {
                  println("test="+groupArray[position])
                  groupName = groupArray[position]
              }
            }

        }

        finish_btn.setOnClickListener {
            val mIntent = Intent(this,
                RoutineRegister02::class.java)
            mIntent.putExtra("title",multiRadioGroup.getCheckedRadioName())
            mIntent.putExtra("groupName",groupName)
           startActivity(mIntent)
        }
    }

    fun radioSetting() {
        for (i in 0 until radioGroup_layout.childCount) {
            exercisePartRbGroup.add(radioGroup_layout.getChildAt(i) as RadioGroup)
        }
        multiRadioGroup = MultiRadioGroup(exercisePartRbGroup)
    }
}
