package com.jaeyoungkim.app.exercisehelper.util

import android.app.Activity
import android.widget.RadioButton
import android.widget.RadioGroup

class MultiRadioGroup(rbGroup : MutableList<RadioGroup>)  {
    private var exercisePartRbGroup : MutableList<RadioGroup> = rbGroup
    private var clearFlag = mutableListOf<Boolean>()

    init {
        exercisePartRbGroup.forEach {
            clearFlag.add(true)
        }
        radioOnCheckedListener()
    }

   private fun checkedRadio(radioGroup: RadioGroup){
        exercisePartRbGroup.forEach {
            if(radioGroup!=it) {
                clearFlag[(exercisePartRbGroup.indexOf(it))] = false
                it.clearCheck()
            }
        }

    }

    private fun radioOnCheckedListener(){
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

    fun getCheckedRadioName() :String{
        exercisePartRbGroup.forEach {
            for(i in 0 until it.childCount){
                val radioButton = it.getChildAt(i) as RadioButton
                if(radioButton.isChecked) return radioButton.text.toString()
            }
        }
        return "null"
    }

    fun getCheckedId() :Int{
        exercisePartRbGroup.forEach {
            for(i in 0 until it.childCount){
                val radioButton = it.getChildAt(i) as RadioButton
                if(radioButton.isChecked) return radioButton.id
            }
        }
        return -1
    }


}