package shinhan.ac.kr.exercisehelper

import android.app.Activity
import android.content.Context
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
            if(it.checkedRadioButtonId!=-1) {
                val radioButton = mActivity.findViewById<RadioButton>(it.checkedRadioButtonId)
                return radioButton.text.split(" ")[0]
            }
        }
        return "null"
    }


}