package shinhan.ac.kr.exercisehelper

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_routine_register.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar


class RoutineRegister : AppCompatActivity() {
    private var exercisePartRbGroup = mutableListOf<RadioGroup>()
    private var clearFlag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register)
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        radioSetting()
        radioOnCheckedListener()
        back_btn.setOnClickListener {
            Toast.makeText(this,"asdf", Toast.LENGTH_SHORT).show()
        }

    }

    fun checkedRadio(radioGroup: RadioGroup){
        exercisePartRbGroup.forEach {
            if(radioGroup!=it) it.clearCheck()
        }
        clearFlag = false
        //radioGroup.clearCheck()
    }

    fun radioOnCheckedListener(){
        exercisePartRbGroup.forEach {
            it.setOnCheckedChangeListener { group, checkedId ->
                if(clearFlag)
                    checkedRadio(group)
                clearFlag = true
            }
        }
    }

    fun radioSetting(){
        for(i in 0 until radioGroup_layout.childCount){
            exercisePartRbGroup.add(radioGroup_layout.getChildAt(i) as RadioGroup)
        }
    }
}
