package com.jaeyoungkim.app.exercisehelper.util

import android.app.Activity
import android.widget.RadioButton
import android.widget.RadioGroup

class MultiRadioGroup(rbGroup : MutableList<RadioGroup> , checkFlag : MutableList<Boolean>,activity: Activity)  {
    private var exercisePartRbGroup : MutableList<RadioGroup> = rbGroup
    private var clearFlag = checkFlag
    private var mActivity = activity
    private var rbId = -1

    init {
        radioOnCheckedListener()
    }

    fun checkedRadio(radioGroup: RadioGroup){
        exercisePartRbGroup.forEach {
            if(radioGroup!=it) {
                clearFlag[(exercisePartRbGroup.indexOf(it))] = false
                it.clearCheck()
            }
        }

    }

    fun radioOnCheckedListener(){
        exercisePartRbGroup.forEach {
            it.setOnCheckedChangeListener { group, checkedId ->
                if(clearFlag[(exercisePartRbGroup.indexOf(it))]) {
                    checkedRadio(it)
                } else {
                    if(checkedId == -1)
                        clearFlag[(exercisePartRbGroup.indexOf(it))] = true
                }
            }
        }
    }

    fun getName() :String{
        exercisePartRbGroup.forEach {
            for(i in 0 until it.childCount){
                val radioButton = it.getChildAt(i) as RadioButton
                if(radioButton.isChecked) return radioButton.text.toString()
            }
        }
        return "null"
    }


}